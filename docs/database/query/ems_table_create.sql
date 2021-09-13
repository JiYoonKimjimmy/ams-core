-- 1. 'STUDENTS' 생성
CREATE TABLE STUDENTS (
    ID NUMBER NOT NULL PRIMARY KEY,
    NAME VARCHAR(20) NOT NULL,
    MOBILE_NUMBER VARCHAR(11) NOT NULL,
    DATE_OF_BIRTH VARCHAR(8) NOT NULL,
    GENDER CHAR(2) NOT NULL,
    SCHOOL VARCHAR(20),
    GRADE CHAR(2),
    VERSION NUMBER,
    CREATED DATE,
    UPDATED DATE
);

-- 2. 'STUDENTS_PARENTS' 생성
CREATE TABLE STUDENTS_PARENTS (
    ID INT NOT NULL PRIMARY KEY,
    STUDENT_ID INT NOT NULL,
    NAME VARCHAR(20) NOT NULL,
    MOBILE_NUMBER VARCHAR(11) NOT NULL,
    VERSION INT,
    CREATED DATE,
    UPDATED DATE,
    FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(ID)
);


