import boto3
import re
from botocore.exceptions import ClientError

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
    bucket_name = check_bucket_names(input("Enter a bucket name: "))

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
    
        option = get_object_input(input("Select an option: "), objects)
        
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

    print("      FILES      ")
    for key in files.keys():
        print(" ", key + ":", files.get(key))
    
    file_name = selected_file(input("Select an option: "), files)
    
    try:
        response = s3_client.head_object(Bucket=bucket_name, Key=file_name)
        print(file_name, "is already stored in", bucket_name)
        upload_to_bucket()
    except ClientError as e:
        if e.response['Error']['Message'] == "Not Found":
            try:
                s3_resource.Object(bucket_name,  file_name).upload_file("Homework1/" + file_name)
                print(file_name, "was successfully uploaded to", bucket_name)
                repeat("2", bucket_name)
            except ClientError as e:
                print("Unable to upload", file_name, "to", bucket_name)
        else:
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
        delete_option = input("This bucket needs to be emptied first. Would you like to delete all objects in this bucket? Y/N: ")
        
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
    print("Select a Bucket to copy an Object from: ")
    bucket_name = get_bucket_name()

    objects = get_objects(bucket_name)

    if len(objects) > 0:
        object_count = 1

        print("      OBJECTS      ")
        for bucket_object in objects.keys():
            print(str(object_count) + ":", objects.get(str(object_count)))
            object_count += 1
    
        option = get_object_input(input("Select an option: "), objects)
        
        object_key = objects.get(str(option)) # key for s3 object

        if destination_bucket == "":
            print("Select a destination bucket: ")
            destination_bucket = valid_destination(get_bucket_name(bucket_filter=bucket_name))
        
        print("Source Bucket: %s" %bucket_name)
        print("Selected Object: %s" %object_key)
        print("Destination Bucket: %s" %destination_bucket)

        copy_source = {
            'Bucket': bucket_name,
            'Key': object_key
        }
        
        try:
            s3_client.copy(copy_source, destination_bucket, object_key)
            print(object_key, "was successfully copied to", destination_bucket, "from", bucket_name)
            repeat("5", destination_bucket)
        except ClientError as e:
            print("Error:", e)
            print("Unable tp copy", object_key, "to", destination_bucket, "from", bucket_name)
    else:
        print("This Bucket is empty. Choose another bucket.")
        copy_to_bucket()

def exit_program():
    # method that displays date and time then exits program
    print("Goodbye!")
    global run 
    run = False
    
def valid_destination(bucket, selected_object):
    objects = get_objects(bucket)
    
    if selected_object in objects.values():
        print(selected_object)
    
def available_bucket(bucket_name):
    # method that returns True or False if bucket name is avaliable in s3
    try:
        response = s3_client.head_bucket(Bucket=bucket_name)
        return False
    except ClientError as e:
        if (e.response['Error']['Message'] == "Not Found"):
            return True

def check_bucket_names(bucket_name):
    # method that checks for valid bucket name
    valid_name = ""
    
    if bucket_name not in buckets.values():
        if len(bucket_name) > 2 and len(bucket_name) < 64 and " " not in bucket_name:
            if available_bucket(bucket_name) and bucket_name.islower():
                    if bucket_name.isalnum() or '-' in bucket_name or '.' in bucket_name:
                        valid_name = bucket_name

    if valid_name is not "":
        print(valid_name)
        return valid_name
    else:
        print("Invalid Bucket Name:\n  - must be a unique Bucket name\n  - must be between 3 and 63 characters\n  - cannot contain spaces\n  - cannot contain uppercase letters\n  - cannot resemble an IP address\n  - can only contain numbers, lowercase letters, periods and hyphens")
        return check_bucket_names(input("Enter a new Bucket name: "))


def selected_file(option, files):
    # method that gets the selected file name based on user's input
    if option in files.keys():
        return files.get(option)
    else:
        return selected_file(input("Please select a valid menu option: "), files)

def get_buckets():
    # msthod that gets Buckets in s3
    global bucket_count

    if len(buckets) > 0: # there should be no buckets stored in case of getting buckets again
        buckets.clear()
        bucket_count = 0
    # method that gets the all the buckets
    response = s3_client.list_buckets() # automatically get buckets
    
    if "Buckets" in response:
        for bucket in response['Buckets']:
            bucket_count += 1
            buckets[str(bucket_count)] = bucket['Name']

def get_bucket_name(bucket_filter=""):
    # method that prompts the user for a bucket and returns the bucket name
    if (bucket_count > 0):
        counter = 1
        print("      BUCKETS      ")
        
        if bucket_filter == "":
            for bucket in buckets:
                print(" ", str(counter) + ":", buckets.get(str(counter)))
                counter += 1
            
            option = input("Choose a bucket: ")
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

            option = input("Choose a bucket: ")
            if option in filtered_buckets.keys():
                return filtered_buckets.get(option)
            else:
                print("Select an appropiate option\n")
                return get_bucket_name(bucket_filter)
    else:
        print("No buckets found. You should create one with menu option 1.\n")

def get_objects(bucket_name):
    # method that gets objects given bucket_name via params
    objects = {}
    object_count = 0;
    
    response = s3_client.list_objects(Bucket=bucket_name)
    
    if "Contents" in response:
        for key in response["Contents"]:
            object_count += 1
            objects[str(object_count)] = key['Key']
        
    return objects

def get_object_input(option, objects):
    # gets the appropiate menu option according to the objects dict
    if option in objects.keys():
        return option
    else:
        return get_object_input(input("Please select an appropiate menu option: "), objects)

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
        answer = input("Do you want to download another Object from %s? Y/N: " &bucket)
    
    if answer in ["y","Y","n","N"]:
        if answer in ["y", "Y"]:
            get_buckets()
            if menu_option in ["2", "3", "5", "6"]:
                menu[menu_option](bucket)
            else:
                menu[menu_option]()
    else:
        print("Please enter a valid menu option.")
        repeat(menu_option, bucket)
        
def confirm(item):
    # method that confirms delete on Objects and Buckets
    answer = input("Are you sure you want to delete %s? Y/N: " %item)

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
    #"6": download_from_bucket,
    "7": exit_program
}

def start():
    # method that displays menu and calls the appropiate function based on user input
    while(run):
        print("      MENU      \n1: Create Bucket\n2: Upload to Bucket\n3: Delete an Object from a Bucket\n4: Delete Bucket\n5: Copy to Bucket\n6: Download From Bucket\n7: Exit Program")
        
        option = input("\nSelect an option: ")

        if option in menu.keys() and option != "7":
            get_buckets()
            menu[option]()
        elif option == "7":
            exit_program()
        else:
            print("Please enter a valid menu option\n")
     
start()
