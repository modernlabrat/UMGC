import sys
import boto3
from botocore.exceptions import ClientError, ParamValidationError

from datetime import datetime as dt

run = True

s3_client = boto3.client('s3') # S3 Client
s3_resource = boto3.resource('s3') # S3 Resource

bucket_count = 0
buckets = {} # holds bucket names

files = {
        "1": "aang.jpg",
        "2": "code_names.txt",
        "3": "large_US_cities.txt",
        "4": "lotus.jpg",
        "5": "rare_animals.txt",
        "6": "whitewolf.jpg",
        "7": "wraithapexlegends.jpg"
    }


def create_bucket():
    # method that creates S3 buckets given a name
    bucket_name = check_bucket_names(check_for_return(input("Enter a bucket name: ")))

    try:
        s3_client.create_bucket(Bucket=bucket_name) # create S3 Bucket
        print("%s Bucket successfully created." %bucket_name)
    except ClientError as e:
        print("Could not create Bucket: %s." %bucket_name)
    
    repeat("1")


def delete_from_bucket(bucket_name=""):
    # method that allows the user to delete objects from a bucket
    if bucket_name == "":
        bucket_name = get_bucket_name()

    objects = get_objects(bucket_name)

    if (len(objects) > 0):
        object_count = 1

        print("      OBJECTS      ")
        for bucket_object in objects.keys():
            print(str(object_count) + ":", objects.get(str(object_count)))
            object_count += 1
    
        option = get_object_input(check_for_return(input("Select an option: ")), objects)
        
        object_key = objects.get(str(option)) # key for s3 object
        
        confirmed_delete = confirm(object_key)
            
        if confirmed_delete in ["y", "Y"]:
            try:
                response = s3_client.delete_object(Bucket=bucket_name, Key=object_key)
                print(object_key, "was successfully deleted from", bucket_name)
                repeat("3", bucket_name)
            except ClientError as e:
                print("Unable to delete object from", bucket_name)
    else:
        print("This bucket is empty. Choose another bucket.")
        delete_from_bucket()

def upload_to_bucket(bucket_name=""):
    # method that allows users to choose from a list of files to upload
    if bucket_name == "":
        bucket_name = get_bucket_name() # get bucket name from user

    print("Location: %s" %bucket_name)
    
    folder_path = prompt_for_folder()

    print("      FILES      ")
    for key in files.keys():
        print(" ", key + ":", files.get(key))
    
    file_name = get_selected_file(check_for_return(input("Select an option: ")), files)
    
    try:
        response = s3_client.head_object(Bucket=bucket_name, Key=file_name)
        print(file_name, "is already stored in", bucket_name)
        upload_to_bucket()
    except ClientError as e:
        if e.response['Error']['Message'] == "Not Found":
            try:
                if folder_path == "":
                    s3_resource.Object(bucket_name,  file_name).upload_file("Homework1/" + file_name)
                    print(file_name, "was successfully uploaded to", bucket_name)
                else:
                    folder_key = folder_path + file_name
                    s3_resource.Object(bucket_name,  folder_key).upload_file("Homework1/" + file_name)
                    print(file_name, "was successfully uploaded to", bucket_name, "in", folder_path)
                
                repeat("2", bucket_name)
            except ClientError as e:
                if not e.response["Error"] == "S3UploadFailedError":
                    print("Unable to upload", file_name, "to", bucket_name)
            except boto3.exceptions.S3UploadFailedError as upfe:
                print("Unable to upload", file_name, "to", bucket_name)
        print("Unable to upload", file_name, "to", bucket_name)

def delete_bucket():
    #method that deletes a bucket
    bucket_name = get_bucket_name()
    objects = get_objects(bucket_name)
    
    if (len(objects) == 0):
        confirmed_delete = confirm(bucket_name)
    
        if confirmed_delete in ["y", "Y"]:
            try:
                s3_client.delete_bucket(Bucket=bucket_name)
                print("%s was successfully deleted." %bucket_name)
                repeat("4")
            except ClientError as e:
                print("Unable to delete %s" %bucket_name)
    else:
        delete_option = check_for_return(input("This bucket needs to be emptied first. Would you like to delete all objects in this bucket? Y/N: "))
        
        delete_string = "all of the objects in " + bucket_name
        confirmed_delete = confirm(delete_string)
        
        if confirmed_delete in ["y", "Y"]:
            try:
                for key in objects.keys():
                    object_key = objects.get(key)
                    response = s3_client.delete_object(Bucket=bucket_name, Key=object_key)

                print("Successfully deleted all buckets in", bucket_name)
                print("Deleting Bucket:", bucket_name)
                response = s3_client.delete_bucket(Bucket=bucket_name)
                print("%s was successfully deleted." %bucket_name)
                repeat("4")
            except ClientError as e:
                print("Error: ", e)

