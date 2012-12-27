--// create blog post
-- Migration SQL that makes the change goes here.

CREATE TABLE ${BlogPost} (
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`TITLE` VARCHAR(100) NOT NULL ,
    `CONTENT` TEXT NOT NULL ,
    `CREATE_AT` TIMESTAMP NOT NULL ,
	`MODIFY_AT` TIMESTAMP NOT NULL ,
    `TAG_ID` BIGINT NOT NULL ,
    `CREATE_BY_ID` BIGINT NOT NULL ,
	PRIMARY KEY (`ID`) ,
    CONSTRAINT FK_TAG_ID_${BlogTag} FOREIGN KEY (`TAG_ID`) REFERENCES ${BlogTag}(`ID`) ,
    CONSTRAINT FK_CREATE_BY_ID_${BlogTag} FOREIGN KEY (`CREATE_BY_ID`) REFERENCES ${User}(`ID`)
);

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${BlogPost};
