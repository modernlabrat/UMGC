/*
Kyra Samuel
CMIS 320
Project 3 
03/30/2021
*/

/*
# Create Tables
*/

CREATE TABLE Castmates(
    castmate_id VARCHAR(15) NOT NULL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    gender VARCHAR(15), 
);

CREATE TABLE Genres(
    genre_name VARCHAR(100) NOT NULL UNIQUE,
    discount int
);

CREATE TABLE Movies(
    movie_id VARCHAR(15) NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    rating float,
    release_year NUMERIC(4,0) NOT NULL,
);

CREATE TABLE Involvements(
    castmate VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Castmates(castmate_id),
    movie VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Movies(movie_id),
    castmate_type VARCHAR(25) NOT NULL,
    character_name VARCHAR(255),
);

CREATE TABLE Distributors(
    distributor_id VARCHAR(15) NOT NULL PRIMARY KEY,
    company VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Videos(
    video_id VARCHAR(15) NOT NULL PRIMARY KEY,
    movie VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Movies(movie_id),
    quantity INT NOT NULL,
    distributor_sn VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Distributors(distributor_id),
    distributor_movie_id VARCHAR(100) NOT NULL,
    running_length int NOT NULL,
    price FLOAT NOT NULL,
    rented_count INT NOT NULL,
    genre VARCHAR(100) NOT NULL REFERENCES Genres(genre_name),
    UNIQUE(distributor_movie_id)
);

CREATE TABLE DVDs(
    dvd_id VARCHAR(15) NOT NULL PRIMARY KEY,
    movie VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Movies(movie_id),
    quantity INT NOT NULL,
    distributor_sn VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Distributors(distributor_id),
    distributor_movie_id VARCHAR(100) NOT NULL,
    running_length int NOT NULL,
    price FLOAT NOT NULL,
    rented_count INT NOT NULL,
    genre VARCHAR(100) NOT NULL REFERENCES Genres(genre_name),
    UNIQUE(distributor_movie_id)
);

CREATE TABLE Awards(
    award_name VARCHAR(100) NOT NULL,
    movie VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Movies(movie_id),
    castmate VARCHAR(15) FOREIGN KEY REFERENCES Castmates(castmate_id),
    year int NOT NULL,
)

CREATE TABLE Customers(
    customer_id VARCHAR(15) NOT NULL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    res_address VARCHAR(100) NOT NULL,
    UNIQUE(phone_number, email)
);

CREATE TABLE VideoInvoices(
    video_invoice_id VARCHAR(15) NOT NULL PRIMARY KEY,
    customer VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Customers(customer_id),
    video  VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Videos(video_id),
    rented_on DATETIME NOT NULL,
    return_status BIT NOT NULL,
    returned_on DATETIME
);

CREATE TABLE DVDInvoices(
    dvd_invoice_id VARCHAR(15) NOT NULL PRIMARY KEY,
    customer VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Customers(customer_id),
    dvd  VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES DVDs(dvd_id),
    rented_on DATETIME NOT NULL,
    return_status BIT NOT NULL,
    returned_on DATETIME
);

CREATE TABLE VideoCharges(
    video_charge_id VARCHAR(15) NOT NULL PRIMARY KEY,
    video_invoice VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES VideoInvoices(video_invoice_id),
    charge_type int NOT NULL,
    charge_fee float NOT NULL,
    customer VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Customers(customer_id),
    charge_date DATETIME NOT NULL,
    tax float,
    paid_status BIT NOT NULL
)

CREATE TABLE DVDCharges(
    dvd_charge_id VARCHAR(15) NOT NULL PRIMARY KEY,
    dvd_invoice VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES DVDInvoices(dvd_invoice_id),
    charge_type int NOT NULL,
    charge_fee float NOT NULL,
    customer VARCHAR(15) NOT NULL FOREIGN KEY REFERENCES Customers(customer_id),
    charge_date DATETIME NOT NULL,
    tax float,
    paid_status BIT NOT NULL
)

/*
# Fill Tables
*/

INSERT INTO Distributors(distributor_id, company)
VALUES
('2YHZSEANXC', 'Film Gateway'),
('H5OD2LA5V6', 'Katies Stores'),
('7PCF6PBU5S', 'Premier Wholesale Distributor'),
('OUZ2OF35HA', 'Garden State Movies'),
('RYLIRUBDU8', 'DVD Wholesale'),
('Q4PXHQZGXQ', 'Seller Central'),
('EN7UYJNWGC', 'Wholesale Discs');

INSERT INTO Genres(genre_name, discount)
VALUES 
('Action', 10),
('Comedy', null),
('Horror', 5),
('Thriller', null),
('Documentary', null),
('Crime', null),
('Romance', 10),
('Science Fiction', null),
('Musical', null);

INSERT INTO Movies(movie_id, title, rating, release_year)
VALUES
('GSX70RS35N', 'The Wolf on Wall Street', 8.2, 2013),
('EKA52BBRAT', 'Toy Story 4', 7.8, 2019),
('3HBUM8HQ9R', 'Ma', 5.6, 2019),
('USEY0ELAWO', 'Gone Girl', 8.1, 2014),
('BTFNB6PPJD', 'Avengers: Infinity War', 8.4, 2018),
('E542HUYXFP', '12 Years a Slave', 8.1, 2013),
('1GI1MHOO2B', 'Parasite', 8.6, 2019),
('7SCUNEBMUH', 'Get Out', 7.7, 2017),
('B8AKYRZZUT', 'Us', 6.8, 2019),
('NX16Y8A736', 'Argo', 7.7, 2012);

INSERT INTO Castmates( castmate_id, full_name, gender )
VALUES 
('LKRVEPT78P', 'Leonardo DiCaprio', 'male'),
('U9V5N9QYY9', 'Margot Robbie', 'female'),
('ICJSICD9CI', 'Tyler Perry', 'male'),
('BLVB5ZIT3G', 'Damien Chazelle', 'male'),
('3JB4GESGB2', 'Alejandro G. Iñárritu', 'male'),
('6K0EPA940X', 'Danny Boyle', 'male'),
('SBNGFZN1TV', 'Woody Allen', 'male'),
('FVMYAKBLE1', 'Joel Coen', 'male'),
('0TWYSTBNRR', 'Ethan Coen', 'male'),
('NMX3OEDIAG', 'Viola Davis', 'female'),
('IRHAJMYLJ8', 'Meryl Streep', 'female'),
('7AFVLS794J', 'Katharine Hepburn', 'female'),
('DHWZON2KW4', 'Samuel L. Jackson', 'male'),
('Q2Z4RUT2TX', 'Will Smith', 'male'),
('89QCKP81ZI','Jonah Hill', 'male'),
('UG36L3FSHZ','Martin Scorsese', 'male'),
('E6MRQJLL8S','Jordan Peele', 'male'),
('3P8B5RTU99', 'Lupita Nyong''o', 'female'),
('8G0O4OD530', 'Gabe Wilson', 'male'),
('32KQJTDQGE', 'Cho Yeo-jeong', 'female'),
('W4QQ31IYYM', 'Park So-dam Ki-jung', 'female'),
('A8B0PY6SJ6', 'Woo-sik Choi Ki-woo', 'male'),
('FMPV7EVXAS', 'Bong Joon-ho', 'male'),
('6KEU7CNOSR', 'Daniel Kaluuya', 'male'),
('KHXOZ2JCVP', 'Allison Williams', 'female'),
('AIB3LEHYJ1', 'Tom Hanks', 'male'),
('SFQVKMAODX', 'Tim Allen', 'male'),
('X940HB7SGP', 'Keanu Reeves', 'male'),
('OYDCWN89H7', 'Alan Arkin', 'male'),
('UGI0VSRUOX', 'Ben Affleck', 'male'),
('E37RNFR3OO', 'Bryan Cranston', 'male'),
('XYTVBY9QMG', 'Scarlett Johansson', 'female'),
('VBZR6WRILY', 'Robert Downey Jr.', 'male'),
('9I7KZUCGNG', 'Chris Evans', 'male'),
('UV4T7FDA6O', 'Chris Hemsworth', 'male'),
('VJT4P3O8AK', 'Mark Ruffalo', 'male'),
('62P01JU4ZA', 'Morgan Freeman', 'male'),
('E2DGPBHDCJ', 'Elizabeth Olsen', 'female'),
('AUK7VCMRLS', 'Channing Tatum', 'male'),
('29YHE71EMR', 'Stan Lee', 'male'),
('5K15CUM8YW', 'Anthony Russo', 'male'),
('C611O33MOC', 'Joe Russo', 'male'),
('AQ8HPC5N4I', 'Joss Whedon', 'male'),
('XOM56IGXWZ', 'Rosamund Pike', 'female'),
('2NTR05RF6K', 'David Fincher', 'female'),
('MTT35T2BM9', 'Chiwetel Ejiofor', 'male'),
('92RM08NNMP', 'Michael Fassbender', 'male'),
('S9YC7O8ZLN', 'Brad Pitt', 'male'),
('828ZXEA8WC', 'Steve McQueen', 'male');

INSERT INTO Videos ( video_id, movie, quantity, distributor_sn,
    distributor_movie_id, running_length, price,
    rented_count, genre)
VALUES
('CWIUWZCWCF', (SELECT movie_id FROM Movies WHERE movie_id='GSX70RS35N'), 40, (SELECT distributor_id FROM Distributors WHERE distributor_id='Q4PXHQZGXQ'), 'ZbsbbAHBU1cs', 190, 3.99, 6, (SELECT genre_name FROM Genres WHERE genre_name='Comedy')),
('X4FFBEOQPV', (SELECT movie_id FROM Movies WHERE movie_id='GSX70RS35N'), 25, (SELECT distributor_id FROM Distributors WHERE distributor_id='7PCF6PBU5S'), 'ncKq5TMEythR', 185, 2.99, 10, (SELECT genre_name FROM Genres WHERE genre_name='Comedy')),
('84QITDHT21', (SELECT movie_id FROM Movies WHERE movie_id='7SCUNEBMUH'), 35, (SELECT distributor_id FROM Distributors WHERE distributor_id='H5OD2LA5V6'), 'BbKShA8dLRWw', 130, 3.55, 4, (SELECT genre_name FROM Genres WHERE genre_name='Horror')),
('I7H9SH7MDE', (SELECT movie_id FROM Movies WHERE movie_id='B8AKYRZZUT'), 15, (SELECT distributor_id FROM Distributors WHERE distributor_id='EN7UYJNWGC'), '19CyymFYjOLt', 125, 3.90, 5, (SELECT genre_name FROM Genres WHERE genre_name='Horror')),
('YHSNKU4OUD', (SELECT movie_id FROM Movies WHERE movie_id='7SCUNEBMUH'), 35, (SELECT distributor_id FROM Distributors WHERE distributor_id='H5OD2LA5V6'), 'j7JP3Z49KQeq', 128, 2.40, 7, (SELECT genre_name FROM Genres WHERE genre_name='Horror')),
('P2898R2LD8', (SELECT movie_id FROM Movies WHERE movie_id='E542HUYXFP'), 45, (SELECT distributor_id FROM Distributors WHERE distributor_id='RYLIRUBDU8'), 'yWK9FvNzBxFn', 184, 2.50, 13, (SELECT genre_name FROM Genres WHERE genre_name='Drama')), 
('M7ELA9GCZL', (SELECT movie_id FROM Movies WHERE movie_id='NX16Y8A736'), 30, (SELECT distributor_id FROM Distributors WHERE distributor_id='Q4PXHQZGXQ'), '7zLmedj0c31u', 134, 3.30, 7, (SELECT genre_name FROM Genres WHERE genre_name='Drama')),
('VKBF5B23RM', (SELECT movie_id FROM Movies WHERE movie_id='BTFNB6PPJD'), 25, (SELECT distributor_id FROM Distributors WHERE distributor_id='EN7UYJNWGC'), 'IJGu8oOZLCds', 190, 2.99, 11, (SELECT genre_name FROM Genres WHERE genre_name='Action'));


INSERT INTO DVDs ( dvd_id, movie, quantity, distributor_sn,
    distributor_movie_id, running_length, price,
    rented_count, genre)
VALUES
('CWIUWZCWCF', (SELECT movie_id FROM Movies WHERE movie_id='USEY0ELAWO'), 10, (SELECT distributor_id FROM Distributors WHERE distributor_id='2YHZSEANXC'), 'ZbsbbAHBU1cs', 172, 4.99, 12, (SELECT genre_name FROM Genres WHERE genre_name='Mystery')),
('X4FFBEOQPV', (SELECT movie_id FROM Movies WHERE movie_id='GSX70RS35N'), 25, (SELECT distributor_id FROM Distributors WHERE distributor_id='7PCF6PBU5S'), 'ncKq5TMEythR', 100, 3.99, 14, (SELECT genre_name FROM Genres WHERE genre_name='Thriller')),
('84QITDHT21', (SELECT movie_id FROM Movies WHERE movie_id='3HBUM8HQ9R'), 25, (SELECT distributor_id FROM Distributors WHERE distributor_id='H5OD2LA5V6'), 'BbKShA8dLRWw', 130, 4.75, 11, (SELECT genre_name FROM Genres WHERE genre_name='Horror')),
('I7H9SH7MDE', (SELECT movie_id FROM Movies WHERE movie_id='1GI1MHOO2B'), 5, (SELECT distributor_id FROM Distributors WHERE distributor_id='OUZ2OF35HA'), '19CyymFYjOLt', 135, 4.90, 12, (SELECT genre_name FROM Genres WHERE genre_name='Dark Comedy')),
('YHSNKU4OUD', (SELECT movie_id FROM Movies WHERE movie_id='USEY0ELAWO'), 25, (SELECT distributor_id FROM Distributors WHERE distributor_id='H5OD2LA5V6'), 'j7JP3Z49KQeq', 168, 3.85, 3, (SELECT genre_name FROM Genres WHERE genre_name='Mystery')),
('P2898R2LD8', (SELECT movie_id FROM Movies WHERE movie_id='E542HUYXFP'), 20, (SELECT distributor_id FROM Distributors WHERE distributor_id='RYLIRUBDU8'), 'yWK9FvNzBxFn', 184, 3.80, 13, (SELECT genre_name FROM Genres WHERE genre_name='Drama')), 
('M7ELA9GCZL', (SELECT movie_id FROM Movies WHERE movie_id='NX16Y8A736'), 10, (SELECT distributor_id FROM Distributors WHERE distributor_id='OUZ2OF35HA'), '7zLmedj0c31u', 134, 4.60, 8, (SELECT genre_name FROM Genres WHERE genre_name='Drama')),
('VKBF5B23RM', (SELECT movie_id FROM Movies WHERE movie_id='BTFNB6PPJD'), 15, (SELECT distributor_id FROM Distributors WHERE distributor_id='EN7UYJNWGC'), 'IJGu8oOZLCds', 190, 3.89, 15, (SELECT genre_name FROM Genres WHERE genre_name='Action'));

INSERT INTO Involvements(castmate, movie, castmate_type, character_name)
VALUES
((SELECT castmate_id FROM Castmates WHERE castmate_id='3P8B5RTU99'),(SELECT movie_id FROM Movies WHERE movie_id='E542HUYXFP'),'actress','Patsey'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='3P8B5RTU99'),(SELECT movie_id FROM Movies WHERE movie_id='B8AKYRZZUT'),'actress','Red'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='UGI0VSRUOX'),(SELECT movie_id FROM Movies WHERE movie_id='USEY0ELAWO'),'actor','Nick Dunne'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='XOM56IGXWZ'),(SELECT movie_id FROM Movies WHERE movie_id='USEY0ELAWO'),'actress','Amy Dunne'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='UGI0VSRUOX'),(SELECT movie_id FROM Movies WHERE movie_id='USEY0ELAWO'),'director', null),
((SELECT castmate_id FROM Castmates WHERE castmate_id='UGI0VSRUOX'),(SELECT movie_id FROM Movies WHERE movie_id='NX16Y8A736'),'actor','Tony Mendez'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='UGI0VSRUOX'),(SELECT movie_id FROM Movies WHERE movie_id='NX16Y8A736'),'director',null),
((SELECT castmate_id FROM Castmates WHERE castmate_id='2NTR05RF6K'),(SELECT movie_id FROM Movies WHERE movie_id='USEY0ELAWO'),'director', null),
((SELECT castmate_id FROM Castmates WHERE castmate_id='ICJSICD9CI'),(SELECT movie_id FROM Movies WHERE movie_id='USEY0ELAWO'),'director', null),
((SELECT castmate_id FROM Castmates WHERE castmate_id='XYTVBY9QMG'),(SELECT movie_id FROM Movies WHERE movie_id='BTFNB6PPJD'),'actress', 'Black Widow'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='VBZR6WRILY'),(SELECT movie_id FROM Movies WHERE movie_id='BTFNB6PPJD'),'actor', 'Iron Man'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='9I7KZUCGNG'),(SELECT movie_id FROM Movies WHERE movie_id='BTFNB6PPJD'),'actor', 'Captain America'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='UV4T7FDA6O'),(SELECT movie_id FROM Movies WHERE movie_id='BTFNB6PPJD'),'actor', 'Thor, Thor'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='VJT4P3O8AK'),(SELECT movie_id FROM Movies WHERE movie_id='BTFNB6PPJD'),'actor', 'Hulk'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='DHWZON2KW4'),(SELECT movie_id FROM Movies WHERE movie_id='BTFNB6PPJD'),'actor', 'Nick Fury'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='U9V5N9QYY9'),(SELECT movie_id FROM Movies WHERE movie_id='GSX70RS35N'),'actor', 'Jordan Belfort'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='89QCKP81ZI'),(SELECT movie_id FROM Movies WHERE movie_id='GSX70RS35N'),'actor', 'Donnie Azoff'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='E2DGPBHDCJ'),(SELECT movie_id FROM Movies WHERE movie_id='GSX70RS35N'),'actress', 'Naomi Lapaglia'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='UG36L3FSHZ'),(SELECT movie_id FROM Movies WHERE movie_id='GSX70RS35N'),'director', null),
((SELECT castmate_id FROM Castmates WHERE castmate_id='AIB3LEHYJ1'),(SELECT movie_id FROM Movies WHERE movie_id='EKA52BBRAT'),'voice actor', 'Sheriff Woody'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='SFQVKMAODX'),(SELECT movie_id FROM Movies WHERE movie_id='EKA52BBRAT'),'voice actor', 'Buzz Lightyear'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='X940HB7SGP'),(SELECT movie_id FROM Movies WHERE movie_id='EKA52BBRAT'),'voice actor', 'Duke Cabroom'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='TBHG1S1HMJ'),(SELECT movie_id FROM Movies WHERE movie_id='3HBUM8HQ9R'),'actress', 'Sue Ann'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='RX9P4QN148'),(SELECT movie_id FROM Movies WHERE movie_id='3HBUM8HQ9R'),'actress', 'Maggie'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='WH43GZI5QA'),(SELECT movie_id FROM Movies WHERE movie_id='3HBUM8HQ9R'),'actor', 'Andy Hawkins'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='XD4VXVVWBI'),(SELECT movie_id FROM Movies WHERE movie_id='3HBUM8HQ9R'),'director', null),
((SELECT castmate_id FROM Castmates WHERE castmate_id='32KQJTDQGE'),(SELECT movie_id FROM Movies WHERE movie_id='1GI1MHOO2B'),'actress', 'Yeon-kyo'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='W4QQ31IYYM'),(SELECT movie_id FROM Movies WHERE movie_id='1GI1MHOO2B'),'actress', 'Ki-jung'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='A8B0PY6SJ6'),(SELECT movie_id FROM Movies WHERE movie_id='1GI1MHOO2B'),'actor', 'Ki-woo'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='FMPV7EVXAS'),(SELECT movie_id FROM Movies WHERE movie_id='1GI1MHOO2B'),'director', null),
((SELECT castmate_id FROM Castmates WHERE castmate_id='6KEU7CNOSR'),(SELECT movie_id FROM Movies WHERE movie_id='7SCUNEBMUH'),'actor', 'Chris Washington'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='KHXOZ2JCVP'),(SELECT movie_id FROM Movies WHERE movie_id='7SCUNEBMUH'),'actress', 'Rose Armitage'),
((SELECT castmate_id FROM Castmates WHERE castmate_id='E6MRQJLL8S'),(SELECT movie_id FROM Movies WHERE movie_id='7SCUNEBMUH'),'director', null),
((SELECT castmate_id FROM Castmates WHERE castmate_id='E6MRQJLL8S'),(SELECT movie_id FROM Movies WHERE movie_id='B8AKYRZZUT'),'director', null);

