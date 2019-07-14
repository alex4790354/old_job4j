CREATE TABLE public.users
(
   id SERIAL PRIMARY KEY,
   login character varying(50),
   password character varying(50),
   name character varying(200),
   --inserted_date TIMESTAMP,
   email character varying(200)
);

INSERT INTO users (login, password, name, email) VALUES('alexandr', '123', 'Alex',  'alex@mail.ru'); -- CURRENT_TIMESTAMP(0),
INSERT INTO users (login, password, name, email) VALUES('agent009', '123', 'Agent KGB', 'kgb@mail.ru');
INSERT INTO users (login, password, name, email) VALUES('agent007', '123', 'Agent 007', '007@mail.ru');
