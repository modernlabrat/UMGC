from flask import Flask
from config import Config
from flask_login import LoginManager
from database import UserDatabase


app = Flask(__name__)
app.config.from_object(Config)

login = LoginManager(app)
login.login_view = 'login'
db = UserDatabase()

from app import routes