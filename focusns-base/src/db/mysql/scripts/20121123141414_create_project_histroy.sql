--// Create Project Histroy
-- Migration SQL that makes the change goes here.

CREATE TABLE ${ProjectHistroy} (
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`CONTENT` VARCHAR(255) NOT NULL ,
	`CREATE_AT` TIMESTAMP NOT NULL ,
	`PROJECT_ID` BIGINT NOT NULL ,
    `CREATE_BY_ID` BIGINT NOT NULL ,
    `TARGET_ID` BIGINT NOT NULL ,
    `TARGET_TYPE` VARCHAR(255) NOT NULL ,
	PRIMARY KEY (`ID`) , 
	CONSTRAINT FK_PROJECT_ID_${ProjectHistroy} FOREIGN KEY (`PROJECT_ID`) REFERENCES ${Project}(`ID`) ,
    CONSTRAINT FK_CREATE_BY_ID_${ProjectHistroy} FOREIGN KEY (`CREATE_BY_ID`) REFERENCES ${User}(`ID`)
);

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${ProjectHistroy};
