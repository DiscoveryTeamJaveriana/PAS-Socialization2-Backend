-- Database: despachos_db
-- DROP DATABASE despachos_db;
CREATE DATABASE despachos_db
    WITH 
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: despachos
-- DROP SCHEMA despachos ;

CREATE SCHEMA despachos AUTHORIZATION discoveryteam;

-- Table: despachos.usuario
--DROP TABLE despachos.usuario;
CREATE TABLE despachos.usuario
(
    apellidos character varying(50) COLLATE pg_catalog."default",
    correo character varying(70) COLLATE pg_catalog."default",
    direccion character varying(100) COLLATE pg_catalog."default",
    id bigint NOT NULL,
    rol character varying(20) COLLATE pg_catalog."default",
    nombreUsuario character varying(20) COLLATE pg_catalog."default",
    nombres character varying(50) COLLATE pg_catalog."default",
    telefono character varying(13) COLLATE pg_catalog."default",
    contrasena character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE despachos.usuario
    OWNER to discoveryteam;

-- Table: despachos.despacho
--DROP TABLE despachos.despacho;
CREATE TABLE despachos.despacho
(
    id SERIAL NOT NULL,
    cantidad_cajas bigint,
    peso_total numeric,
    estado character varying(20) COLLATE pg_catalog."default",
    id_usuario_destino bigint,
    CONSTRAINT despacho_pkey PRIMARY KEY (id),
    CONSTRAINT id_usuario_destino FOREIGN KEY (id_usuario_destino)
        REFERENCES despachos.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE despachos.despacho
    OWNER to discoveryteam;
	
-- Table: despachos.oferta
--DROP TABLE despachos.oferta;
CREATE TABLE despachos.oferta
(
    id SERIAL NOT NULL,
    id_despacho bigint,
    id_usuario_transporte bigint,
    oferta numeric,
    CONSTRAINT oferta_pkey PRIMARY KEY (id),
    CONSTRAINT id_despacho FOREIGN KEY (id_despacho)
        REFERENCES despachos.despacho (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT id_usuario_transporte FOREIGN KEY (id_usuario_transporte)
        REFERENCES despachos.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE despachos.oferta
    OWNER to discoveryteam;

INSERT INTO despachos.usuario (apellidos, correo, direccion, id, rol, nombreusuario, nombres, telefono, contrasena)
VALUES('coord', 'coord', 'coord', 1, 'SHIPPER', 'Coordinadora', 'coord', '123', '123'),
      ('tcc', 'tcc', 'tcc', 2, 'SHIPPER', 'TCC', 'tcc', '123', '123');