def copy_to_bucket(destination_bucket=""):
    # method that copies an Object to Bucket with option destination_bucket param for repeat
    print("Select a Bucket to copy an Object from: ")

    if destination_bucket == "":
        bucket_name = get_bucket_name()
    else:
        bucket_name = get_bucket_name(bucket_filter=destination_bucket)

    objects = get_objects(bucket_name)

    if len(objects) > 0:
        object_count = 1

        print("      OBJECTS      ")
        for bucket_object in objects.keys():
            print(str(object_count) + ":", objects.get(str(object_count)))
            object_count += 1
    
        option = get_object_input(check_for_return(input("Select an option: ")), objects)
        
        object_key = objects.get(str(option)) # key for s3 object

        if destination_bucket == "":
            print("Select a destination bucket: ")
            destination_bucket = valid_destination(get_bucket_name(bucket_filter=bucket_name), object_key, bucket_name)
        
        print("Source Bucket: %s" %bucket_name)
        print("Selected Object: %s" %object_key)
        print("Destination Bucket: %s" %destination_bucket)

        copy_source = {
            'Bucket': bucket_name,
            'Key': object_key
        }
        
        try:
            s3_client.copy(copy_source, destination_bucket, object_key) # copy tje Object
    
            print(object_key, "was successfully copied to", destination_bucket, "from", bucket_name)
            repeat("5", destination_bucket)
        except ClientError as e:
            print("Unable tp copy", object_key, "to", destination_bucket, "from", bucket_name)
    else:
        print("This Bucket is empty. Choose another bucket.")
        copy_to_bucket()

def download_from_bucket(bucket_name=""):
    # method that downloads file from Bucket
    if bucket_name == "":
        bucket_name = get_bucket_name() # get bucket name from user
    
    objects = get_objects(bucket_name, filtered=True)
    
    option = get_object_input(check_for_return(input("Select an option: ")), objects)
        
    object_key = objects.get(str(option)) # key for s3 object
    
    try:
        file_name = object_key.split("/")
        
        copy_string = "copy_of_"+file_name[-1]
        s3_client.download_file(bucket_name, object_key, Filename=copy_string)
        print(object_key, "was successfully downloaded as:", copy_string)
        repeat("6", bucket=bucket_name)
    except ClientError as e:
        print("Unable to download:", object_key)
        download_from_bucket()

def exit_program():
    # method that displays date and time then exits program
    current_time = dt.now()
    print("Timestamp: %s Goodbye!" %current_time)
    global run 
    run = False
    sys.exit(0)

def check_for_return(user_input):
    # returns user to menu based on user input
    user_input = user_input.strip()

    if user_input is "<":
        start()
    return user_input  
    
def valid_destination(selected_destination, selected_object, source_bucket):
    #method that checks if destination Bucket contains Object that wants to be copied
    objects = get_objects(selected_destination)
    
    if selected_object in objects.values():
        print(selected_object, "is already in", selected_destination)
        print("Select a different bucket.")
        return valid_destination(get_bucket_name(bucket_filter=source_bucket), selected_object, source_bucket)
    else:
        return selected_destination
        
    
def available_bucket(bucket_name):
    # method that returns True or False if bucket name is avaliable in s3
    try:
        response = s3_client.head_bucket(Bucket=bucket_name)
        return False
    except ClientError as e:
        if (e.response['Error']['Message'] == "Not Found"):
            return True
    except ParamValidationError as e:
        return False

def check_bucket_names(bucket_name):
    # method that checks for valid bucket name
    valid_name = ""
    
    if bucket_name not in buckets.values():
        if len(bucket_name) > 2 and len(bucket_name) < 64 and " " not in bucket_name:
            if available_bucket(bucket_name) and bucket_name.islower():
                    if bucket_name.isalnum() or '-' in bucket_name or '.' in bucket_name:
                        valid_name = bucket_name

    if valid_name is not "":
        return valid_name
    else:
        print("Invalid Bucket Name:\n  - must be a unique Bucket name\n  - must be between 3 and 63 characters\n  - cannot contain spaces\n  - cannot contain uppercase letters\n  - cannot resemble an IP address\n  - can only contain numbers, lowercase letters, periods and hyphens")
        return check_bucket_names(check_for_return(input("Enter a new Bucket name: ")))

