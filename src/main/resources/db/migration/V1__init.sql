create table users (
                       id SERIAL NOT NULL ,
                       username TEXT NOT NULL,
                       password TEXT NOT NULL,
                       role TEXT NOT NULL,
                       enabled BOOLEAN,
                       PRIMARY KEY(id)
);

create table task (
                       id BIGINT NOT NULL ,
                       user_id BIGINT NOT NULL ,
                       description TEXT NOT NULL,
                       name TEXT NOT NULL
);