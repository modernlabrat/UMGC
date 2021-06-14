from PIL import Image

data = {
        "Alabama": {
            "state": "Alabama", "capital": "Montgomery", "population": 4887680, 
            "flowers": ["Camellia", "Oak-Leaf Hydrangea"], 
            "images": ["camellia-flower.jpg", "oak-leaf-hydrangea.jpg"]
        },
        "Alaska": {
            "state": "Alaska", "capital": "Juneau", "population": 735139,
            "flowers": ["Alpine Forget-me-not"],
            "images": ["alphineforgetmenot.jpg"]
        },
        "Arizona": {
            "state": "Arizona", "capital": "Phoenix", "population": 7158020,
            "flowers": ["Saguaro Cactus Blossom"],
            "images": ["saguarocactusblossom.jpg"]
        },
        "Arkansas": {
            "state": "Arkansas", "capital": "Little Rock", "population": 3009730,
            "flowers": ["Apple Blossom"],
            "images": ["appleblossomarkansas.jpg"]
        },
        "California": {
            "state": "California", "capital": "Sacramento", "population": 39461600,
            "flowers": ["California Poppy"],
            "images": ["californiapoppy.jpg"]
        },
        "Colorado": {
            "state": "Colorado", "capital": "Denver", "population": 5691290,
            "flowers": ["Rocky Mountain Columbine"],
            "images": ["coloradocolumbine.jpg"]
        },
        "Connecticut": {
            "state": "Connecticut", "capital": "Hartford", "population": 3571520,
            "flowers": ["Mountain Laurel"],
            "images": ["mountainlaurel.jpg"]
        },
        "Delaware": {
            "state": "Delaware", "capital": "Dover", "population": 965479,
            "flowers": ["Peach Blossom"],
            "images": ["peachblossomspeachflowers.jpg"]
        },
        "Florida": {
            "state": "Florida", "capital": "Tallahassee", "population": 21244300,
            "flowers": ["Orange Blossom", "Coreopsis"],
            "images": ["orangeblossom.jpg", "coreopsis.jpg"]
        },
        "Georgia": {
            "state": "Georgia", "capital": "Atlanta", "population": 10511100,
            "flowers": ["Cherokee Rose", "Native Azalea"],
            "images": ["cherokeerose.jpg", "nativeazalea.jpg"]
        },
        "Hawaii": {
            "state": "Hawaii", "capital": "Honolulu", "population": 1420590,
            "flowers": ["Yellow Pua Aloalo", "'Ohi'a Lehua", "Lokelani", "Pua 'Ilima", "Hinahina", "Pua Kukui"],
            "images": ["yellowpuaaloalo.jpg", "ohialehua.jpg", "lokelani.jpg", "puailima.jpg", "hinahina.jpg", "puakukui.jpg"]
        },
        "Idaho": {
            "state": "Idaho", "capital": "Boise", "population": 1750540,
            "flowers": ["Syringa"],
            "images": ["syringa.jpg"]
        },
        "Illinois": {
            "state": "Illinois", "capital": "Springfield", "population": 12723100,
            "flowers": ["Violet"],
            "images": ["violet.jpg"]
        },
        "Indiana": {
            "state": "Indiana", "capital": "Indianapolis", "population": 6695500,
            "flowers": ["Peony"],
            "images": ["peony.jpg"]
        },        
        "Iowa": {
            "state": "Iowa", "capital": "Des Moines", "population": 3148620,
            "flowers": ["Wild Rose"],
            "images": ["wiserose.jpg"]
        },
        "Kansas": {
            "state": "Kansas", "capital": "Topeka", "population": 2911360,
            "flowers": ["Wild Native Sunflower"],
            "images": ["nativesunflower.jpg"]
        },
        "Kentucky": {
            "state": "Kentucky", "capital": "Frankfort", "population": 4461150,
            "flowers": ["Goldenrod"],
            "images": ["goldenrod.jpg"]
        },
        "Lousiana": {
            "state": "Louisiana", "capital": "Baton Rouge", "population": 4659690,
            "flowers": ["Louisiana Iris", "Magnolia"],
            "images": ["iriswildflower.jpg", "magnolia.jpg"]
        },
        "Maine": {
            "state": "Maine", "capital": "Augusta", "population": 1339060,
            "flowers": ["White Pine Cone and Tassel"],
            "images": ["whitepinemalecones.jpg"]
        },
        "Maryland": {
            "state": "Maryland", "capital": "Annapolis", "population": 6035800,
            "flowers": ["Black-Eyed Susan"],
            "images": ["blackeyedsusan.jpg"]
        },
        "Massachusetts": {
            "state": "Massachusetts", "capital": "Boston", "population": 6882640,
            "flowers": ["Mayflower"],
            "images": ["mayflower.jpg"]
        },
        "Michigan": {
            "state": "Michigan", "capital": "Lansing", "population": 9984070,
            "flowers": ["Dwarf Lake Iris", "Apple Blossom"],
            "images": ["dwarflakeiriswildflower.jpg", "appleblossom.jpg"]
        },
        "Minnesota": {
            "state": "Minnesota", "capital": "St. Paul", "population": 5606250,
            "flowers": ["Pink & White Lady Slipper"],
            "images": ["pinkwhitelady.jpg"]
        },
        "Mississippi": {
            "state": "Mississippi", "capital": "Jackson", "population": 2981020,
            "flowers": ["Magnolia", "Coreopsis"],
            "images": ["magnolia.jpg", "coreopsis.jpg"]
        },
        "Missouri": {
            "state": "Missouri", "capital": "Jefferson City", "population": 6121620,
            "flowers": ["White Hawthorn Blossom"],
            "images": ["whitehawthornblossom.jpg"]
        },
        "Montana": {
            "state": "Montana", "capital": "Helena", "population": 1060660,
            "flowers": ["Bitterroot"],
            "images": ["bitterroot.jpg"]
        },
        "Nebraska": {
            "state": "Nebraska", "capital": "Lincoln", "population": 1925610,
            "flowers": ["Goldenrod"],
            "images": ["nebraskagoldenrod.jpg"]
        },
        "Nevada": {
            "state": "Nevada", "capital": "Carson City", "population": 3027340,
            "flowers": ["Sagebrush"],
            "images": ["sagebrush.jpg"]
        },
        "New Hampshire": {
            "state": "New Hampshire", "capital": "Concord", "population": 1353460,
            "flowers": ["Pink Lady's Slipper"],
            "images": ["pinkladyslipper.jpg"]
        },
        "New Jersey": {
            "state": "New Jersey", "capital": "Trenton", "population": 8886020,
            "flowers": ["Violet"],
            "images": ["njviolet.jpg"]
        },
        "New Mexico": {
            "state": "New Mexico", "capital": "Santa Fe", "population": 2092740,
            "flowers": ["Yucca"],
            "images": ["yucca.jpg"]
        },
        "New York": {
            "state": "New York", "capital": "Albany", "population": 19530400,
            "flowers": ["Rose"],
            "images": ["rose.jpg"]
        },
        "North Carolina": {
            "state": "North Carolina", "capital": "Raleigh", "population": 10381600,
            "flowers": ["Dogwood", "Carolina Lily"],
            "images": ["dogwood.JPG", "carolinalily.jpg"]
        },
        "North Dakota": {
            "state": "North Dakota", "capital": "Bismarck", "population": 758080,
            "flowers": ["Wild Prairie Rose"],
            "images": ["wildprairierose.jpg"]
        },
        "Ohio": {
            "state": "Ohio", "capital": "Columbus", "population": 11676300,
            "flowers": ["Scarlet Carnation", "Large White Trillium"],
            "images": ["scarlet.jpg", "whitetrillium.jpg"]
        },
        "Oklahoma": {
            "state": "Oklahoma", "capital": "Oklahoma City", "population": 3940240,
            "flowers": ["Oklahoma Rose", "Mistletoe"],
            "images": ["oklahomarose.jpg", "mistletoe.jpg"]
        },
        "Oregon": {
            "state": "Oregon", "capital": "Salem", "population": 4181890,
            "flowers": ["Oregon Grape"],
            "images": ["oregongrape.jpg"]
        },
        "Pennsylvania": {
            "state": "Pennsylvania", "capital": "Harrisburg", "population": 12800900,
            "flowers": ["Mountain Laurel", "Penngift Crown Vetch"],
            "images": ["pennymountainlaurel.jpg", "penngift.jpg"]
        },
        "Rhode Island": {
            "state": "Rhode Island", "capital": "Providence", "population": 1058290,
            "flowers": ["Violet"],
            "images": ["violet.jpg"]
        },
        "South Carolina": {
            "state": "South Carolina", "capital": "Columbia", "population": 5084160,
            "flowers": ["Yellow Jessamine", "Goldenrod"],
            "images": ["yellowjessamine.jpg", "goldenrod.jpg"]
        },
        "South Dakota": {
            "state": "South Dakota", "capital": "Pierre", "population": 878698,
            "flowers": ["Pasque Flower"],
            "images": ["pasque.jpg"]
        },
        "Tennessee": {
            "state": "Tennessee", "capital": "Nashville", "population": 6771630,
            "flowers": ["Iris", "Purple Passionflower", "Tennesee Purple Coneflower"],
            "images": ["iris.jpg", "purplepassion.jpg", "purpleconeflower.jpg"]
        },
        "Texas": {
            "state": "Texas", "capital": "Austin", "population": 28628700,
            "flowers": ["Bluebonnet"],
            "images": ["blueblonnet.jpg"]
        },
        "Utah": {
            "state": "Utah", "capital": "Salt Lake City", "population": 3153550,
            "flowers": ["Sego lily"],
            "images": ["segolily.jpg"]
        },
        "Vermont": {
            "state": "Vermont", "capital": "Montpelier", "population": 624358,
            "flowers": ["Red Clover"],
            "images": ["redclover.jpg"]
        },
        "Virginia": {
            "state": "Virginia", "capital": "Richmond", "population": 8501290,
            "flowers": ["American Dogwood"],
            "images": ["americandogwood.jpg"]
        },
        "Washington": {
            "state": "Washington", "capital": "Olympia", "population": 7523870,
            "flowers": ["Coast Rhododendron"],
            "images": ["coastrhododendron.jpg"]
        },
        "West Virginia": {
            "state": "West Virginia", "capital": "Charleston", "population": 1804290,
            "flowers": ["Rhododendron"],
            "images": ["rhododendron.jpg"]
        },
        "Wisconsin": {
            "state": "Wisconsin", "capital": "Madison", "population": 5807410,
            "flowers": ["Wood Violet"],
            "images": ["woodviolet.jpg"]
        },
        "Wyoming": {
            "state": "Wyoming", "capital": "Cheyenne", "population": 577601,
            "flowers": ["Indian Paintbrush"],
            "images": ["ipaintbrush.jpg"]
        }
    }


