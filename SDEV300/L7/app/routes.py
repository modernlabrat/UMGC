from flask import url_for, render_template, flash, redirect, request
from app import app, db, logger
from app.forms import LoginForm, RegistrationForm, ResetForm
from flask_login import current_user, login_user, logout_user, login_required
from app.models import User
from datetime import datetime as dt
import socket


@app.route('/')
@app.route('/index')
def home():
    return render_template('index.html')

@app.route('/shop')
@login_required
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

        try:
            db.session.add(user)
            db.session.commit()
            flash("Account Successfully Created")
        except Exception as e:
            flash("Unable to create account. Username may already be in use.", e.message)    
            return redirect(url_for('register'))

        return redirect(url_for('login'))
    return render_template('register.html', form=form)

@app.route('/news')
def news():
    return render_template('news.html')

@app.route('/profile/<username>')
@login_required
def profile(username):
    user = User.query.filter_by(username=username).first_or_404()
    return render_template('profile.html', user=user)

@app.route('/profile/<username>/reset', methods=['GET', 'POST'])
@login_required
def reset(username):
    user = User.query.filter_by(username=username).first_or_404()

    form = ResetForm()

    if form.validate_on_submit():
        user.set_password(form.password.data)
        db.session.commit()
        flash("Password has been successfully reset. Please login")
        return redirect(url_for('logout'))

    return render_template('reset_password.html', user=user, form=form)
@app.route('/login', methods=['GET', 'POST'])
def login():
    if current_user.is_authenticated:
        return redirect(url_for('index'))

    form = LoginForm()

    if form.validate_on_submit():
        user = User.query.filter_by(username=form.username.data).first()

        if user is None or not user.verify_password(form.password.data):
            flash("Username or Password is incorrect.")

            hostname = socket.gethostname()
            local_ip = socket.gethostbyname(hostname)
            
            date_time = "Date and Time: " + dt.now().strftime("%d/%m/%Y %H:%M")
            log = date_time + " IP Address: " + local_ip

            logger.append_log(log)

            return redirect(url_for('login'))
        
        login_user(user, remember=form.remember_me.data)

        next_page = request.args.get('next')

        if not next_page:
            return redirect(url_for('profile', username=form.username.data))

        return redirect(next_page)
    
    return render_template('login.html', form=form)

@app.route('/logout')
def logout():
    logout_user()
    return redirect(url_for('login'))