INSERT INTO Customers(customer_id, full_name, phone_number, email, res_address)
VALUES
('Q5C5EEHW5K', 'Joie Mentzer', '5404346563', 'jmentzer@gmail.com', '123 sally lane 23451'),
('K9387LGYC0', 'Lola Holeman', '2025456554', 'lholeman@outlook.com', '123 sally lane 23451'),
('SHE6L4PI5H', 'McKenzie Tincher', '2024765435', 'mtincher@gmail.com', '123 sally lane 23451'),
('NSOAVFDPRR', 'Cathryn Folts', '7574878683', 'cfoltz@gmail.com', '123 sally lane 23451'),
('JKRYR1C68D', 'Fred Johnson', '5714765554', 'fjohnson@yahoo.com', '123 sally lane 23451'),
('L56ZMK77JJ', 'Carly Davies', '5408776757', 'cdavies@aol.com', '123 sally lane 23451'),
('2267Y1N1D5', 'Martin Roger', '5712535435', 'mroger@outlook.com', '123 sally lane 23451'),
('LK0KE5XYR0', 'Gianna Mendez', '5718768767', 'gmendez@gmail.com', '123 sally lane 23451'),
('LZL160YHEA', 'Frankie Wilson', '5716542532', 'fwilson@yahoo.com', '123 sally lane 23451'),
('1R4SUICA6R', 'Percy Smith', '2024899904', 'psmith@gmail.com', '123 sally lane 23451'),
('KJSM4Q3BNY', 'Dianna Washington', '2027657764', 'dwashington@gmail.com', '123 sally lane 23451'),
('CSQ3GYHY5H', 'Forgy Armstrong', '2026540963', 'farmstrong@aol.com', '123 sally lane 23451'),
('QU5OQ64KWK', 'Kyra Samuel', '5404325455', 'ksamuel@gmail.com', '123 sally lane 23451'),
('3ER91F9TZ7', 'Gregory Kay', '7577657856', 'gkay@yahoo.com', '123 sally lane 23451'),
('KG0TVUDHFM', 'Mason Turner', '7576346352', 'mturner@aol.com', '123 sally lane 23451'),
('MKFC4E0DFC', 'Luke Matthews', '7570432427', 'lmatthews@yahoo.com', '123 sally lane 23451'),
('75W4OKI1P2', 'Kaiden Choi', '7576546904', 'kchoi@aol.com', '123 sally lane 23451'),
('BHDWB3OC9S', 'Nasir Rodrigues', '5404346560', 'nrodrigues@gmail.com', '123 sally lane 23451'),
('37K7Y6OKHB', 'Oluwasum Fields', '5715436739', 'ofields@outlook.com', '123 sally lane 23451'),
('TCYIE5YYHH', 'Bobby Glover', '7574543542', 'bglover@aol.com', '123 sally lane 23451'),
('342QB52XB6', 'Vivian Richards', '2020645646', 'vrichards@outlook.com', '123 sally lane 23451'),
('KDRA2FHNHU', 'Kristian Browns', '5408365466', 'kbrowns@gmail.com', '123 sally lane 23451'),
('4KJ06O2QMD', 'Pok Higgins', '5409069906', 'phiggins@yahoo.com', '123 sally lane 23451');

