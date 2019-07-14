CREATE TABLE public.users
(
   id SERIAL PRIMARY KEY,
   login character varying(50),
   password character varying(50),
   name character varying(200),
   email character varying(200),
   inserted_date TIMESTAMP
);
INSERT INTO users (login, password, name, inserted_date, email) VALUES('alex', '123', 'Alex', CURRENT_TIMESTAMP(0), 'alex@mail.ru');
INSERT INTO users (login, password, name, inserted_date, email) VALUES('agent007', '123', 'Agent 007', CURRENT_TIMESTAMP(0), '007@mail.ru');
INSERT INTO users (login, password, name, inserted_date, email) VALUES('agent009', '123', 'Agent KGB', CURRENT_TIMESTAMP(0), 'kgb@mail.ru');


CREATE TABLE cars_brand (
   id            SERIAL PRIMARY KEY,
   name          CHARACTER VARYING(100)
);
INSERT INTO cars_brand (name) VALUES ('Hyundai');
INSERT INTO cars_brand (name) VALUES ('Toyota');
INSERT INTO cars_brand (name) VALUES ('KIA');

CREATE TABLE cars_model (
   id            SERIAL PRIMARY KEY,
   brand_id      INTEGER REFERENCES cars_brand (id),
   name          CHARACTER VARYING(100)
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
   id            SERIAL PRIMARY KEY,
   name          CHARACTER VARYING(100)
);
INSERT INTO cars_body_type (name) VALUES ('sedan');
INSERT INTO cars_body_type (name) VALUES ('chitchback');
INSERT INTO cars_body_type (name) VALUES ('wagon');
INSERT INTO cars_body_type (name) VALUES ('SUV');
INSERT INTO cars_body_type (name) VALUES ('minivan');


CREATE TABLE cars_transmission(
   id            SERIAL PRIMARY KEY,
   name          CHARACTER VARYING(100)
);
INSERT INTO cars_transmission(name) VALUES ('auto');
INSERT INTO cars_transmission(name) VALUES ('manual');
INSERT INTO cars_transmission(name) VALUES ('robot');
INSERT INTO cars_transmission(name) VALUES ('variable speed drive');


CREATE TABLE cars_engine_type(
   id            SERIAL PRIMARY KEY,
   name          CHARACTER VARYING(100)
);
INSERT INTO cars_engine_type(name) VALUES ('petrol');
INSERT INTO cars_engine_type(name) VALUES ('diesel');
INSERT INTO cars_engine_type(name) VALUES ('gas');


CREATE TABLE cars_drive_unit(
   id            SERIAL PRIMARY KEY,
   name          CHARACTER VARYING(100)
);
INSERT INTO cars_drive_unit(name) VALUES ('front');
INSERT INTO cars_drive_unit(name) VALUES ('rear');
INSERT INTO cars_drive_unit(name) VALUES ('full');


CREATE TABLE cars_heating (
   id            SERIAL PRIMARY KEY,
   name          CHARACTER VARYING(100)
);
INSERT INTO cars_heating (name) VALUES ('front seats');
INSERT INTO cars_heating (name) VALUES ('mirrors');
INSERT INTO cars_heating (name) VALUES ('rear window');
INSERT INTO cars_heating (name) VALUES ('steering wheel');

CREATE TABLE cars_photos (
   id              SERIAL PRIMARY KEY,
   ad_id           INTEGER REFERENCES cars_ads(id)
);

INSERT INTO cars_photos(id) VALUES(0);

CREATE TABLE cars_ads (
   id                    SERIAL PRIMARY KEY,
   cars_brand_id         INTEGER REFERENCES cars_brand(id),
   cars_model_id         INTEGER REFERENCES cars_model(id),
   cars_body_type_id     INTEGER REFERENCES cars_body_type(id),
   cars_transmission_id  INTEGER REFERENCES cars_transmission(id),
   cars_engine_type_id   INTEGER REFERENCES cars_engine_type(id),
   cars_drive_unit_id    INTEGER REFERENCES cars_drive_unit(id),
   mileage               INTEGER,
   description           CHARACTER VARYING(200),
   user_id               INTEGER REFERENCES users(id),
   photo_id              INTEGER REFERENCES cars_photos(id) DEFAULT 0,
   status                CHARACTER VARYING(5) DEFAULT 'Y',         -- Y - for sale, N - NOT for sale. Can be M - moderator, H - hold, VIP etc.
   inserted_date         TIMESTAMP
);
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,1,2,1,1,1,1000 ,'Descr-1', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,2,5,2,2,2,20000,'Descr-2', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,3,4,3,3,3,30000,'Descr-3', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,4,1,4,1,1,40000,'Descr-4', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,5,4,1,2,3,50000,'Descr-5', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,6,4,2,1,3,60000,'Descr-6', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,7,1,3,2,1,70000,'Descr-7', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,8,2,4,3,2,80000,'Descr-8', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ads(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,9,3,1,3,2,90000,'Descr-9', 1, 0, CURRENT_TIMESTAMP(0));


CREATE TABLE cars_ads_heating_details (
   id              SERIAL PRIMARY KEY,
   add_id          INTEGER REFERENCES cars_ads(id),
   cars_heating_id INTEGER REFERENCES cars_heating(id)
);

INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (1, 3);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (2, 3);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (2, 1);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (3, 2);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (3, 3);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (3, 4);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (4, 1);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (4, 2);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (4, 3);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (4, 4);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (5, 1);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (5, 2);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (5, 3);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (6, 1);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (6, 3);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (7, 3);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (8, 1);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (8, 2);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (8, 4);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (9, 1);
INSERT INTO cars_ads_heating_details (add_id, cars_heating_id) VALUES (9, 4);

