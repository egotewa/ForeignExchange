DROP TABLE IF EXISTS TRANSACTIONS;

CREATE TABLE TRANSACTIONS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  date DATE,
  conversionType VARCHAR(10),
  rate DOUBLE
);