INSERT INTO Awards(award_name, movie, castmate, year)
VALUES
('Best Motion Picture of the Year', (SELECT movie_id FROM Movies WHERE movie_id='NX16Y8A736'), (SELECT castmate_id FROM Castmates WHERE castmate_id='UGI0VSRUOX'), 2013),
('Best Motion Picture of the Year', (SELECT movie_id FROM Movies WHERE movie_id='E542HUYXFP'), (SELECT castmate_id FROM Castmates WHERE castmate_id='S9YC7O8ZLN'), 2014),
('Best Supporting Actress', (SELECT movie_id FROM Movies WHERE movie_id='E542HUYXFP'), (SELECT castmate_id FROM Castmates WHERE castmate_id='3P8B5RTU99'), 2014),
('Best Director of the Year', (SELECT movie_id FROM Movies WHERE movie_id='1GI1MHOO2B'), (SELECT castmate_id FROM Castmates WHERE castmate_id='FMPV7EVXAS'), 2020),
('Best Motion Picture of the Year', (SELECT movie_id FROM Movies WHERE movie_id='E542HUYXFP'), (SELECT castmate_id FROM Castmates WHERE castmate_id='S9YC7O8ZLN'), 2014);

INSERT INTO VideoCharges(video_charge_id, video_invoice, charge_type, charge_fee, customer, charge_date, tax, paid_status)
VALUES
('3VSOIHbsaCSZc', 'JIOFJOI4123FD', 1, '4.99', 'L56ZMK77JJ', '03/07/2020', null, 0),
('ldSTSNK861yfL', 'uhHUHI83LJOIF', 0, '15.99', 'BHDWB3OC9S', '02/28/2021', '6.99', 1),
('lbif9312kbfe3', 'nfININD321765', 2, '20.99', 'KJSM4Q3BNY', '07/25/2020', '5.99', 0),
('HBLIBAF420804', 'HIfewabo07843', 2, '20.99', 'LZL160YHEA', '08/26/2020', '5.99', 1),
('knIOHnlkfaIJ0', 'Klao0O21Nnhsa', 1, '4.99', '342QB52XB6', '03/07/2020', null, 1);

