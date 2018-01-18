INSERT INTO table_user (username,email, password, activated) VALUES ('bucky', 'bucky@gmail.com', '$2a$10$u5cb3mq2g7RQlnj2JI2.OOQfUtas7E0qz014yOcauQ68lKp1khknK', true);

INSERT INTO table_role (name) VALUES ('ROLE_USER');
INSERT INTO table_role (name) VALUES ('ROLE_ADMIN');

INSERT INTO table_user_role (user_id,role_id) VALUES (1,1);

INSERT INTO table_user_role (user_id,role_id) VALUES (1,2);

INSERT INTO table_product (name) VALUES ('Mac');
INSERT INTO table_product (name) VALUES ('DEll');
INSERT INTO table_product (name) VALUES ('Hp');
