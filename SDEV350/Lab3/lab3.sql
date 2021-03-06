-- CREATE PROFILE

CREATE PROFILE PSamuelKyra LIMIT
  PASSWORD_VERIFY_FUNCTION ORA12C_VERIFY_FUNCTION
  SESSIONS_PER_USER 3
  FAILED_LOGIN_ATTEMPTS 4
  PASSWORD_LIFE_TIME 120
  PASSWORD_LOCK_TIME 1/24;

SELECT * FROM DBA_PROFILES WHERE PROFILE = 'PSAMUELKYRA';

-- CREATE TABLESPACE

CREATE TABLESPACE USERS;

-- CREATE USERS

CREATE USER U1KyraSamuel
  IDENTIFIED BY U1IIJSDOJF#2
  DEFAULT TABLESPACE USERS
  QUOTA 30M ON USERS
    TEMPORARY TABLESPACE temp
    PROFILE PSamuelKyra
  PASSWORD EXPIRE;
  
CREATE USER U2KyraSamuel
  IDENTIFIED BY U2LKJS45#ADS
  DEFAULT TABLESPACE USERS
  QUOTA 30M ON USERS
    TEMPORARY TABLESPACE temp
    PROFILE PSamuelKyra
  PASSWORD EXPIRE;

-- CREATE ROLE

CREATE ROLE R1KyraSamuel;
GRANT CONNECT, CREATE SESSION, CREATE TABLE
TO R1KyraSamuel;

-- CREATE AND INSERT DATA INTO TABLES

CREATE TABLE User1Data (
  U1ID INTEGER PRIMARY KEY,
  full_name VARCHAR(30), 
  age INTEGER, 
  eye_color VARCHAR(15)
  );

CREATE TABLE USER2DATA (
  U2ID INTEGER PRIMARY KEY,
  FULL_NAME VARCHAR(40),
  FAVORITE_COLOR VARCHAR(14),
  HEIGHT_IN_INCHES NUMERIC
);

INSERT ALL
  INTO User1Data (U1ID, full_name, age, eye_color) 
  VALUES (1, 'tommy lee', 22, 'blue')
  
  INTO User1Data (U1ID, full_name, age, eye_color) 
  VALUES (2, 'joe ryan', 42, 'brown')
  
  INTO User1Data (U1ID, full_name, age, eye_color) 
  VALUES (3, 'westley joan', 28, 'hazel')

  INTO USER2DATA (U2ID, FULL_NAME, FAVORITE_COLOR, HEIGHT_IN_INCHES) 
  VALUES (4, 'weston rich', 'periwinkle', 54.5)
  
  INTO USER2DATA (U2ID, FULL_NAME, FAVORITE_COLOR, HEIGHT_IN_INCHES) 
  VALUES (5, 'forgy pines', 'sunset orange', 61.4)
  
  INTO USER2DATA (U2ID, FULL_NAME, FAVORITE_COLOR, HEIGHT_IN_INCHES) 
  VALUES (6, 'lisa mundo', 'burgundy', 57.8)
SELECT 1 FROM dual;

-- GRANT PERMISSIONS

GRANT R1KYRASAMUEL TO U1KYRASAMUEL;
GRANT R1KYRASAMUEL TO U2KYRASAMUEL;

GRANT SELECT, INSERT ON USER1DATA TO U1KYRASAMUEL;
GRANT SELECT ON USER1DATA TO U2KYRASAMUEL;
GRANT SELECT ON USER2DATA TO U2KYRASAMUEL;

-- CHANGE PASSWORDS DUE TO EXPIRATION

ALTER USER U1KYRASAMUEL
IDENTIFIED BY DARKRED4321#;

ALTER USER U2KYRASAMUEL
IDENTIFIED BY DARKBLUE4321#;

-- LOGGED IN AS U1KYRASAMUEL

CREATE TABLE MY_TABLE (
  MID INTEGER PRIMARY KEY,
  COLOR VARCHAR(20)
  );

-- LOGGED IN AS U2KYRASAMUEL