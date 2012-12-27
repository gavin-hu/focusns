--// create blog tag
-- Migration SQL that makes the change goes here.

CREATE TABLE ${BlogTag} (
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`NAME` VARCHAR(100) NOT NULL ,
    `PROJECT_ID` BIGINT NOT NULL ,
	PRIMARY KEY (`ID`) ,
    CONSTRAINT FK_PROJECT_ID_${BlogTag} FOREIGN KEY (`PROJECT_ID`) REFERENCES ${Project}(`ID`)
);

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${BlogTag};
