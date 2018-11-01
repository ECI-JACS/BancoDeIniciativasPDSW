-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-11-01 15:50:35.266

-- tables
-- Table: Area
CREATE TABLE Area (
    id int  NOT NULL,
    nombre varchar(20)  NOT NULL,
    CONSTRAINT Area_ak_nombre UNIQUE (nombre) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Area_pk PRIMARY KEY (id)
);

-- Table: Estado
CREATE TABLE Estado (
    id int  NOT NULL,
    descripcion varchar(30)  NOT NULL,
    CONSTRAINT Estado_ak_descripcion UNIQUE (descripcion) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Estado_pk PRIMARY KEY (id)
);

-- Table: Iniciativa
CREATE TABLE Iniciativa (
    id int  NOT NULL,
    Testado int  NOT NULL,
    descripcion varchar(150)  NOT NULL,
    detalle varchar(500)  NOT NULL,
    fechaCreacion date  NOT NULL,
    Usuario_correo varchar(50)  NOT NULL,
    Estado_id int  NOT NULL,
    CONSTRAINT Iniciativa_pk PRIMARY KEY (id)
);

-- Table: Interes
CREATE TABLE Interes (
    Iniciativa_id int  NOT NULL,
    Usuario_correo varchar(50)  NOT NULL,
    comentario varchar(300)  NOT NULL,
    fecha date  NOT NULL,
    Tnivel int  NOT NULL,
    CONSTRAINT Interes_pk PRIMARY KEY (Iniciativa_id,Usuario_correo)
);

-- Table: Rol
CREATE TABLE Rol (
    id int  NOT NULL,
    rol varchar(20)  NOT NULL,
    CONSTRAINT Rol_ak_rol UNIQUE (rol) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Rol_pk PRIMARY KEY (id)
);

-- Table: Usuario
CREATE TABLE Usuario (
    nombres varchar(30)  NOT NULL,
    apellidos varchar(30)  NOT NULL,
    correo varchar(50)  NOT NULL,
    codigo int  NOT NULL,
    fechaNacimiento date  NOT NULL,
    estado varchar(10)  NOT NULL,
    Rol_id int  NOT NULL,
    Area_id int  NOT NULL,
    CONSTRAINT Usuario_ak_codigo UNIQUE (codigo) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Usuario_pk PRIMARY KEY (correo)
);

-- Table: Voto
CREATE TABLE Voto (
    Usuario_correo varchar(50)  NOT NULL,
    Iniciativa_id int  NOT NULL,
    CONSTRAINT Voto_pk PRIMARY KEY (Usuario_correo,Iniciativa_id)
);

-- foreign keys
-- Reference: Comentario_Iniciativa (table: Interes)
ALTER TABLE Interes ADD CONSTRAINT Comentario_Iniciativa
    FOREIGN KEY (Iniciativa_id)
    REFERENCES Iniciativa (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Comentario_Usuario (table: Interes)
ALTER TABLE Interes ADD CONSTRAINT Comentario_Usuario
    FOREIGN KEY (Usuario_correo)
    REFERENCES Usuario (correo)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Iniciativa_Estado (table: Iniciativa)
ALTER TABLE Iniciativa ADD CONSTRAINT Iniciativa_Estado
    FOREIGN KEY (Estado_id)
    REFERENCES Estado (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Iniciativa_Usuario (table: Iniciativa)
ALTER TABLE Iniciativa ADD CONSTRAINT Iniciativa_Usuario
    FOREIGN KEY (Usuario_correo)
    REFERENCES Usuario (correo)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Usuario_Area (table: Usuario)
ALTER TABLE Usuario ADD CONSTRAINT Usuario_Area
    FOREIGN KEY (Area_id)
    REFERENCES Area (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Usuario_Rol (table: Usuario)
ALTER TABLE Usuario ADD CONSTRAINT Usuario_Rol
    FOREIGN KEY (Rol_id)
    REFERENCES Rol (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Voto_Iniciativa (table: Voto)
ALTER TABLE Voto ADD CONSTRAINT Voto_Iniciativa
    FOREIGN KEY (Iniciativa_id)
    REFERENCES Iniciativa (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Voto_Usuario (table: Voto)
ALTER TABLE Voto ADD CONSTRAINT Voto_Usuario
    FOREIGN KEY (Usuario_correo)
    REFERENCES Usuario (correo)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