def valid_path(path_string):
    # method that checks for valid path string for Folder creation, folder names can only be alphanumeric for simplicity.
    path_string = path_string.strip()
    
    path_length = len(path_string)
    # Get last character of string i.e. char at index position len -1
    if path_length > 0:
        last_char = path_string[path_length -1]
        first_char = path_string[0]
    
        if "/" in path_string and last_char == "/":
            if first_char is not "/":
                path_list = path_string.split('/')
                
                for item in path_list:
                    if any(not c.isalnum() for c in item) or len(item) < 0:
                        print("Please enter a valid path i.e 'Jobs/Work/'. For simplicity, folder names can only contain numbers, spaces, and letters: ")
                        return valid_path(check_for_return(input("Enter Folder Path: ")))
        
                return path_string
            else:
                print("Folder names cannot begin with a character.")
                print("Please enter a valid path i.e 'Jobs/Work/'. For simplicity, folder names can only contain numbers, spaces, and letters: ")
                return valid_path(check_for_return(input("Enter Folder Path: ")))
        else:
            print("Please enter a valid path i.e 'Jobs/Work/'. For simplicity, folder names can only contain numbers, spaces, and letters")
            return valid_path(check_for_return(input("Enter new folder path: ")))
    else:
        print("Please enter a valid path i.e 'Jobs/Work/'. For simplicity, folder names can only contain numbers, spaces, and letters")
        return valid_path(check_for_return(input("Enter new folder path: ")))

def prompt_for_folder():
    # method that prompts for a folder path
    folder_path = ""
    
    folder_response = check_for_return(input("Do you want to upload an Object to a folder? Y/N: "))
    
    if folder_response in ["y", "Y", "n", "N"]:
        if folder_response in ["y", "Y"]:
            folder_path = valid_path(check_for_return(input("Enter folder path: ")))
            print("The Object will be stored here: %s. If the folder does not exist, a new folder will be created." %folder_path)
            return folder_path
    else:
        print("Please enter a valid response.")
        return prompt_for_folder()
    return folder_path

def get_selected_file(option, files):
    # method that gets the selected file name based on user's input
    if option in files.keys():
        return files.get(option)
    else:
        return get_selected_file(check_for_return(input("Please select a valid menu option: ")), files)

def get_buckets():
    # msthod that gets Buckets in s3
    global bucket_count

    if len(buckets) > 0: # there should be no buckets stored in case of getting buckets again
        buckets.clear()
        bucket_count = 0
    # method that gets the all the buckets
    try:
        response = s3_client.list_buckets() # automatically get buckets
        
        if "Buckets" in response:
            for bucket in response['Buckets']:
                bucket_count += 1
                buckets[str(bucket_count)] = bucket['Name']
    except ClientError as e:
        print("An Error Occured. Unable to get buckets.")
        start()

def get_bucket_name(bucket_filter=""):
    # method that prompts the user for a bucket and returns the bucket name
    if (bucket_count > 0):
        counter = 1
        print("      BUCKETS      ")
        
        if bucket_filter == "":
            for bucket in buckets:
                print(" ", str(counter) + ":", buckets.get(str(counter)))
                counter += 1
    
            option = check_for_return(input("Choose a bucket: "))
            if option in buckets.keys():
                return buckets.get(option)
            else:
                print("Select an appropiate option\n")
                return get_bucket_name()
        else:
            filtered_buckets = buckets.copy()

            buckets_key_list = list(buckets.keys())
            buckets_val_list = list(buckets.values())
 
            # print key with val 100
            position = buckets_val_list.index(bucket_filter)

            filter_key = buckets_key_list[position]

            del filtered_buckets[filter_key]

            for bucket_key in filtered_buckets.keys():
                print(" ", bucket_key + ":", filtered_buckets.get(bucket_key))

            option = check_for_return(input("Choose a bucket: "))
            if option in filtered_buckets.keys():
                return filtered_buckets.get(option)
            else:
                print("Select an appropiate option\n")
                return get_bucket_name(bucket_filter)
    else:
        print("No buckets found. You should create one with menu option 1.\n")