-- charge type:  'Damage - 0, 'Failure to Rewind - 1, Failure to Return 2
INSERT INTO DVDCharges(dvd_charge_id, dvd_invoice, charge_type, charge_fee, customer, charge_date, tax, paid_status)
VALUES
('pnlbnOUBBJKNS', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='LXasE2BN04543'), 0, '15.99', (SELECT customer_id FROM Customers WHERE customer_id='3ER91F9TZ7'), '09/28/2019', null, 0),
('n3PJOPOINFAFE', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='4932u90843fsS'), 0, '15.99', (SELECT customer_id FROM Customers WHERE customer_id='BHDWB3OC9S'), '04/28/2020', '8.99', 1),
('biubOINANKDLA', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='obgare943523g'), 2, '25.99', (SELECT customer_id FROM Customers WHERE customer_id='2267Y1N1D5'), '03/14/2020', '5.99', 1),
('808893okjhUHU', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='ks34231N4DSAS'), 0, '15.99', (SELECT customer_id FROM Customers WHERE customer_id='NSOAVFDPRR'), '02/08/2019', '8.99', 1),
('209OJxlmcCDAS', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='8439808432GSF'), 2, '25.99', (SELECT customer_id FROM Customers WHERE customer_id='75W4OKI1P2'), '01/18/2020', '5.99', 1);

