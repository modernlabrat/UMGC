s3_client = boto3.client('s3') # S3 Client
s3_resource = boto3.resource('s3') # S3 Resource

def show_image(image_path):
    bucket_name = "sdev400-products-ks"
