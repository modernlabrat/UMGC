from werkzeug.security import check_password_hash, generate_password_hash
from flask import jsonify
from flask_login import UserMixin
from app import login, db

class User(UserMixin, db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(18), index=True, unique=True)
    password = db.Column(db.String(100))

    def __repr__(self):
        return '@{}'.format(self.username)
        
    def set_password(self, password):
        self.password = generate_password_hash(password)

    def verify_password(self, password):
        return check_password_hash(self.password, password)

@login.user_loader
def load_user(id):
    return User.query.get(int(id))