INSERT INTO VideoInvoices(video_invoice_id, customer, video, rented_on, return_status, returned_on)
VALUES
('nfININD321765', (SELECT customer_id FROM Customers WHERE customer_id='KJSM4Q3BNY'), (SELECT video_id FROM Videos WHERE movie = @InfinityWar and price = (SELECT MIN(price) FROM Videos WHERE movie = @InfinityWar)), '06/24/2020 4:45PM', 0,  null),
('Klao0O21Nnhsa', (SELECT customer_id FROM Customers WHERE customer_id='342QB52XB6'), (SELECT video_id FROM Videos WHERE movie = @GetOut and price = (SELECT MIN(price) FROM Videos WHERE movie = @GetOut)), '11/28/2020 4:45PM', 1,  '12/01/2021 6:27PM'),
('fewa90sdlb324', (SELECT customer_id FROM Customers WHERE customer_id='KG0TVUDHFM'), (SELECT video_id FROM Videos WHERE movie = @GetOut and price = (SELECT MIN(price) FROM Videos WHERE movie = @GetOut)), '04/14/2020 4:45PM', 1,  '04/23/2020 4:58PM'),
('HIfewabo07843', (SELECT customer_id FROM Customers WHERE customer_id='LZL160YHEA'), (SELECT video_id FROM Videos WHERE movie = @InfinityWar and price = (SELECT MIN(price) FROM Videos WHERE movie = @InfinityWar)), '07/25/2020 4:45PM', 0,  null),
('JIOFJOI4123FD', (SELECT customer_id FROM Customers WHERE customer_id='L56ZMK77JJ'), (SELECT video_id FROM Videos WHERE movie = @Argo and price = (SELECT MIN(price) FROM Videos WHERE movie = @Argo)), '03/05/2020 3:22PM', 1, '03/07/2020 12:34PM'),
('uhHUHI83LJOIF', (SELECT customer_id FROM Customers WHERE customer_id='BHDWB3OC9S'), (SELECT video_id FROM Videos WHERE movie = @GetOut and price = (SELECT MIN(price) FROM Videos WHERE movie = @GetOut)), '02/25/2021 2:47PM', 1,  '02/28/2021 4:07PM'),
('HIF8FEWL2HNDA', (SELECT customer_id FROM Customers WHERE customer_id='QU5OQ64KWK'), (SELECT video_id FROM Videos WHERE movie = @InfinityWar and price = (SELECT MIN(price) FROM Videos WHERE movie = @InfinityWar)), '04/05/2021 4:45PM', 1,  '04/07/2021 11:05AM');


