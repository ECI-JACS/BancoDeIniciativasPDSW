-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-11-02 20:47:05.274

-- foreign keys
ALTER TABLE Comment
    DROP CONSTRAINT Comment_Initiative;

ALTER TABLE Comment
    DROP CONSTRAINT Comment_User;

ALTER TABLE Initiative
    DROP CONSTRAINT Initiative_User;

ALTER TABLE Initiative
    DROP CONSTRAINT Initiative_InitiativeStatus;

ALTER TABLE "User"
    DROP CONSTRAINT User_Area;

ALTER TABLE Vote
    DROP CONSTRAINT Vote_Initiative;

ALTER TABLE Vote
    DROP CONSTRAINT Vote_User;

-- tables
DROP TABLE Area;

DROP TABLE Comment;

DROP TABLE Initiative;

DROP TABLE InitiativeStatus;

DROP TABLE "User";

DROP TABLE Vote;

-- End of file.

