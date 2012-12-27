--// Create Project Attribute
-- Migration SQL that makes the change goes here.

CREATE TABLE ${ProjectAttribute} (
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`NAME` VARCHAR(50) NOT NULL ,
	`VALUE` VARCHAR(255) NOT NULL ,
	`TYPE` VARCHAR(50) NOT NULL DEFAULT "" ,
    `LEVEL` INT DEFAULT 0 ,
	`PROJECT_ID` BIGINT NOT NULL ,
	PRIMARY KEY (`ID`) , 
	CONSTRAINT FK_PROJECT_ID_${ProjectAttribute} FOREIGN KEY (`PROJECT_ID`) REFERENCES ${Project}(`ID`)
);

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${ProjectAttribute};