DECLARE @InfinityWar AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='Avengers: Infinity War');
DECLARE @Argo AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='Argo'); 
DECLARE @GetOut AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='Get Out'); 
DECLARE @WolfWallSt AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='The Wolf on Wall Street'); 
DECLARE @Us AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='Us'); 
DECLARE @Parasite AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='Parasite'); 
DECLARE @12Years AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='12 Years A Slave'); 
DECLARE @ToyStory4 AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='Toy Story 4'); 
DECLARE @GoneGirl AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='Gone Girl');
DECLARE @Ma AS VARCHAR(15) = (SELECT movie_id FROM Movies WHERE title='Ma'); 

-- charge type:  'Damage - 0, 'Failure to Rewind - 1, Failure to Return 2
INSERT INTO VideoCharges(video_charge_id, video_invoice, charge_type, charge_fee, customer, charge_date, tax, paid_status)
VALUES
('3VSOIHbsaCSZc', 'JIOFJOI4123FD', 1, '4.99', 'L56ZMK77JJ', '03/07/2020', null, 0),
('ldSTSNK861yfL', 'uhHUHI83LJOIF', 0, '15.99', 'BHDWB3OC9S', '02/28/2021', '6.99', 1),
('lbif9312kbfe3', 'nfININD321765', 2, '20.99', 'KJSM4Q3BNY', '07/25/2020', '5.99', 0),
('HBLIBAF420804', 'HIfewabo07843', 2, '20.99', 'LZL160YHEA', '08/26/2020', '5.99', 1),
('knIOHnlkfaIJ0', 'Klao0O21Nnhsa', 1, '4.99', '342QB52XB6', '03/07/2020', null, 1);
-- charge type:  'Damage - 0, 'Failure to Rewind - 1, Failure to Return 2
INSERT INTO DVDCharges(dvd_charge_id, dvd_invoice, charge_type, charge_fee, customer, charge_date, tax, paid_status)
VALUES
('pnlbnOUBBJKNS', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='LXasE2BN04543'), 0, '15.99', (SELECT customer_id FROM Customers WHERE customer_id='3ER91F9TZ7'), '09/28/2019', null, 0),
('n3PJOPOINFAFE', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='4932u90843fsS'), 0, '15.99', (SELECT customer_id FROM Customers WHERE customer_id='BHDWB3OC9S'), '04/28/2020', '8.99', 1),
('biubOINANKDLA', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='obgare943523g'), 2, '25.99', (SELECT customer_id FROM Customers WHERE customer_id='2267Y1N1D5'), '03/14/2020', '5.99', 1),
('808893okjhUHU', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='ks34231N4DSAS'), 0, '15.99', (SELECT customer_id FROM Customers WHERE customer_id='NSOAVFDPRR'), '02/08/2019', '8.99', 1),
('209OJxlmcCDAS', (SELECT dvd_invoice_id FROM DVDInvoices WHERE dvd_invoice_id='8439808432GSF'), 2, '25.99', (SELECT customer_id FROM Customers WHERE customer_id='75W4OKI1P2'), '01/18/2020', '5.99', 1);



