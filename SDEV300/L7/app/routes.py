from flask import url_for, render_template, flash, redirect, request
from app import app, db
from app.forms import LoginForm, RegistrationForm
from flask_login import current_user, login_user, logout_user, login_required
from app.models import User
from werkzeug.urls import url_parse


@app.route('/')
def home():
    return render_template('index.html')

@login_required
@app.route('/shop')
def shop():
    return render_template('shop.html')

@app.route('/register', methods=['GET', 'POST'])
def register():
    if current_user.is_authenticated:
        return redirect(url_for('index'))
    form = RegistrationForm()
    if form.validate_on_submit():
        user = User(username=form.username.data)
        user.set_password(form.password.data)
        response = db.add_user(user)
        if response["ok"]:
            flash("Account Successfully Created")
        else:
            flash(response["error"])
        return redirect(url_for('login'))
    return render_template('register.html', form=form)

@login_required
@app.route('/news')
def news():
    return render_template('news.html')

@login_required
@app.route('/profile')
def profile():
    return render_template('profile.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    if current_user.is_authenticated:
        return redirect(url_for('index'))

    form = LoginForm()

    if form.validate_on_submit():
        redirect(url_for('shop'))
    
    return render_template('login.html', form=form)

@app.route('/logout')
def logout():
    logout_user()
    return redirect(url_for('index'))