def get_objects(bucket_name, filtered=False):
    # method that gets objects given bucket_name via params
    objects = {}
    object_count = 0;
    
    try:
        response = s3_client.list_objects(Bucket=bucket_name)
        
        if "Contents" in response:
            for key in response["Contents"]:
                object_count += 1
                if filtered: # display Objects in a nice format for readability
                    filtered_objects = {}
                    file_objects = {}
        
                    if '/' in key['Key']:
                        filtered_list = key['Key'].split("/")
                        if len(filtered_list) > 1:
                            file_objects[str(object_count)] = filtered_list[-1]
                            filtered_objects[key['Key']] = file_objects.get(str(object_count))
                    else:
                        file_objects[str(object_count)] = key['Key']
                        filtered_objects[key['Key']] = file_objects.get(str(object_count))
                    
                    if len(filtered_objects) > 0:
                        for bucket_object in filtered_objects.keys():
                            if "/" in bucket_object:
                                if filtered_objects.get(bucket_object) != "":
                                    path_list = bucket_object.split("/")
                                    path_list.pop(-1)
            
                                    folder_path_cleaned = ""
                                    for path_item in path_list:
                                        folder_path_cleaned += path_item + "/"
            
                                    print(str(object_count) + ":", filtered_objects.get(bucket_object), "in", folder_path_cleaned)
                                    objects[str(object_count)] = bucket_object
                                else:
                                    object_count -= 1
                            else:
                              print(str(object_count) + ":", filtered_objects.get(bucket_object))
                              objects[str(object_count)] = filtered_objects.get(bucket_object)
                    else:
                        print("This Bucket is empty. Choose another bucket.")
                        start()
                else:
                    objects[str(object_count)] = key['Key']
    except ClientError as e:
        print("An error occured. Unable to load %s Bucket objects." %bucket_name)
        start()

    return objects

def get_object_input(option, objects):
    # gets the appropiate menu option according to the objects dict
    if option in objects.keys():
        return option
    else:
        return get_object_input(check_for_return(input("Please select an appropiate menu option: ")), objects)

def repeat(menu_option, bucket=""):
    # method that prompts user to repeat a menu option given an optional bucket
    if menu_option == "1":
        answer = input('Do you want to create another Bucket? Y/N: ')
    elif menu_option == "2":
        answer = input("Do you want to upload another Object to %s? Y/N: " %bucket)
    elif menu_option == "3":
        answer = input("Do you want to delete another Object from %s? Y/N: " %bucket)
    elif menu_option == "4":
        answer = input("Do you want to delete another Bucket? Y/N: ")
    elif menu_option == "5":
        answer = input("Do you want to copy another Object to %s? Y/N: " %bucket)
    elif menu_option == "6":
        answer = input("Do you want to download another Object from %s? Y/N: " %bucket)
    
    answer = check_for_return(answer)

    if answer in ["y","Y","n","N"]:
        if answer in ["y", "Y"]:
            get_buckets()
            if menu_option in ["2", "3", "6"]:
                menu[menu_option](bucket_name=bucket)
            elif menu_option is "5":
                menu[menu_option](destination_bucket=bucket)
            else:
                menu[menu_option]()
    else:
        print("Please enter a valid answer.")
        repeat(menu_option, bucket=bucket)
        
def confirm(item):
    # method that confirms delete on Objects and Buckets
    answer = check_for_return(input("Are you sure you want to delete %s? Y/N: " %item))

    if answer in ["y", "Y", "n", "N"]:
        return answer
    else:
        print("Please enter a valid menu option.")
        return confirm(item)

menu = {
    "1": create_bucket,
    "2": upload_to_bucket,
    "3": delete_from_bucket,
    "4": delete_bucket,
    "5": copy_to_bucket,
    "6": download_from_bucket,
    "7": exit_program
}

def start():
    # method that displays menu and calls the appropiate function based on user input
    while(run):
        print("      MENU      \n1: Create Bucket\n2: Upload an Object to a Bucket\n3: Delete an Object from a Bucket\n4: Delete Bucket\n5: Copy an Object to a Bucket\n6: Download a File Object from a Bucket\n7: Exit Program")
        print("\nEnter '<' to return to the menu at any time." )
        option = input("\nSelect an option: ")
    
        option = option.strip()
    
        if option in menu.keys() and option != "7":
            get_buckets()
            menu[option]()
        elif option == "7":
            exit_program()
        else:
            print("Please enter a valid menu option\n")
     
start()