/*
# Queries
*/


SELECT DISTINCT m.title as Title, m.rating as Rating, d.genre as Genre,  m.release_year as 'Release Year'
FROM Movies m
INNER JOIN DVDs d
ON m.movie_id = d.movie
ORDER BY m.title;

/*
## Searching for Movies that star Actresses/Actors via Castmate Name
*/

DECLARE @RobertDowneyJr AS VARCHAR(15) = (SELECT castmate_id FROM Castmates WHERE full_name='Robert Downey Jr.'); 
DECLARE @LeonardoDiCaprio AS VARCHAR(15) = (SELECT castmate_id FROM Castmates WHERE full_name='Leonardo DiCaprio');
DECLARE @LupitaNyongo AS VARCHAR(15) = (SELECT castmate_id FROM Castmates WHERE full_name='Lupita Nyoung''o'); 

-- Robert Downey Jr. 
SELECT m.title as Title, i.character_name as Character
FROM Movies as m --Select title of Movie
INNER JOIN Involvements i
ON m.movie_id = i.movie AND i.castmate = @RobertDowneyJr

-- Leonardo DiCaprio
SELECT m.title as Title, i.character_name as Character
FROM Movies as m --Select title of Movie
INNER JOIN Involvements i
ON m.movie_id = i.movie AND i.castmate = @LeonardoDiCaprio

-- Lupita Nyong'o
SELECT m.title as Title, i.character_name as Character
FROM Movies as m --Select title of Movie
INNER JOIN Involvements i
ON m.movie_id = i.movie AND i.castmate = @LupitaNyongo; 


/*
## Searching for Movies that star Directors via Castmate Name
*/

-- Tyler Perry
DECLARE @TylerPerry AS VARCHAR(15) = (SELECT castmate_id FROM Castmates WHERE full_name='Tyler Perry'); --Declare the Castmate as a variable
SELECT title as Title FROM Movies --Select title of Movie
WHERE movie_id IN (SELECT movie FROM Involvements WHERE castmate=@TylerPerry AND castmate_type='director'); -- Return the Involvement movie value for Tyler Perry

-- Ben Affleck
DECLARE @BenAffleck AS VARCHAR(15) = (SELECT castmate_id FROM Castmates WHERE full_name='Ben Affleck'); --Declare the Castmate as a variable
SELECT title as Title FROM Movies --Select title of Movie
WHERE movie_id IN (SELECT movie FROM Involvements WHERE castmate=@BenAffleck AND castmate_type='director'); -- Return the Involvement movie value for Ben Affleck