def get_data():
    global data

    return data

def update_state(state, field, value):
    """ Updates a State

    Args:
        state (string): state to update
        field (string): field to update
        value (string): value of the field
    """
    global data

    data[state][field] = value

def print_data():
    """ Prints all of the data in the data dictionary
    """
    global data

    for key, value in data.items():
        print("\n" + key + ":\n\tCapital:", value["capital"], end="\n\t")
        print("Population:", value["population"], end="\n\t")
        print("Flowers: ", end=" ")
        for i, item in enumerate(value["flowers"]):
            if i:
                print(',', end=" ")
            print(item, end="")

def print_state(current_state):
    """ Prints the state's capital, state, population, and flowers.

    Args:
        current_state (string): The state given by the user.
    """
    global data

    state = data[current_state]

    print("\n" + state["state"] + ":\n\tCapital:", state["capital"], end="\n\t")
    print("Population:", state["population"], end="\n\t")
    print("Flowers: ", end=" ")

    for i, item in enumerate(state["flowers"]):
        if i:
            print(',', end=" ")
        print(item, end="")

def show_state_flowers(selected_state):
    """ Displays a picture of the flowers in the user's browser

    Args:
        state (string): A valid US state 
    """
    global data
    print("\n\nDisplaying flower(s)...\n")
    for flower in data[selected_state]["images"]:
        path = "./flowers/" + flower
        img = Image.open(path)
        img.show()


