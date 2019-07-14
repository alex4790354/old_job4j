-- Оригинал примера здесь: https://www.postgresql.org/docs/9.2/ddl-constraints.html
-- POSTRGRESQL:

CREATE TABLE contact (
	  ID SERIAL PRIMARY KEY
	, first_name VARCHAR(60) NOT NULL
	, last_name VARCHAR(40) NOT NULL
	, birth_date DATE
	, version INT NOT NULL DEFAULT 0
);

INSERT INTO contact (first_name, last_name, birth_date, version ) Values ('Name0', 'LastName0', '2015-01-01', 0);
INSERT INTO contact (first_name, last_name, birth_date, version ) Values ('Name1', 'LastName1', '2015-02-02', 0);
INSERT INTO contact (first_name, last_name, birth_date, version ) Values ('Name2', 'LastName2', '2015-03-03', 0);
INSERT INTO contact (first_name, last_name, birth_date, version ) Values ('Name3', 'LastName3', '2015-04-04', 0);
INSERT INTO contact (first_name, last_name, birth_date, version ) Values ('Name4', 'LastName4', '2015-05-05', 0);

CREATE TABLE hobby (
	hobby_id VARCHAR(20) PRIMARY KEY
);

INSERT INTO hobby(hobby_id) VALUES('Football');
INSERT INTO hobby(hobby_id) VALUES('Movies');
INSERT INTO hobby(hobby_id) VALUES('Swiming');


CREATE TABLE contact_tel_detail(
	  id SERIAL PRIMARY KEY
	, contact_id INT NOT NULL REFERENCES contact (id)
	, tel_type VARCHAR(20) NOT NULL
	, tel_number VARCHAR(20) NOT NULL
	, version INT NOT NULL DEFAULT 0
);
INSERT INTO contact_tel_detail(contact_id, tel_type, tel_number, version) VALUES (1, 'Home', '8-800-123-4567', 0);
INSERT INTO contact_tel_detail(contact_id, tel_type, tel_number, version) VALUES (1, 'Work', '8-800-222-2222', 0);
INSERT INTO contact_tel_detail(contact_id, tel_type, tel_number, version) VALUES (2, 'Home', '8-800-333-3333', 0);
INSERT INTO contact_tel_detail(contact_id, tel_type, tel_number, version) VALUES (2, 'Work', '8-800-444-4444', 0);
INSERT INTO contact_tel_detail(contact_id, tel_type, tel_number, version) VALUES (3, 'Home', '8-800-555-5555', 0);
INSERT INTO contact_tel_detail(contact_id, tel_type, tel_number, version) VALUES (4, 'Work', '8-800-777-7777', 0);


-- REFERENCES products (product_no),
CREATE TABLE contact_hobby_detail (
	  contact_id INT NOT NULL REFERENCES contact (id)
	, hobby_id VARCHAR(20) NOT NULL REFERENCES hobby (hobby_id)
	, PRIMARY KEY (contact_id, hobby_id)
);
INSERT INTO contact_hobby_detail(contact_id, hobby_id) VALUES (1, 'Football');
INSERT INTO contact_hobby_detail(contact_id, hobby_id) VALUES (2, 'Football');
INSERT INTO contact_hobby_detail(contact_id, hobby_id) VALUES (4, 'Movies');
INSERT INTO contact_hobby_detail(contact_id, hobby_id) VALUES (5, 'Swiming');

---===========================================
---MySQL:

CREATE TABLE contact (
	  ID INT NOT NULL AUTO_INCREMENT
	, first_name VARCHAR(60) NOT NULL
	, last_name VARCHAR(40) NOT NULL
	, birth_date DATE
	, version INT NOT NULL DEFAULT 0
	, UNIQUE UQ_CONTACT_1 (first_name, last_name)
	, PRIMARY KEY (id)
);


CREATE TABLE hobby (
		hobby_id VARCHAR(20) NOT NULL
	, PRIMARY KEY (hobby_id)
);


CREATE TABLE contact_tel_detail(
		id INT NOT NULL AUTO_INCREMENT
	, contact_id INT NOT NULL
	, tel_type VARCHAR(20) NOT NULL
	, tel_number VARCHAR(20) NOT NULL
	, version INT NOT NULL DEFAULT 0
	, UNIQUE uq_contact_tel_detail_1 (contact_id, tel_type)
	, PRIMARY KEY (id)
	, CONSTRAINT FK_CONTACT_TEL_DETAIL_1 FOREIGN KEY (contact_id) REFERENCES javastudy.contact (id)
);

CREATE TABLE contact_hobby_detail (
	  contact_id INT NOT NULL
	, hobby_id VARCHAR(20) NOT NULL
	, PRIMARY KEY (contact_id, hobby_id)
	, CONSTRAINT FK_CONTACT_HOBBY_DETAIL_1 FOREIGN KEY (contact_id) REFERENCES javastudy.contact (id) ON DELETE CASCADE
	, CONSTRAINT FK_CONTACT_HOBBY_DETAIL_2 FOREIGN KEY (hobby_id) REFERENCES javastudy.hobby (hobby_id)
);

