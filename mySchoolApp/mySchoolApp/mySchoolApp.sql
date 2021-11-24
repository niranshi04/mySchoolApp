CREATE TABLE IF NOT EXISTS User (
  password varchar(255) DEFAULT NULL,
  emailAddress varchar(255) NOT NULL UNIQUE,
  dateCreated date NOT NULL,
  isEmailVerified boolean DEFAULT false NOT NULL,
  lastLoginDate date DEFAULT NULL,
  lastLoginTime time DEFAULT NULL,
  role enum ('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT') NOT NULL,
  PRIMARY KEY (emailAddress)
);

DELIMITER $$
CREATE TRIGGER check_insert_user_emailAddress BEFORE INSERT ON User
FOR EACH ROW
BEGIN
IF (NEW.emailAddress REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_user_emailAddress BEFORE UPDATE ON User
FOR EACH ROW
BEGIN
IF (NEW.emailAddress REGEXP "^[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$") = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

CREATE TABLE IF NOT EXISTS UserToken (
  emailAddress varchar(255) NOT NULL UNIQUE,
  token varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (emailAddress),
  FOREIGN KEY (emailAddress) REFERENCES User(emailAddress) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `Student` (
  `name` varchar(255) NOT NULL,
  `registrationNo` int NOT NULL AUTO_INCREMENT,
  `gender` enum('Male','Female','Not Specified') NOT NULL DEFAULT 'Not Specified',
  `dateOfBirth` date NOT NULL,
  `houseNumber` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `dateOfAdmission` date NOT NULL,
  `dateOfLeavingSchool` date DEFAULT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `phonNumber` varchar(15) NOT NULL,
  PRIMARY KEY (`registrationNo`),
  UNIQUE KEY `emailAddress` (`emailAddress`),
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`emailAddress`) REFERENCES `User` (`emailAddress`) ON DELETE CASCADE ON UPDATE CASCADE
)

CREATE TABLE IF NOT EXISTS Parents (
  registrationNo int NOT NULL,
  motherName varchar(255) NOT NULL,
  motherJob varchar(255) DEFAULT NULL,
  fatherName varchar(255) NOT NULL,
  fatherJob varchar(255) DEFAULT NULL,
  phoneNumber1 varchar(15) DEFAULT NULL,
  phoneNumber2 varchar(15) DEFAULT NULL,
 PRIMARY KEY (registrationNo),
  FOREIGN KEY (registrationNo) REFERENCES Student(registrationNo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS StudentPhoneNumber (
  phoneNumber varchar(255) NOT NULL,
  registrationNo int NOT NULL,
  PRIMARY KEY (phoneNumber, registrationNo),
  FOREIGN KEY (registrationNo) REFERENCES Student(registrationNo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS StudentFees (
  registrationNo int NOT NULL,
  lastDate date DEFAULT NULL,
  scholarshipPercent int DEFAULT NULL,
  scholarshipExpiry date DEFAULT NULL,
  CHECK((!lastDate ||DAY(lastDate) = 30 )&& (!scholarshipExpiry ||DAY(scholarshipExpiry) = 30 )),
  PRIMARY KEY (registrationNo),
  FOREIGN KEY (registrationNo) REFERENCES Student(registrationNo) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS FeeDetails (
	classNo int NOT NULL,
    month ENUM("01", "02","03","04","05","06","07","08","09","10","11","12") NOT NULL,
    year int NOT NULL,
    fees int ,
    PRIMARY KEY (classNo, month,year),
    FOREIGN KEY(classNo) REFERENCES ClassDetails(classId) ON DELETE CASCADE ON UPDATE CASCADE);

    
    CREATE TABLE IF NOT EXISTS ClassDetails (
classNo int NOT NULL,
section char NOT NULL,
classId int NOT NULL AUTO_INCREMENT,
PRIMARY KEY (classId))

CREATE TABLE IF NOT EXISTS ClassStudent (
  classId int NOT NULL, 
  registrationNo int NOT NULL,
  startYear int NOT NULL,
  unique(registratioNo, startYear)
  FOREIGN KEY(classId) REFERENCES ClassDetails(classId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY(registrationNo) REFERENCES Student(registrationNo) ON DELETE CASCADE ON UPDATE CASCADE)
  CREATE TABLE IF NOT EXISTS Teacher (
  teacherId int NOT NULL AUTO_INCREMENT,
  gender enum ('Male','Female', 'Not Specified') DEFAULT 'Not Specified' NOT NULL,
  dateOfBirth date NOT NULL,
  dateOfJoining date NOT NULL,
  houseNumber varchar(255) NOT NULL,
  street varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  PRIMARY KEY (teacherId))
  
  CREATE TABLE IF NOT EXISTS TeacherClassSubject (
  teacherId int NOT NULL,
  classId int NOT NULL,
  subjectId int NOT NULL,
  year int NOT NULL,
  PRIMARY KEY (subjectId, classId, year),
  FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (subjectId) REFERENCES Subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (classId) REFERENCES ClassDetails(classId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Teacher (
  teacherId int NOT NULL AUTO_INCREMENT,
  gender enum ('Male','Female', 'Not Specified') DEFAULT 'Not Specified' NOT NULL,
  dateOfBirth date NOT NULL,
  dateOfJoining date NOT NULL,
  houseNumber varchar(255) NOT NULL,
  street varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  subject1 int NOT NULL,
  subject2 int NOT NULL,
  subject3 int NOT NULL,
  classType enum("senior","middle","junior"),
  emailAddress VARCHAR(255) NOT NULL,
  PRIMARY KEY (teacherId),
  FOREIGN KEY (emailAddress) REFERENCES User(emailAddress) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (subject1) REFERENCES Subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (subject2) REFERENCES Subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (subject3) REFERENCES Subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE);
  
  
  CREATE TABLE IF NOT EXISTS TeacherSalary (
  teacherId int NOT NULL,
  lastDate date DEFAULT NULL,
  salary int DEFAULT NULL,
  CHECK((!lastDate ||DAY(lastDate) = 30 )),
  PRIMARY KEY (teacherId),
  FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId) ON DELETE CASCADE ON UPDATE CASCADE
);
  
CREATE TABLE IF NOT EXISTS StudentPhoneNumber (
  phoneNumber varchar(255) NOT NULL,
  registrationNo int NOT NULL,
  PRIMARY KEY (phoneNumber, registrationNo),
  FOREIGN KEY (registrationNo) REFERENCES Student(registrationNo) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS Grades (
  registrationNo int NOT NULL,
  subjectId int NOT NULL,
  classId int NOT NULL,
  year int NOT NULL,
  teacherId int NOT NULL,
  grades int NULL,
  examId int NOT NULL,
  primary key(registrationNo, subjectId, classId, examId, year),
  FOREIGN KEY (registrationNo) REFERENCES Student(registrationNo) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (subjectId) REFERENCES Subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (classId) REFERENCES ClassDetails(classId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (examId) REFERENCES exam(examId) ON DELETE CASCADE ON UPDATE CASCADE)
  
  CREATE table if not exists attendance(
registrationNo int NOT NULL,
attendance boolean DEFAULT false,
date date NOT NULL,
classId int NOT NULL,
PRIMARY KEY(registrationNo,date,classId),
FOREIGN KEY(registrationNo) REFERENCES student(registrationNo) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(classId) REFERENCES classDetails(classId) ON DELETE CASCADE ON UPDATE CASCADE)