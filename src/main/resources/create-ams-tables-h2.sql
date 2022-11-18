-- 1. `STUDENTS`
CREATE TABLE STUDENTS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    NAME VARCHAR(20),
    MOBILE_NUMBER VARCHAR(11),
    DATE_OF_BIRTH VARCHAR(8),
    GENDER CHAR(2),
    SCHOOL VARCHAR(20),
    GRADE CHAR(2),
    STATUS VARCHAR(10),
    CREATED DATETIME,
    UPDATED DATETIME,
    PRIMARY KEY (ID)
);

-- 2. `PARENTS`
CREATE TABLE PARENTS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    STUDENT_ID BIGINT NOT NULL,
    NAME VARCHAR(20),
    MOBILE_NUMBER VARCHAR(11),
    GENDER CHAR(2),
    STATUS VARCHAR(10),
    CREATED DATETIME,
    UPDATED DATETIME,
    FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(ID)
);

-- -- 3. `TEACHERS`
-- CREATE TABLE TEACHERS (
--     ID INT PRIMARY KEY AUTO_INCREMENT COMMENT '학생 부모 ID',
--     NAME VARCHAR(20) COMMENT '성명',
--     MOBILE_NUMBER VARCHAR(11) COMMENT '휴대폰 번호',
--     STATUS VARCHAR(10) COMMENT '상태 (ACTIVE: 정상, DELETED: 탈퇴)',
--     CREATED DATETIME COMMENT '생성일',
--     UPDATED DATETIME COMMENT '수정일'
-- );
--
-- -- 4. `CLASSES`
-- CREATE TABLE CLASSES (
--     ID INT PRIMARY KEY AUTO_INCREMENT COMMENT '수업 ID',
--     TEACHER_ID INT NOT NULL COMMENT '선생님 ID',
--     NAME VARCHAR(50) COMMENT '수업명',
--     TYPE VARCHAR(10) COMMENT '수업 구분 (독서 토론, 논술, 역사 수업 등.. CODE 관리)',
--     START_DATE DATE COMMENT '시작 일자',
--     END_DATE DATE COMMENT '종료 일자',
--     DAY_OF_WEEK VARCHAR(50) COMMENT '수업 요일 목록 (eg. MON,TUE,WEN..)',
--     WEEKLY_REPEAT INT COMMENT '주별 반복 (default: 1) 주기수업 시작 주차 기준 반복 기간 설정',
--     TIME_DURATION VARCHAR(2) COMMENT '수업 시간 (mm 단위)',
--     STATUS VARCHAR(10) COMMENT '상태 READY: 준비(default), ACTIVE: 정상, DELETED: 삭제, FINISHED: 종강',
--     CREATED DATETIME COMMENT '생성일',
--     UPDATED DATETIME COMMENT '수정일',
--     FOREIGN KEY (TEACHER_ID) REFERENCES TEACHERS(ID)
-- );
--
-- -- 5. `CLASS_SCHEDULES`
-- CREATE TABLE CLASS_SCHEDULES (
--     ID INT PRIMARY KEY AUTO_INCREMENT COMMENT '수업 일정 ID',
--     CLASS_ID INT NOT NULL COMMENT '수업 ID',
--     TYPE VARCHAR(10) COMMENT '수업 구분 REGULAR: 정규, MAKEUP: 보강, SPECIAL: 특강',
--     YEAR VARCHAR(4) COMMENT '연도 YYYY',
--     MONTH VARCHAR(2) COMMENT '월 MM',
--     DAY VARCHAR(2) COMMENT '일 DD',
--     HOUR VARCHAR(2) COMMENT '시 HH',
--     MINUTE VARCHAR(2) COMMENT '분 mm',
--     TIME_DURATION VARCHAR(2) COMMENT '수업 시간 (mm 단위)',
--     STATUS VARCHAR(10) COMMENT '상태 READY`: 준비(default), FINISHED: 완료, CANCELED: 취소',
--     CREATED DATETIME COMMENT '생성일',
--     UPDATED DATETIME COMMENT '수정일',
--     FOREIGN KEY (CLASS_ID) REFERENCES CLASSES(ID)
-- );
-- -- `CLASS_SCHEDULES` UNIQUE INDEX
-- CREATE UNIQUE INDEX CLASS_SCHEDULES_UNIQUE_01 ON CLASS_SCHEDULES(CLASS_ID, TYPE, YEAR, MONTH, DAY, HOUR, MINUTE);
--
-- -- 6. `CLASS_ATTENDANCE`
-- CREATE TABLE CLASS_ATTENDANCE (
--     ID INT PRIMARY KEY AUTO_INCREMENT COMMENT '출석 ID',
--     STUDENT_ID INT NOT NULL COMMENT '학생 ID',
--     CLASS_SCHEDULE_ID INT NOT NULL COMMENT '수업 일정 ID',
--     CREATED DATETIME COMMENT '생성일',
--     UPDATED DATETIME COMMENT '수정일',
--     FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(ID),
--     FOREIGN KEY (CLASS_SCHEDULE_ID) REFERENCES CLASS_SCHEDULES(ID)
-- );