--// create blog comment
-- Migration SQL that makes the change goes here.

CREATE TABLE ${BlogComment} (
	`ID` BIGINT NOT NULL AUTO_INCREMENT ,
	`TITLE` VARCHAR(100) NOT NULL ,
    `CONTENT` TEXT NOT NULL ,
    `CREATE_AT` TIMESTAMP NOT NULL ,
	`MODIFY_AT` TIMESTAMP NOT NULL ,
    `POST_ID` BIGINT NOT NULL ,
    `CREATE_BY_ID` BIGINT NOT NULL ,
	PRIMARY KEY (`ID`) ,
    CONSTRAINT FK_POST_ID_${BlogComment} FOREIGN KEY (`POST_ID`) REFERENCES ${BlogPost}(`ID`) ,
    CONSTRAINT FK_CREATE_BY_ID_${BlogComment} FOREIGN KEY (`CREATE_BY_ID`) REFERENCES ${User}(`ID`)
);

--//@UNDO
-- SQL to undo the change goes here.

DROP TABLE ${BlogComment};
