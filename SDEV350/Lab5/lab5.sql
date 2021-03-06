-- CREATE CUSTOMERS TABLE
CREATE TABLE CUSTOMERS (
  CUSTOMERID INTEGER PRIMARY KEY NOT NULL,
  CUSTOMERLASTNAME VARCHAR2(40) NOT NULL,
  CUSTOMERFIRSTNAME VARCHAR2(40) NOT NULL,
  CUSTOMEREMAIL VARCHAR(80) NOT NULL,
  CUSTOMERPHONE VARCHAR2(12),
  CUSTOMERCELLPHONE VARCHAR2(12)
);

-- CREATE SALES2019 TABLE
CREATE TABLE SALES2019 (
  CUSTOMERID INTEGER NOT NULL,
    FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMERS(CUSTOMERID),
  TRANSACTIONDATE DATE NOT NULL,
    PRIMARY KEY (TRANSACTIONDATE, CUSTOMERID),
  SALESAMOUNT NUMBER(10, 2) NOT NULL,
  PROFITAMOUNT NUMBER(10, 2) NOT NULL
);

-- CREATE PROJECTIONS TABLE
CREATE TABLE PROJECTIONS2020 (
  CUSTOMERID INTEGER NOT NULL,
    FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMERS(CUSTOMERID),
  QUARTERLYPURCHASEAMOUNT NUMBER(10, 2) NOT NULL,
  QUARTERLYPROFITAMOUNT NUMBER(10, 2)  NOT NULL,
  CONFIDENCE NUMBER(4, 2) NOT NULL
);


-- INSERT DATA INTO CUSTOMERS TABLE
INSERT ALL
  -- CUSTOMERS
  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERCELLPHONE)
  VALUES(1, 'Samuel', 'Kyra', 'ksamuel@email.com', '5406545325')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL)
  VALUES(2, 'Bolton', 'Dakota', 'dbolton@email.com')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE, CUSTOMERCELLPHONE)
  VALUES(3, 'Middleton', 'Bianca', 'bmiddleton@email.com', '3113413699', '5695324766')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE, CUSTOMERCELLPHONE)
  VALUES(4, 'Macias', 'Laylah', 'lmacias@email.com', '8436891197', '5624428763')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE, CUSTOMERCELLPHONE)
  VALUES(5, 'Ponce', 'Clark', 'cponce@email.com', '7774327754', '7539602077')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE, CUSTOMERCELLPHONE)
  VALUES(6, 'Mcguire', 'Lilia', 'lmcguire@email.com', '4256468955', '8445040298')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERCELLPHONE)
  VALUES(7, 'Friedman', 'Shelby', 'sfriedman@email.com', '6543003294')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL)
  VALUES(8, 'Jamir', 'Lowe', 'jlowe@email.com')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE, CUSTOMERCELLPHONE)
  VALUES(9, 'Fernandez', 'Camila', 'cfernandez@email.com', '2322642558', '5654326790')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE, CUSTOMERCELLPHONE)
  VALUES(10, 'Nicholson', 'Lorelei', 'lnicholson@email.com', '4043819402', '2914026493')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE)
  VALUES(11, 'Diaz', 'Samir', 'sdiaz@email.com', '7257194324')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERCELLPHONE)
  VALUES(12, 'Chambers', 'Jimena', 'jchambers@email.com', '8549350134')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE, CUSTOMERCELLPHONE)
  VALUES(13, 'Best', 'Todd', 'tbest@email.com', '5409424932', '5718429432')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE, CUSTOMERCELLPHONE)
  VALUES(14, 'Fox', 'Jaylynn', 'jfox@email.com', '5404924111', '8043249102')

  INTO CUSTOMERS(CUSTOMERID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMEREMAIL, CUSTOMERPHONE)
  VALUES(15, 'Rice', 'Cynthia', 'ksamuel@email.com', '80343290143')
  
  -- PROJECTIONS2020
  INTO PROJECTIONS2020(CUSTOMERID, QUARTERLYPURCHASEAMOUNT, QUARTERLYPROFITAMOUNT, CONFIDENCE)
  VALUES (1, 3, 94.34, 0.53)

  INTO PROJECTIONS2020(CUSTOMERID, QUARTERLYPURCHASEAMOUNT, QUARTERLYPROFITAMOUNT, CONFIDENCE)
  VALUES (13, 10, 294.67, 0.83)

  INTO PROJECTIONS2020(CUSTOMERID, QUARTERLYPURCHASEAMOUNT, QUARTERLYPROFITAMOUNT, CONFIDENCE)
  VALUES (7, 8, 240.69, 0.93)

  INTO PROJECTIONS2020(CUSTOMERID, QUARTERLYPURCHASEAMOUNT, QUARTERLYPROFITAMOUNT, CONFIDENCE)
  VALUES (4, 5, 188.47, 0.43)

  INTO PROJECTIONS2020(CUSTOMERID, QUARTERLYPURCHASEAMOUNT, QUARTERLYPROFITAMOUNT, CONFIDENCE)
  VALUES (10, 12, 362.72, 0.76)

  -- SALES2019 
  INTO SALES2019(CUSTOMERID, TRANSACTIONDATE, SALESAMOUNT, PROFITAMOUNT)
  VALUES (1, TO_DATE('2019/02/03 09:47:23', 'yyyy/mm/dd hh24:mi:ss'), '23.14', '12.05')

  INTO SALES2019(CUSTOMERID, TRANSACTIONDATE, SALESAMOUNT, PROFITAMOUNT)
  VALUES (3, TO_DATE('2019/01/23 12:30:18', 'yyyy/mm/dd hh24:mi:ss'), '13.54', '8.34')

  INTO SALES2019(CUSTOMERID, TRANSACTIONDATE, SALESAMOUNT, PROFITAMOUNT)
  VALUES (6, TO_DATE('2019/08/17 10:28:50', 'yyyy/mm/dd hh24:mi:ss'), '18.54', '9.15')

  INTO SALES2019(CUSTOMERID, TRANSACTIONDATE, SALESAMOUNT, PROFITAMOUNT)
  VALUES (5, TO_DATE('2019/10/08 02:30:32', 'yyyy/mm/dd hh24:mi:ss'), '4.82', '2.17')

  INTO SALES2019(CUSTOMERID, TRANSACTIONDATE, SALESAMOUNT, PROFITAMOUNT)
  VALUES (10, TO_DATE('2019/08/19 03:26:31', 'yyyy/mm/dd hh24:mi:ss'), '13.54', '7.05')

  INTO SALES2019(CUSTOMERID, TRANSACTIONDATE, SALESAMOUNT, PROFITAMOUNT)
  VALUES (7, TO_DATE('2019/12/21 01:47:42', 'yyyy/mm/dd hh24:mi:ss'), '13.31', '7.15')

  INTO SALES2019(CUSTOMERID, TRANSACTIONDATE, SALESAMOUNT, PROFITAMOUNT)
  VALUES (2, TO_DATE('2019/08/18 05:28:41', 'yyyy/mm/dd hh24:mi:ss'), '6.54', '3.19')

  INTO SALES2019(CUSTOMERID, TRANSACTIONDATE, SALESAMOUNT, PROFITAMOUNT)
  VALUES (8, TO_DATE('2019/10/24 11:16:34', 'yyyy/mm/dd hh24:mi:ss'), '34.87', '16.87')
