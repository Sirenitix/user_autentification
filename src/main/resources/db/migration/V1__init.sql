create table account (
     id INT,
     clientID VARCHAR(50),
     bankID INT,
     balance  DECIMAL(50),
     withdrawAllowed VARCHAR(50),
     accountType VARCHAR(50)
);

create table transaction (
    accountID VARCHAR(500),
    clientID VARCHAR(50),
    transaction VARCHAR(500),
    currentBalance DECIMAL(50)
);

create table user (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       username VARCHAR(250) NOT NULL,
                       password VARCHAR(250) NOT NULL,
                       role VARCHAR(250) NOT NULL,
                       enabled BOOLEAN
);