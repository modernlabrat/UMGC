CREATE TABLE Users(
    username VARCHAR(15) NOT NULL PRIMARY KEY,
    password VARCHAR(15) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    credit_card VARCHAR(20) NOT NULL, 
);

INSERT INTO Users (username, password, full_name, credit_card)
VALUES
    ('ksamuel', 'wifipassword0', 'Kyra Samuel', '3217-2212-2123-2123'),
    ('tsaunders', 'forty2018!!', 'Tony Saunders', '3122-3223-8982-9321'),
    ('rarmstrong', 'admin!99@#', 'Rae Armstrong', '1390-3218-8932-3213');  

SELECT * FROM Users WHERE username = 'ksamuel';

SELECT * FROM Users WHERE username = 'ksamuel' OR 4=4;

