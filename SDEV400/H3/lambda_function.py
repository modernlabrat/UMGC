import json
import boto3
from botocore.exceptions import ClientError


def lambda_handler(event, context):
    # TODO implement
    dynamodb = boto3.client('dynamodb')

    run(dynamodb)
    sport = event["sport"]
    team = event["team"]

    if sport not in ["Hockey", "Basketball", "Baseball"]:
        return {
            'statusCode': 400,
            'Error': json.dumps("Sports Available: Hockey, Baseball, and Basketball")
        }

    if team not in ["Lakers", "Wizards", "Nets", "Bruins", "Lightning", "Islanders", "Red Sox", "Yankees", "Dodgers"]:
        return {
            'statusCode': 400,
            'Error': json.dumps("Teams Available: Lakers, Wizards, Nets, Bruins, Lightning, Islanders, Red Sox, Yankees, Dodgers")
        }

    try:
        response = dynamodb.get_item(TableName="Sports", Key={
                                     'Sport': {'S': sport}, 'Team': {'S': team}})

        return {
            'statusCode': 200,
            'Games': json.dumps(response.get('Item').get('Games').get('SS'))
        }
    except ClientError as e:
        return {
            'statusCode': 400,
            'Error': json.dumps("AWS is still creating the Courses Table, please wait.")
        }


def table_created(dynamodb):
    try:
        table = dynamodb.describe_table(TableName="Sports")
    except dynamodb.exceptions.ResourceNotFoundException:
        table = dynamodb.create_table(
            TableName='Sports',
            KeySchema=[
                {
                    'AttributeName': 'Sport',
                    'KeyType': 'HASH'  # Partition key
                },
                {
                    'AttributeName': 'Team',
                    'KeyType': 'RANGE'  # Partition key
                }
            ],
            AttributeDefinitions=[
                {
                    'AttributeName': 'Sport',
                    'AttributeType': 'S'
                },
                {
                    'AttributeName': 'Team',
                    'AttributeType': 'S'
                }
            ],
            ProvisionedThroughput={
                'ReadCapacityUnits': 20,
                'WriteCapacityUnits': 9
            }
        )

    return True


def is_finished(dynamodb):
    try:
        table = dynamodb.describe_table(TableName="Sports")
        return True
    except dynamodb.exceptions.ResourceNotFoundException:
        return False


def has_items(dynamodb):
    try:
        response = dynamodb.get_item(TableName="Sports", Key={
            'Sport': {'S': "Basketball"}, 'Team': {'S': "Lakers"}})
        if response.get("Item") is None:
            return False
        else:
            return True
    except dynamodb.exceptions.ResourceNotFoundException:
        return False


def fill_table(dynamodb):
    response = dynamodb.batch_write_item(
        RequestItems={
            'Sports': [
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Basketball'},
                            'Team': {'S': 'Lakers'},
                            'Games': {'SS': ["Lakers lose to Wizards 107-116 on Apr 28, 2021", "Lakers beat Magic 114-103 on Apr 26, 2021", "Lakers lose to Mavericks 93-108 on Apr 24, 2021", "Lakers lose to Mavericks 110-115 on Apr 22, 2021"]}
                        }
                    }
                },
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Basketball'},
                            'Team': {'S': 'Nets'},
                            'Games': {'SS': ["Nets beat Raptors 116-103 on Apr 27, 2021", "Nets beat Suns 128-119 on Apr 25, 2021", "Nets beat Celtics 109-104 on Apr 23, 2021", "Nets lose to Raptors 103-114 on Apr 21, 2021"]}
                        }
                    }
                },
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Basketball'},
                            'Team': {'S': 'Wizards'},
                            'Games': {'SS': ["Wizards lose to Mavericks 124-125 on May 1, 2021", "Wizards beat Cavaliers 122-93 on Apr 30, 2021", "Wizards beat Lakers 116-107 on Apr 28, 2021", "Wizards lose to Spurs in OT 143-146 on Apr 26, 2021"]}
                        }
                    }
                },
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Baseball'},
                            'Team': {'S': 'Dodgers'},
                            'Games': {'SS': ["Dodgers lost to Cubs 1-7 on Apr 4, 2021", "Dodgers beat Brewers 16-4 on May 2, 2021", "Dodgers lose to Brewers 5-6 on May 1, 2021", "Dodgers lose to Brewers 1-3 on Apr 30, 2021"]}
                        }
                    }
                },
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Baseball'},
                            'Team': {'S': 'Yankees'},
                            'Games': {'SS': ["Yankees beat Astros on 3-1 on May 4, 2021", "Yankees beat Tigers 2-0 on May 2, 2021", "Yankees beat Tigers 6-4 on May 1, 2021", "Yankees beat Tigers 10-0 on Apr 30, 2021"]}
                        }
                    }
                },
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Baseball'},
                            'Team': {'S': 'Red Sox'},
                            'Games': {'SS': ["Red Sox lose to Rangers 3-5 on May 2, 2021", "Red Sox lose to Rangers 6-8 on May 1, 2021", "Red Sox beat Rangers 6-1 on Apr 30, 2021", "Red Sox lose to Rangers 1-4 on Apr 29, 2021"]}
                        }
                    }
                },
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Hockey'},
                            'Team': {'S': 'Lightning'},
                            'Games': {'SS': ["Lightning beat Red Wings 2-1 on May 2, 2021", "Lightning lose to Red Wings 0-1 on May 1, 2021", "Lightning beat Stars 3-0 on Apr 29, 2021", "Lightning beat Blackhawks 7-4 on Apr 27, 2021"]}
                        }
                    }
                },
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Hockey'},
                            'Team': {'S': 'Bruins'},
                            'Games': {'SS': ["Bruins beat Devils 3-0 on May 3, 2021", "Bruins beat Sabres 6-2 on May 1, 2021", "Bruins beat Sabres 5-2 on Apr 29, 2021", "Bruins beat Penguins 3-1 on Apr 27, 2021"]}
                        }
                    }
                },
                {
                    'PutRequest': {
                        'Item': {
                            'Sport': {'S': 'Hockey'},
                            'Team': {'S': 'Islanders'},
                            'Games': {'SS': ["Islanders beat Rangers 3-0 on May 1, 2021", "Islanders beat Rangers 4-0 on Apr 29, 2021", "Islanders lose to Capitals 0-1 on Apr 27, 2021", "Islanders lose to Capitals 3-6 on Apr 24, 2021"]}
                        }
                    }
                }
            ]
        }
    )


def run(dynamodb):
    if table_created(dynamodb):
        if is_finished(dynamodb):
            if not has_items(dynamodb):
                fill_table(dynamodb)
                run(dynamodb)
        else:
            return {
            'statusCode': 400,
            'Error': json.dumps("AWS is still creating the Courses Table, please wait.")
        }
    else:
        return {
            'statusCode': 400,
            'Error': json.dumps("AWS is still creating the Courses Table, please wait.")
        }
