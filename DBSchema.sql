create database bookMyRide;

#create users
CREATE TABLE `bookmyride`.`users` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `EMAIL_ID` VARCHAR(30) NOT NULL,
  `NAME` VARCHAR(30) NOT NULL,
  `CONTACT_NO` INT NOT NULL,
  `PASSWORD` VARCHAR(255) NOT NULL,
  `ROLE` ENUM('USER', 'DRIVER', 'ADMIN') NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE INDEX `EMAIL_ID_UNIQUE` (`EMAIL_ID` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
