--// Create Project Feature
-- Migration SQL that makes the change goes here.

CREATE TABLE ${ProjectFeature} (
	`ID` INT NOT NULL AUTO_INCREMENT ,
	`CODE` VARCHAR(100) NOT NULL ,
	`LABEL` VARCHAR(100) NOT NULL ,
	`LEVEL` INT NOT NULL DEFAULT 0,
	`ENABLED` BOOLEAN NOT NULL ,
	`PROJECT_ID` BIGINT NOT NULL ,
	PRIMARY KEY (`ID`) ,
	UNIQUE INDEX `CODE_UNIQUE` (`CODE` ASC) ,
	CONSTRAINT FK_PROJECT_ID_${ProjectFeature} FOREIGN KEY (`PROJECT_ID`) REFERENCES ${Project}(`ID`)
);

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${ProjectFeature};