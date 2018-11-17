-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-11-02 20:47:05.274

-- tables
-- Table: Area
CREATE TABLE Area (
    id int  NOT NULL,
    name varchar(500)  NOT NULL,
    CONSTRAINT Area_uk_name UNIQUE (name),
    CONSTRAINT Area_pk PRIMARY KEY (id)
);

-- Table: Comment
CREATE TABLE Comment (
    id int  NOT NULL,
    description varchar(500)  NOT NULL,
	commentDate date NOT NULL,
    User_email varchar(500)  NOT NULL,
    Initiative_id int  NOT NULL,
    CONSTRAINT Comment_pk PRIMARY KEY (id,User_email,Initiative_id)
);

-- Table: Initiative
CREATE TABLE Initiative (
    id int  NOT NULL,
    description varchar(500)  NOT NULL,
    detail varchar(500)  NOT NULL,
    creationDate date  NOT NULL,
    modificationDate date  NULL,
    keyWords varchar(500)  NOT NULL,
    User_email varchar(500)  NOT NULL,
    InitiativeStatus_id int  NOT NULL,
    CONSTRAINT Initiative_pk PRIMARY KEY (id)
);

-- Table: InitiativeStatus
CREATE TABLE InitiativeStatus (
    id int  NOT NULL,
    description varchar(500)  NOT NULL,
    CONSTRAINT Estado_uk_description UNIQUE (description),
    CONSTRAINT InitiativeStatus_pk PRIMARY KEY (id)
);

-- Table: User
CREATE TABLE "User" (
    names varchar(500)  NOT NULL,
    lastnames varchar(500)  NOT NULL,
    email varchar(500)  NOT NULL,
    code int  NOT NULL,
    role varchar(500)  NOT NULL,
    status varchar(500)  NOT NULL,
    Area_id int  NOT NULL,
    CONSTRAINT Usuario_uk_code UNIQUE (code),
    CONSTRAINT User_pk PRIMARY KEY (email)
);

-- Table: Vote
CREATE TABLE Vote (
	id int NOT NULL,
    User_email varchar(500)  NOT NULL,
    voteDate date  NOT NULL,
    affinity int  NOT NULL,
    Initiative_id int  NOT NULL,
	CONSTRAINT Vote_uk_user_initiative UNIQUE (User_email,Initiative_id),
    CONSTRAINT Vote_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: Comment_Initiative (table: Comment)
ALTER TABLE Comment ADD CONSTRAINT Comment_Initiative
    FOREIGN KEY (Initiative_id)
    REFERENCES Initiative (id)
;

-- Reference: Comment_User (table: Comment)
ALTER TABLE Comment ADD CONSTRAINT Comment_User
    FOREIGN KEY (User_email)
    REFERENCES "User" (email)
;

-- Reference: Iniciative_User (table: Initiative)
ALTER TABLE Initiative ADD CONSTRAINT Initiative_User
    FOREIGN KEY (User_email)
    REFERENCES "User" (email)
;

-- Reference: Initiative_InitiativeStatus (table: Initiative)
ALTER TABLE Initiative ADD CONSTRAINT Initiative_InitiativeStatus
    FOREIGN KEY (InitiativeStatus_id)
    REFERENCES InitiativeStatus (id)
;

-- Reference: User_Area (table: User)
ALTER TABLE "User" ADD CONSTRAINT User_Area
    FOREIGN KEY (Area_id)
    REFERENCES Area (id)
;

-- Reference: Vote_Initiative (table: Vote)
ALTER TABLE Vote ADD CONSTRAINT Vote_Initiative
    FOREIGN KEY (Initiative_id)
    REFERENCES Initiative (id)
;

-- Reference: Vote_User (table: Vote)
ALTER TABLE Vote ADD CONSTRAINT Vote_User
    FOREIGN KEY (User_email)
    REFERENCES "User" (email)
;

-- End of file.

