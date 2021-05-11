import boto3
from botocore.exceptions import ClientError

def tables_created(dynamodb):
    try:
        products_table = dynamodb.describe_table(TableName="Products")

        try:
            invoices_table = dynamodb.describe_table(TableName="Users")

            try:
                invoices_table = dynamodb.describe_table(TableName="Invoices")
            except dynamodb.exceptions.ResourceNotFoundException:
                invoices_table = dynamodb.create_table(
                TableName='Invoices',
                KeySchema=[
                    {
                        'AttributeName': 'Invoice',
                        'KeyType': 'HASH'  # Partition key
                    }
                ],
                AttributeDefinitions=[
                    {
                        'AttributeName': 'Invoice',
                        'AttributeType': 'S'
                    }
                ],
                ProvisionedThroughput={
                    'ReadCapacityUnits': 20,
                    'WriteCapacityUnits': 20
                }
            )
        except dynamodb.exceptions.ResourceNotFoundException:
            users_table = dynamodb.create_table(
            TableName='Users',
            KeySchema=[
                {
                    'AttributeName': 'Username',
                    'KeyType': 'HASH'  # Partition key
                }
            ],
            AttributeDefinitions=[
                {
                    'AttributeName': 'Username',
                    'AttributeType': 'S'
                }
            ],
            ProvisionedThroughput={
                'ReadCapacityUnits': 20,
                'WriteCapacityUnits': 20
            }
        )

    except dynamodb.exceptions.ResourceNotFoundException:
        products_table = dynamodb.create_table(
            TableName='Products',
            KeySchema=[
                {
                    'AttributeName': 'Catalog',
                    'KeyType': 'HASH'  # Partition key
                },
                {
                    'AttributeName': 'Name',
                    'KeyType': 'RANGE'  # Partition key
                }
            ],
            AttributeDefinitions=[
                {
                    'AttributeName': 'Catalog',
                    'AttributeType': 'S'
                },
                {
                    'AttributeName': 'Name',
                    'AttributeType': 'S'
                }
            ],
            ProvisionedThroughput={
                'ReadCapacityUnits': 20,
                'WriteCapacityUnits': 20
            }
        )
    return True


def is_finished(dynamodb):
    try:
        products_table = dynamodb.describe_table(TableName="Products")
        users_table = dynamodb.describe_table(TableName="Users")
        invoices_table = dynamodb.describe_table(TableName="Invoices")
        
        return True
    except dynamodb.exceptions.ResourceNotFoundException:
        return False


def has_products(dynamodb):
    try:
        response = dynamodb.get_item(TableName="Products", Key={
            'Catalog': {'S': "Shirts"}, 'Name': {'S': "LA Graphic Tee"}})
        if response.get("Item") is None:
            return False
        else:
            return True
    except dynamodb.exceptions.ResourceNotFoundException:
        return False


def fill_products(dynamodb):
    response = dynamodb.batch_write_item(
        RequestItems={
            'Products': [
                {
                    'PutRequest': {
                        'Item': {
                            'Catalog': {'S': 'Shirts'},
                            'Name': {'S': 'LA Graphic Tee'},
                            'Price': {'S': '$10.00'}
                        }
                    }
                }
            ]
        }
    )


def run(dynamodb):
    if tables_created(dynamodb):
        if is_finished(dynamodb):
            if not has_products(dynamodb):
                fill_products(dynamodb)
                run(dynamodb)
            else:
                return True

    return False