import random

class UserDatabase():
    database = []

    @classmethod
    def add_user(cls, new_user):
        response = {
            "ok": True,
            "error": None
        }

        for user in cls.database:
            if new_user._id == user._id:
                new_user._id = random.randint(0, 100)
                return cls.add_user(new_user)
            
        cls.database.append(new_user)
        return response
    
    def check_username(cls, username):
        for user in cls.database:
            if username == user.username:
                return user
        
        return None