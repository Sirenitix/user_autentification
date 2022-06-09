create table account (
     id INT,
     clientID VARCHAR(50),
     bankID INT,
     balance  DECIMAL(5,2),
     withdrawAllowed VARCHAR(50),
     accountType VARCHAR(50)
);

create table transaction (
    accountID VARCHAR(500),
    transaction VARCHAR(500),
    currentBalance DECIMAL(5,2)
);

create table users (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       username VARCHAR(250) NOT NULL,
                       password VARCHAR(250) NOT NULL,
                       role VARCHAR(250) NOT NULL,
                       enabled BOOLEAN
);