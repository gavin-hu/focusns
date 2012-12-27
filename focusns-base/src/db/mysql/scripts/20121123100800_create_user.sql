--// Create User
-- Migration SQL that makes the change goes here.

CREATE TABLE ${User} (
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`USERNAME` VARCHAR(100) NOT NULL ,
	`PASSWORD` VARCHAR(100) NOT NULL ,
	`EMAIL` VARCHAR(100) NOT NULL ,
    `PROJECT_ID` BIGINT ,
	PRIMARY KEY (`ID`)
);

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${User}
