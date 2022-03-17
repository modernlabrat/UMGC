-- Drop Tables

DROP TABLE Engineers PURGE;
DROP TABLE Faculty PURGE;
DROP TABLE Classes PURGE;
DROP TABLE ClassEnrollments PURGE;

-- Create Tables

CREATE TABLE Engineers(
  EID INTEGER PRIMARY KEY,
  Lastname varchar2(20),
  Firstname varchar2(20),
  Email varchar2(35),
  Graddate DATE
);

CREATE TABLE Faculty (
  FID INTEGER PRIMARY KEY,
  Lastname varchar2(20),
  Firstname varchar2(20),
  Email varchar2(35),
  Hiredate DATE
);

CREATE TABLE Classes (
  CID INTEGER PRIMARY KEY,
  Subject varchar2(15),
  Catalognbr INTEGER,
  Title varchar2(35)
);

CREATE TABLE ClassEnrollments (
  EnID INTEGER PRIMARY KEY,
  CID INTEGER,
  FID INTEGER,
  EID INTEGER,
  FOREIGN KEY (CID) REFERENCES Classes(CID),
  FOREIGN KEY (FID) REFERENCES Faculty(FID),
  FOREIGN KEY (EID) REFERENCES Engineers(EID)
);

-- Insert Data into Tables

INSERT ALL 
  -- Engineers

  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (1, 'Armstrong', 'Raeleigh', 'rarmstrong@student.umgc.edu', TO_DATE('2024/12/08', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (2, 'Randall', 'Tom', 'trandall@student.umgc.edu', TO_DATE('2024/07/26', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (3, 'Johnson', 'Matthew', 'mjohnson@student.umgc.edu', TO_DATE('2023/06/11', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (4, 'Fields', 'Timmy', 'tfields@student.umgc.edu', TO_DATE('2024/05/17', 'yyyy/mm/dd'))

  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (5, 'Winters', 'Craig', 'cwinters@student.umgc.edu', TO_DATE('2023/05/05', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (6, 'Phillips', 'Joy', 'jphillips@student.umgc.edu', TO_DATE('2023/04/18', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (7, 'Soy', 'Choi', 'csoy@student.umgc.edu', TO_DATE('2025/12/16', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (8, 'Davids', 'Pete', 'pdavidson@student.umgc.edu', TO_DATE('2022/03/20', 'yyyy/mm/dd'))

  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (9, 'Vinci', 'Leo', 'lvinci@student.umgc.edu', TO_DATE('2024/05/19', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (10, 'Griff', 'Pete', 'pgriff@student.umgc.edu', TO_DATE('2024/05/05', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (11, 'Dash', 'Kimberly', 'kdash@student.umgc.edu', TO_DATE('2024/04/03', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (12, 'Eill', 'Bill', 'beill@student.umgc.edu', TO_DATE('2022/02/05', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (13, 'Richards', 'Tony', 'trichards@student.umgc.edu', TO_DATE('2024/11/26', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (14, 'Tesa', 'Abel', 'atesa@student.umgc.edu', TO_DATE('2023/12/16', 'yyyy/mm/dd'))
  
  INTO Engineers(EID, Lastname, Firstname, Email, Graddate) 
  VALUES (15, 'Samuel', 'Kyra', 'ksamuel@student.umgc.edu', TO_DATE('2022/11/16', 'yyyy/mm/dd'))
  -- Faculty

  INTO Faculty(FID, Lastname, Firstname, Email, Hiredate) 
  VALUES (1, 'Gibbons', 'Lee', 'lgibbons@falculty.umgc.edu', TO_DATE('2012/01/04', 'yyyy/mm/dd'))

  INTO Faculty(FID, Lastname, Firstname, Email, Hiredate) 
  VALUES (2, 'Jenkins', 'Tasha', 'tjenkins@falculty.umgc.edu', TO_DATE('2018/11/19', 'yyyy/mm/dd'))

  INTO Faculty(FID, Lastname, Firstname, Email, Hiredate) 
  VALUES (3, 'Lincoln', 'Ford', 'flincoln@falculty.umgc.edu', TO_DATE('2020/06/21', 'yyyy/mm/dd'))

  -- Classes

  INTO Classes(CID, Subject, Catalognbr, Title) 
  VALUES (1, 'SDEV', 350, 'Database Security')

  INTO Classes(CID, Subject, Catalognbr, Title) 
  VALUES (2, 'CMSC', 405, 'Computer Graphics')
  
  INTO Classes(CID, Subject, Catalognbr, Title) 
  VALUES (3, 'BIO', 101, 'Concepts of Biology')

  -- ClassEnrollments

  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (1, 2, 2, 2)

  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (2, 1, 3, 4)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (3, 2, 2, 6)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (4, 1, 1, 8)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (5, 3, 2, 9)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (6, 3, 3, 11)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (7, 2, 3, 4)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (8, 1, 2, 3)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (9, 1, 2, 2)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (10, 3, 1, 14)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (11, 2, 3, 9)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (12, 1, 2, 10)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (13, 3, 3, 6)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (14, 3, 2, 1)
  
  INTO ClassEnrollments(EnID, CID, FID, EID) 
  VALUES (15, 1, 1, 15)
SELECT 1 FROM dual;

-- QUERY ALL TABLES BY PRIMARY KEY IN DESCENDING ORDER

SELECT * FROM Engineers ORDER BY EID DESC;
SELECT * FROM Faculty ORDER BY FID DESC;
SELECT * FROM Classes ORDER BY CID DESC;
SELECT * FROM ClassEnrollments ORDER BY EnID DESC;

-- UPDATE ROWS

UPDATE Faculty
SET Lastname='Friendship'
WHERE FID=1;

UPDATE Engineers
SET Firstname='Amadeus'
WHERE EID=13;

UPDATE Classes
SET Subject='IOT Cyber'
WHERE CID=2;