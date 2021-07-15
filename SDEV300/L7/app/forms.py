from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, BooleanField, SubmitField
from wtforms.validators import DataRequired, ValidationError, EqualTo, Length
from app.models import User
import re

class LoginForm(FlaskForm):
    username = StringField('Username', validators=[DataRequired(), Length(min=4)])
    password = PasswordField('Password', validators=[DataRequired()])
    remember_me = BooleanField('Remember Me')
    submit = SubmitField('Login')

class RegistrationForm(FlaskForm):
    username = StringField('Username', validators=[DataRequired()])
    password = PasswordField('Password', validators=[DataRequired(), Length(min=12)])
    c_password = PasswordField(
        'Confirm Password', validators=[DataRequired(), EqualTo('password'), Length(min=12)])
    submit = SubmitField('Register')

    def validate_username(self, username):
        user = User.query.filter_by(username=username.data).first()
        if user is not None:
            raise ValidationError('Username is already in use.')
    
    def validate_password(self, password):
        mixed_case = not password.data.islower() and not password.data.isupper()
        regex = re.compile('[@_!#$%^&*()<>?/\|}{~:]')

        contains_characters = regex.search(password.data) != None

        if not mixed_case:
            raise ValidationError('Password must contain at least 1 uppercase letter and 1 lowercase letter')
        elif not contains_characters:
            raise ValidationError('Password must contain at least one special character')
        elif not any(char.isdigit() for char in password.data):
            raise ValidationError('Password must contain at least one digit')
        
