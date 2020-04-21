CREATE TABLE instructor (
                            id          SERIAL,
                            first_name  character varying(250) NOT NULL,
                            last_name   character varying(250) NOT NULL,
                            email       character varying(250) NOT NULL,
                            CONSTRAINT instructor_pk PRIMARY KEY(id),
                            CONSTRAINT instructor_email_uniqe UNIQUE (email)
);
INSERT INTO instructor (first_name, last_name, email) VALUES ('Alex',  'pupkin', 'Alex@mail.ru');
INSERT INTO instructor (first_name, last_name, email) VALUES ('Ivan',  'Mamkin', 'Ivan@mail.ru');
INSERT INTO instructor (first_name, last_name, email) VALUES ('Pasha', 'Sobin',  'Pash@mail.ru');


CREATE TABLE course (
                        id          SERIAL,
                        title  character varying(250) NOT NULL,
                        instructor_id  INTEGER REFERENCES instructor(id)
);
INSERT INTO course(title, instructor_id) VALUES('Mathematics', 1);
INSERT INTO course(title, instructor_id) VALUES('Phisics', 2);
INSERT INTO course(title, instructor_id) VALUES('English', 2);
INSERT INTO course(title, instructor_id) VALUES('German', 3);


CREATE TABLE employee (
                          id          SERIAL,
                          first_name  character varying(250) NOT NULL,
                          last_name   character varying(250) NOT NULL,
                          department  character varying(250) NOT NULL,
                          CONSTRAINT employee_pk PRIMARY KEY(id)
);
INSERT INTO employee(first_name, last_name, department) VALUES('Alex',  'pupkin', 'Alex@mail.ru');
INSERT INTO employee(first_name, last_name, department) VALUES('Ivan',  'Mamkin', 'Ivan@mail.ru');
INSERT INTO employee(first_name, last_name, department) VALUES('Pasha', 'Sobin',  'Pash@mail.ru');


CREATE TABLE account (
                         acct_id     SERIAL,
                         acct_no     character varying(250) NOT NULL,
                         emp_id      INTEGER REFERENCES employee(id),
                         CONSTRAINT account_pk PRIMARY KEY(acct_id),
                         CONSTRAINT account_no_uniqe UNIQUE (acct_no)
) ;
INSERT INTO account(acct_no, emp_id) VALUES('123456', 1);
INSERT INTO account(acct_no, emp_id) VALUES('234567', 2);
INSERT INTO account(acct_no, emp_id) VALUES('345678', 2);
INSERT INTO account(acct_no, emp_id) VALUES('456789', 3);


CREATE TABLE company (
                         id          SERIAL,
                         name        character varying(250) NOT NULL,
                         CONSTRAINT company_pk PRIMARY KEY(id)
);
INSERT INTO company(name) VALUES('Adidas');
INSERT INTO company(name) VALUES('Apple');
INSERT INTO company(name) VALUES('Loblaws');


CREATE TABLE product (
                         id          SERIAL,
                         name        character varying(250) NOT NULL,
                         company_id  INTEGER REFERENCES company(id),
                         CONSTRAINT product_pk PRIMARY KEY(id)
) ;
INSERT INTO product(name, company_id) VALUES('Sneakers', 1);
INSERT INTO product(name, company_id) VALUES('Slates', 1);
INSERT INTO product(name, company_id) VALUES('Laptop', 2);
INSERT INTO product(name, company_id) VALUES('Phone', 2);
INSERT INTO product(name, company_id) VALUES('Orange', 3);
INSERT INTO product(name, company_id) VALUES('Strawberry', 3);

