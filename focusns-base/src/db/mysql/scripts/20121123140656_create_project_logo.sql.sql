--// Create Project Category
-- Migration SQL that makes the change goes here.

CREATE TABLE ${ProjectLogo} (
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`IMAGE_PATH` VARCHAR(225) NOT NULL ,
	PRIMARY KEY (`ID`) 
);


--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${ProjectLogo};
