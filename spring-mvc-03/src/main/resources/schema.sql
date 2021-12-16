CREATE TABLE accounts
(
  id INTEGER AUTO_INCREMENT,
  holder VARCHAR(255),
  balance INTEGER,
  CONSTRAINT pk_accounts PRIMARY KEY (id)
);
