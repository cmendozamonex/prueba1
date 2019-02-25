DROP TABLE IF EXISTS prueba1;

BEGIN;

CREATE TABLE prueba1(id serial PRIMARY KEY,string varchar);
INSERT INTO prueba1(string) VALUES('Valor 1'); 
INSERT INTO prueba1(string) VALUES('Valor 2'); 
INSERT INTO prueba1(string) VALUES('Valor 3'); 
INSERT INTO prueba1(string) VALUES('Valor 4'); 