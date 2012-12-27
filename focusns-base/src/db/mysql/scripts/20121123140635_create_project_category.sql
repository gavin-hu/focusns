--// Create Project Category
-- Migration SQL that makes the change goes here.

CREATE TABLE ${ProjectCategory} (
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`CODE` VARCHAR(100) NOT NULL ,
    `LABEL` VARCHAR(100) NOT NULL ,
    `ENABLED` BOOLEAN DEFAULT TRUE ,
    `PRIVATE` BOOLEAN DEFAULT FALSE ,
    `LEVEL` INT DEFAULT 0 ,
	PRIMARY KEY (`ID`) ,
	UNIQUE INDEX `CODE_UNIQUE` (`CODE` ASC)
);


--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${ProjectCategory};
