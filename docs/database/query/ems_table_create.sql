-- 1. 'STUDENTS' 생성
create table students (
    id int not null primary key,
    name varchar(20) not null,
    mobile_number varchar(11) not null,
    date_of_birth varchar(8) not null,
    gender char(2) not null,
    school varchar(20),
    grade char(2),
    version int,
    created date,
    updated date
) default charset = utf8;

-- 2. 'STUDENTS_PARENTS' 생성
create table students_parents (
    id int not null primary key,
    student_id int not null,
    name varchar(20) not null,
    mobile_number varchar(11) not null,
    version int,
    created date,
    updated date,
    foreign key (student_id) references students(id)
);


