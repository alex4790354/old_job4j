CREATE TABLE public.users
(
   id SERIAL PRIMARY KEY,
   login character varying(50),
   password character varying(50),
   name character varying(200),
   inserted_date TIMESTAMP,
   email character varying(200)
);

INSERT INTO users (login, password, name, inserted_date, email) VALUES('alex', '123', 'Alex', CURRENT_TIMESTAMP(0), 'alex@mail.ru');
INSERT INTO users (login, password, name, inserted_date, email) VALUES('agent007', '123', 'Agent 007', CURRENT_TIMESTAMP(0), '007@mail.ru');
INSERT INTO users (login, password, name, inserted_date, email) VALUES('agent009', '123', 'Agent KGB', CURRENT_TIMESTAMP(0), 'kgb@mail.ru');
