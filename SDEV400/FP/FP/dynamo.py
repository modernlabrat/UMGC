import boto3
from botocore.exceptions import ClientError

def tables_created(dynamodb):
    # method that creates Users and Products table
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
    # method that checks if tables are created
    try:
        products_table = dynamodb.describe_table(TableName="Products")
        users_table = dynamodb.describe_table(TableName="Users")
        invoices_table = dynamodb.describe_table(TableName="Invoices")
        
        return True
    except dynamodb.exceptions.ResourceNotFoundException:
        return False


def has_products(dynamodb):
    # method that checks if Products table has products
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
    # method that fills table
    try:
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
                    },
                    {
                        'PutRequest': {
                            'Item': {
                                'Catalog': {'S': 'Shirts'},
                                'Name': {'S': 'Pink Short Sleeve'},
                                'Price': {'S': '$3.99'}
                            }
                        }
                    },
                    {
                        'PutRequest': {
                            'Item': {
                                'Catalog': {'S': 'Pants'},
                                'Name': {'S': 'Khakis'},
                                'Price': {'S': '$54.95'}
                            }
                        }
                    },
                    {
                        'PutRequest': {
                            'Item': {
                                'Catalog': {'S': 'Pants'},
                                'Name': {'S': 'Distressed Black Jeans'},
                                'Price': {'S': '$21.95'}
                            }
                        }
                    },
                    {
                        'PutRequest': {
                            'Item': {
                                'Catalog': {'S': 'Shoes'},
                                'Name': {'S': 'Nike Revolution 5'},
                                'Price': {'S': '$49.97'}
                            }
                        }
                    },
                    {
                        'PutRequest': {
                            'Item': {
                                'Catalog': {'S': 'Shoes'},
                                'Name': {'S': 'Black Classic Crocs'},
                                'Price': {'S': '$49.99'}
                            }
                        }
                    }
                ]
            }
        )
    except dynamodb.exceptions.ResourceNotFoundException:
        print("filling data")


def run(dynamodb):
    # method that creates and fill appropriate tables
    if tables_created(dynamodb):
        if is_finished(dynamodb):
            if not has_products(dynamodb):
                fill_products(dynamodb)
                run(dynamodb)
        else:
            run(dynamodb)
    else:
        run(dynamodb)