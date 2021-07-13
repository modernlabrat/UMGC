from werkzeug.security import check_password_hash, generate_password_hash
from flask import jsonify
from flask_login import UserMixin
from app import login
import random


class User(UserMixin):
    def __init__(self, username):
        self.username = username
        self.password = ''
        self._id = random.randint(0, 100)


    def __repr__(self):
        return '@{}'.format(self.username) 

    def login(self):
        user = db.check_username(self.username)
        return user
        
    def set_password(self, password):
        self.password = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.password, password)

@login.user_loader
def load_user(_id):
    return User.get(_id)