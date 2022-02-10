CREATE TABLE soda_details(id IDENTITY PRIMARY KEY, name VARCHAR(255), quantity INT(10));
CREATE TABLE transaction_details(id IDENTITY PRIMARY KEY, soda_detail_id Integer(10), despence_date TIMESTAMP);
INSERT INTO soda_details (id,name, quantity) VALUES (1,'Orange', 50);
