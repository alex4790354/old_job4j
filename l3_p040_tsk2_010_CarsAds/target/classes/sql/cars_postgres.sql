CREATE DATABASE web_cars
    WITH
    OWNER = cars_web
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


CREATE TABLE customer
(
    id SERIAL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    password character varying(50),
    CONSTRAINT customer_pk PRIMARY KEY(id),
    CONSTRAINT customer_email_uniqe UNIQUE (email)
);

INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'David','Adams', 'admin@job4j.com' );
INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'John','Doe',    'john@job4j.com' );
INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'Ajay','Rao',    'ajay@job4j.com' );
INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'Mary','Public', 'mary@job4j.com' );
INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'Maxwell','Dixon','max@job4j.com' );


CREATE TABLE cars_brand (
   id            SERIAL,
   name          CHARACTER VARYING(100),
   CONSTRAINT cars_brand_pk PRIMARY KEY (id)
);
INSERT INTO cars_brand (name) VALUES ('Hyundai');
INSERT INTO cars_brand (name) VALUES ('Toyota');
INSERT INTO cars_brand (name) VALUES ('KIA');

CREATE TABLE cars_model (
   id            SERIAL,
   brand_id      INTEGER REFERENCES cars_brand (id),
   name          CHARACTER VARYING(100),
CONSTRAINT cars_model_pk PRIMARY KEY (id)
);
INSERT INTO cars_model (brand_id, name) VALUES (1 , 'Solaris');
INSERT INTO cars_model (brand_id, name) VALUES (1 , 'Starex');
INSERT INTO cars_model (brand_id, name) VALUES (1 , 'Tucson');
INSERT INTO cars_model (brand_id, name) VALUES (2 , 'Camry');
INSERT INTO cars_model (brand_id, name) VALUES (2 , 'RAV4');
INSERT INTO cars_model (brand_id, name) VALUES (2 , 'Land Cruiser Prado');
INSERT INTO cars_model (brand_id, name) VALUES (3 , 'Rio');
INSERT INTO cars_model (brand_id, name) VALUES (3 , 'Optima');
INSERT INTO cars_model (brand_id, name) VALUES (3 , 'Soul');


CREATE TABLE cars_body_type (
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_body_type_pk PRIMARY KEY (id)
);
INSERT INTO cars_body_type (name) VALUES ('sedan');
INSERT INTO cars_body_type (name) VALUES ('chitchback');
INSERT INTO cars_body_type (name) VALUES ('wagon');
INSERT INTO cars_body_type (name) VALUES ('SUV');
INSERT INTO cars_body_type (name) VALUES ('minivan');


CREATE TABLE cars_transmission(
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_transmission_pk PRIMARY KEY (id)
);
INSERT INTO cars_transmission(name) VALUES ('auto');
INSERT INTO cars_transmission(name) VALUES ('manual');
INSERT INTO cars_transmission(name) VALUES ('robot');
INSERT INTO cars_transmission(name) VALUES ('variable speed drive');


CREATE TABLE cars_engine_type(
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_engine_type_pk PRIMARY KEY (id)
);
INSERT INTO cars_engine_type(name) VALUES ('petrol');
INSERT INTO cars_engine_type(name) VALUES ('diesel');
INSERT INTO cars_engine_type(name) VALUES ('gas');


CREATE TABLE cars_drive_unit(
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_drive_unit_pk PRIMARY KEY (id)
);
INSERT INTO cars_drive_unit(name) VALUES ('front');
INSERT INTO cars_drive_unit(name) VALUES ('rear');
INSERT INTO cars_drive_unit(name) VALUES ('full');


/*CREATE TABLE cars_heating (
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_heating_pk PRIMARY KEY (id)
);
INSERT INTO cars_heating (name) VALUES ('front seats');
INSERT INTO cars_heating (name) VALUES ('mirrors');
INSERT INTO cars_heating (name) VALUES ('rear window');
INSERT INTO cars_heating (name) VALUES ('steering wheel');*/

CREATE TABLE cars_photo (
   id              SERIAL,
   ad_id           INTEGER,
CONSTRAINT cars_photos_pk PRIMARY KEY (id) --REFERENCES cars_ad(id)
);

INSERT INTO cars_photo(id) VALUES(0);


CREATE TABLE cars_ad (
   id                    SERIAL,
   cars_brand_id         INTEGER REFERENCES cars_brand(id),
   cars_model_id         INTEGER REFERENCES cars_model(id),
   cars_body_type_id     INTEGER REFERENCES cars_body_type(id),
   cars_transmission_id  INTEGER REFERENCES cars_transmission(id),
   cars_engine_type_id   INTEGER REFERENCES cars_engine_type(id),
   cars_drive_unit_id    INTEGER REFERENCES cars_drive_unit(id),
   mileage               INTEGER,
   description           CHARACTER VARYING(200),
   user_id               INTEGER REFERENCES customer(id),
   photo_id              INTEGER,
   status                CHARACTER VARYING(5) DEFAULT 'Y',         -- Y - for sale, N - NOT for sale. Can be M - moderator, H - hold, VIP etc.
   inserted_date         TIMESTAMP,
    CONSTRAINT cars_ad_pk PRIMARY KEY (id)
);

INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,1,2,1,1,1,1000 ,'Descr-1', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,2,5,2,2,2,20000,'Descr-2', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,3,4,3,3,3,30000,'Descr-3', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,4,1,4,1,1,40000,'Descr-4', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,5,4,1,2,3,50000,'Descr-5', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,6,4,2,1,3,60000,'Descr-6', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,7,1,3,2,1,70000,'Descr-7', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,8,2,4,3,2,80000,'Descr-8', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,9,3,1,3,2,90000,'Descr-9', 1, 0, CURRENT_TIMESTAMP(0));


ALTER TABLE cars_photo ADD CONSTRAINT cars_photo_fk1  FOREIGN KEY  (ad_id) REFERENCES cars_ad(id);

INSERT INTO cars_photo(id, ad_id) VALUES(1,1);
INSERT INTO cars_photo(id, ad_id) VALUES(2,1);
INSERT INTO cars_photo(id, ad_id) VALUES(3,1);
INSERT INTO cars_photo(id, ad_id) VALUES(4,2);
INSERT INTO cars_photo(id, ad_id) VALUES(5,2);
INSERT INTO cars_photo(id, ad_id) VALUES(6,2);
INSERT INTO cars_photo(id, ad_id) VALUES(7,2);
INSERT INTO cars_photo(id, ad_id) VALUES(8,2);
INSERT INTO cars_photo(id, ad_id) VALUES(9,2);
INSERT INTO cars_photo(id, ad_id) VALUES(10,3);
INSERT INTO cars_photo(id, ad_id) VALUES(11,3);
INSERT INTO cars_photo(id, ad_id) VALUES(12,3);
INSERT INTO cars_photo(id, ad_id) VALUES(13,3);
INSERT INTO cars_photo(id, ad_id) VALUES(14,3);
INSERT INTO cars_photo(id, ad_id) VALUES(15,3);
INSERT INTO cars_photo(id, ad_id) VALUES(16,8);
INSERT INTO cars_photo(id, ad_id) VALUES(17,8);
INSERT INTO cars_photo(id, ad_id) VALUES(18,8);
INSERT INTO cars_photo(id, ad_id) VALUES(19,8);
INSERT INTO cars_photo(id, ad_id) VALUES(20,7);
INSERT INTO cars_photo(id, ad_id) VALUES(21,7);
INSERT INTO cars_photo(id, ad_id) VALUES(22,7);
INSERT INTO cars_photo(id, ad_id) VALUES(23,7);
INSERT INTO cars_photo(id, ad_id) VALUES(24,7);
INSERT INTO cars_photo(id, ad_id) VALUES(25,9);
INSERT INTO cars_photo(id, ad_id) VALUES(26,9);
INSERT INTO cars_photo(id, ad_id) VALUES(27,9);
INSERT INTO cars_photo(id, ad_id) VALUES(28,9);
INSERT INTO cars_photo(id, ad_id) VALUES(29,9);
INSERT INTO cars_photo(id, ad_id) VALUES(30,9);
INSERT INTO cars_photo(id, ad_id) VALUES(31,4);
INSERT INTO cars_photo(id, ad_id) VALUES(32,4);
INSERT INTO cars_photo(id, ad_id) VALUES(33,4);
INSERT INTO cars_photo(id, ad_id) VALUES(34,4);
INSERT INTO cars_photo(id, ad_id) VALUES(35,4);
INSERT INTO cars_photo(id, ad_id) VALUES(36,4);
INSERT INTO cars_photo(id, ad_id) VALUES(37,4);
INSERT INTO cars_photo(id, ad_id) VALUES(38,6);
INSERT INTO cars_photo(id, ad_id) VALUES(39,6);
INSERT INTO cars_photo(id, ad_id) VALUES(40,6);
INSERT INTO cars_photo(id, ad_id) VALUES(41,6);
INSERT INTO cars_photo(id, ad_id) VALUES(42,6);
INSERT INTO cars_photo(id, ad_id) VALUES(43,6);
INSERT INTO cars_photo(id, ad_id) VALUES(44,5);
INSERT INTO cars_photo(id, ad_id) VALUES(45,5);
INSERT INTO cars_photo(id, ad_id) VALUES(46,5);
INSERT INTO cars_photo(id, ad_id) VALUES(47,5);
INSERT INTO cars_photo(id, ad_id) VALUES(48,5);


update cars_ad SET photo_id = 1  WHERE id = 1;
update cars_ad SET photo_id = 4  WHERE id = 2;
update cars_ad SET photo_id = 10 WHERE id = 3;
update cars_ad SET photo_id = 31 WHERE id = 4;
update cars_ad SET photo_id = 44 WHERE id = 5;
update cars_ad SET photo_id = 38 WHERE id = 6;
update cars_ad SET photo_id = 20 WHERE id = 7;
update cars_ad SET photo_id = 16 WHERE id = 8;
update cars_ad SET photo_id = 25 WHERE id = 9;



/*CREATE TABLE cars_ad_heating_details (
    id              SERIAL,
    add_id          INTEGER REFERENCES cars_ad(id),
    cars_heating_id INTEGER REFERENCES cars_heating(id),
    CONSTRAINT cars_ad_heating_details_pk PRIMARY KEY (id)
);

INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (1, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (2, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (2, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (3, 2);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (3, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (3, 4);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (4, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (4, 2);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (4, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (4, 4);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (5, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (5, 2);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (5, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (6, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (6, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (7, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (8, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (8, 2);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (8, 4);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (9, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (9, 4);*/