-- Jordan Peele 
DECLARE @JordanPeele AS VARCHAR(15) = (SELECT castmate_id FROM Castmates WHERE full_name='Jordan Peele'); --Declare the Castmate as a variable
SELECT title as Title FROM Movies --Select title of Movie
WHERE movie_id IN (SELECT movie FROM Involvements WHERE castmate=@JordanPeele AND castmate_type='director'); -- Return the Involvement movie value for Jordan Peele


/*
## Searching the Cast of Movies
*/

-- Avengers: Infinity War

SELECT c.full_name as Cast, i.castmate_type as Type, ISNULL(i.character_name, 'N/A') AS Character -- Show Cast's full name, type, and character name if applicable
FROM Involvements i
INNER JOIN Castmates c
ON i.castmate = c.castmate_id
WHERE movie=@InfinityWar; -- search for Infinity War

-- Parasite

SELECT c.full_name as Cast, i.castmate_type as Type, ISNULL(i.character_name, 'N/A') AS Character -- Show Cast's full name, type, and character name if applicable
FROM Involvements i
INNER JOIN Castmates c
ON i.castmate = c.castmate_id
WHERE movie=@Parasite;

-- Toy Story 4

SELECT c.full_name as Cast, i.castmate_type as Type, ISNULL(i.character_name, 'N/A') AS Character -- Show Cast's full name, type, and character name if applicable
FROM Involvements i
INNER JOIN Castmates c
ON i.castmate = c.castmate_id
WHERE movie=@ToyStory4;

/*
## Displaying Movies with Academy Awards and Awardees
*/

SELECT a.award_name as Award, m.title as Title, c.full_name as Awardee, a.year as Year
FROM ((Awards a
INNER JOIN Movies m
ON a.movie = m.movie_id)
INNER JOIN Castmates c 
ON a.castmate = c.castmate_id);

-- Joei wants to rent Get Out, Avengers: Infinity War, The Wolf on Wall Street. He wants to rent 'Get Out' in Video format, but wants to see both DVD and Video prices for 'The Wolf on Wall Street' and 'Avengers: Infinity War'

SELECT m.title as Title, FORMAT(v.price, 'C') as 'Video Price',  dis.company as 'Distributor'
FROM ((Movies m
INNER JOIN Videos v
ON m.movie_id = v.movie)
INNER JOIN Distributors dis
ON v.distributor_sn = dis.distributor_id)
WHERE m.movie_id IN (@GetOut, @InfinityWar, @WolfWallSt)
ORDER BY m.title;

SELECT m.title as Title, FORMAT(d.price, 'C') as 'DVD Price', dis.company as 'Distributor'
FROM ((Movies m
INNER JOIN DVDs d
ON m.movie_id = d.movie)
INNER JOIN Distributors dis
ON d.distributor_sn = dis.distributor_id)
WHERE m.movie_id IN (@InfinityWar, @WolfWallSt)
ORDER BY m.title;

--Joei begins to checkout. He wants to cheapeast copies of Get Out and Avengers in DVD format and The Wolf on Wall Street in VIdeo.

DECLARE @GetOutCHEAP AS VARCHAR(15) = (SELECT video_id FROM Videos WHERE movie = @GetOut and price = (SELECT MIN(price) FROM Videos WHERE movie = @GetOut));
DECLARE @WallStCHEAP AS VARCHAR(15) = (SELECT video_id FROM Videos WHERE movie = @WolfWallSt and price = (SELECT MIN(price) FROM Videos WHERE movie = @WolfWallSt));
DECLARE @InfinityWarCHEAP AS VARCHAR(15) = (SELECT dvd_id FROM DVDs WHERE movie = @InfinityWar and price = (SELECT MIN(price) FROM DVDs WHERE movie = @InfinityWar));

INSERT INTO VideoInvoices(video_invoice_id, customer, video, rented_on, return_status, returned_on)
VALUES
('snjOIHIFH3321', 'Q5C5EEHW5K', @WallStCHEAP, '04/05/2021 6:50PM', 1, 4, '04/08/2021 12:05PM');  -- 0 meaning false, 1 meaning true for BIT values

INSERT INTO DVDInvoices(dvd_invoice_id, customer, dvd, rented_on, return_status, returned_on)
VALUES
('snjOIHIFH3321', 'Q5C5EEHW5K', @InfinityWarCHEAP, '04/05/2021 6:50PM', 1, '04/08/2021 12:05PM'),  -- 0 meaning false, 1 meaning true for BIT values
('34NPVHSRLDRHV', 'Q5C5EEHW5K', @GetOutCHEAP, '04/05/2021 6:47PM', 1, '04/08/2021 12:00PM');

/*
Customers Manipulation
*/

SELECT full_name as 'Full Name', phone_number as 'Phone Number', res_address as 'Mailing Address' FROM Customers;

SELECT * FROM VideoCharges 
WHERE paid_status=0;

-- copy id from result

-- delete the customer with unpaid video charges

BEGIN TRANSACTION DeleteCustomer

    DELETE FROM Customers
    WHERE customer_id = 'KJSM4Q3BNY'

    DELETE FROM Customers
    WHERE customer_id = 'L56ZMK77JJ'

COMMIT TRANSACTION

BEGIN
    ROLLBACK TRANSACTION DeleteCustomer
END