SELECT 1 FROM DUAL;

-- CREATE PROFILE

CREATE PROFILE PSamuelKyra LIMIT
  PASSWORD_VERIFY_FUNCTION ORA12C_VERIFY_FUNCTION
  SESSIONS_PER_USER 3
  FAILED_LOGIN_ATTEMPTS 4
  PASSWORD_LIFE_TIME 120
  PASSWORD_LOCK_TIME 1/24;

-- CREATE TABLESPACE

CREATE TABLESPACE USERS;


-- CREATE USERS

CREATE USER L5_1KyraSamuel
  IDENTIFIED BY LIGHTBLUE4321$
  DEFAULT TABLESPACE USERS
  QUOTA 30M ON USERS
    TEMPORARY TABLESPACE temp
    PROFILE PSamuelKyra
  ;
  
CREATE USER L5_2KyraSamuel
  IDENTIFIED BY DARKGREEN4321$
  DEFAULT TABLESPACE USERS
  QUOTA 30M ON USERS
    TEMPORARY TABLESPACE temp
    PROFILE PSamuelKyra
  ;

CREATE USER L5_3KyraSamuel
  IDENTIFIED BY DARKBROWN4321$
  DEFAULT TABLESPACE USERS
  QUOTA 30M ON USERS
    TEMPORARY TABLESPACE temp
    PROFILE PSamuelKyra
  ;


-- CREATE ROLE
CREATE ROLE R5KYRASAMUEL;

GRANT CONNECT, CREATE SESSION TO R5KYRASAMUEL;
GRANT SELECT, INSERT, READ, UPDATE, DELETE ON CUSTOMERS TO R5KYRASAMUEL;
GRANT SELECT, INSERT, READ, UPDATE, DELETE ON PROJECTIONS2020 TO R5KYRASAMUEL;
GRANT SELECT, INSERT, READ, UPDATE, DELETE ON SALES2019 TO R5KYRASAMUEL;

GRANT R5KYRASAMUEL TO L5_1KYRASAMUEL;
GRANT R5KYRASAMUEL TO L5_2KYRASAMUEL;
GRANT R5KYRASAMUEL TO L5_3KYRASAMUEL;

