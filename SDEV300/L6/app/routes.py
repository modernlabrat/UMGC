from flask import render_template
from app import app

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/shop')
def shop():
    return render_template('shop.html')

@app.route('/register')
def register():
    return render_template('register.html')