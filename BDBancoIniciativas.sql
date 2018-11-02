-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-11-02 17:52:37.328

-- tables
-- Table: Area
CREATE TABLE Area (
    id int  NOT NULL,
    name text  NOT NULL,
    CONSTRAINT Area_uk_name UNIQUE (name),
    CONSTRAINT Area_pk PRIMARY KEY (id)
);

-- Table: Comment
CREATE TABLE Comment (
    id int  NOT NULL,
    description text  NOT NULL,
    User_email text  NOT NULL,
    Iniciative_id int  NOT NULL,
    CONSTRAINT Comment_pk PRIMARY KEY (id,User_email,Iniciative_id)
);

-- Table: Iniciative
CREATE TABLE Iniciative (
    id int  NOT NULL,
    description text  NOT NULL,
    detail text  NOT NULL,
    creationDate date  NOT NULL,
    modificationDate date  NOT NULL,
    keyWords text[]  NOT NULL,
    User_email text  NOT NULL,
    IniciativeStatus_id int  NOT NULL,
    CONSTRAINT Iniciative_pk PRIMARY KEY (id)
);

-- Table: IniciativeStatus
CREATE TABLE IniciativeStatus (
    id int  NOT NULL,
    description text  NOT NULL,
    CONSTRAINT Estado_uk_description UNIQUE (description),
    CONSTRAINT IniciativeStatus_pk PRIMARY KEY (id)
);

-- Table: User
CREATE TABLE "User" (
    name text  NOT NULL,
    lastname text  NOT NULL,
    email text  NOT NULL,
    code int  NOT NULL,
    Trole text  NOT NULL,
    Tstatus text  NOT NULL,
    Area_id int  NOT NULL,
    CONSTRAINT Usuario_uk_code UNIQUE (code),
    CONSTRAINT User_pk PRIMARY KEY (email)
);

-- Table: Vote
CREATE TABLE Vote (
    User_email text  NOT NULL,
    Iniciative_id int  NOT NULL,
    voteDate date  NOT NULL,
    Tafinity int  NOT NULL,
    CONSTRAINT Vote_pk PRIMARY KEY (User_email,Iniciative_id)
);

-- foreign keys
-- Reference: Comment_Iniciative (table: Comment)
ALTER TABLE Comment ADD CONSTRAINT Comment_Iniciative
    FOREIGN KEY (Iniciative_id)
    REFERENCES Iniciative (id)
;

-- Reference: Comment_User (table: Comment)
ALTER TABLE Comment ADD CONSTRAINT Comment_User
    FOREIGN KEY (User_email)
    REFERENCES "User" (email)
;

-- Reference: Iniciative_IniciativeStatus (table: Iniciative)
ALTER TABLE Iniciative ADD CONSTRAINT Iniciative_IniciativeStatus
    FOREIGN KEY (IniciativeStatus_id)
    REFERENCES IniciativeStatus (id)
;

-- Reference: Iniciative_User (table: Iniciative)
ALTER TABLE Iniciative ADD CONSTRAINT Iniciative_User
    FOREIGN KEY (User_email)
    REFERENCES "User" (email)
;

-- Reference: User_Area (table: User)
ALTER TABLE "User" ADD CONSTRAINT User_Area
    FOREIGN KEY (Area_id)
    REFERENCES Area (id)
;

-- Reference: Vote_Iniciative (table: Vote)
ALTER TABLE Vote ADD CONSTRAINT Vote_Iniciative
    FOREIGN KEY (Iniciative_id)
    REFERENCES Iniciative (id)
;

-- Reference: Vote_User (table: Vote)
ALTER TABLE Vote ADD CONSTRAINT Vote_User
    FOREIGN KEY (User_email)
    REFERENCES "User" (email)
;

-- End of file.

