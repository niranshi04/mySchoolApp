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
