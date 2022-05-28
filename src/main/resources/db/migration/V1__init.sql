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