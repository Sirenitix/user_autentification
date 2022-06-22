create table users (
                       id SERIAL NOT NULL ,
                       username VARCHAR(250) NOT NULL,
                       password VARCHAR(250) NOT NULL,
                       role VARCHAR(250) NOT NULL,
                       enabled BOOLEAN,
                       PRIMARY KEY(id)
);