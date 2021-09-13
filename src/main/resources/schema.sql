CREATE TABLE IF NOT EXISTS DAILYREPORT(

  id INT(2) NOT NULL AUTO_INCREMENT,
  stuff_id INT(2) NOT NULL,
  work_id INT(2) NOT NULL,
  type_id INT(2) NOT NULL,
  created VARCHAR NOT NULL,
  startTime VARCHAR NOT NULL,
  endTime VARCHAR NOT NULL,
  detail VARCHAR(20) NOT NULL,
  name VARCHAR(20) NULL,
  PRIMARY KEY(id)

);

CREATE TABLE IF NOT EXISTS DAILYREPORT_TYPE(

  id INT NOT NULL,
  progress VARCHAR NOT NULL
 
);



CREATE TABLE IF NOT EXISTS  STUFF(
 
  id INT(2) NOT NULL AUTO_INCREMENT,
  type_id INT(2),
  name VARCHAR(20) ,
  detail VARCHAR(20),
  registeredId VARCHAR(20) NOT NULL,
  PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS  SUPPLIER(

 id INT(2) NOT NULL AUTO_INCREMENT,
 type_id INT NOT NULL,
 supplier VARCHAR(20) NOT NULL,
 comment VARCHAR(10) NOT NULL,
 PRIMARY KEY(id)
 
);


CREATE TABLE IF NOT EXISTS WORK(
  id INT(2) NOT NULL AUTO_INCREMENT,
  type_id INT(2) ,
  comment VARCHAR(20),
  workDivId VARCHAR(20) NOT NULL,
  PRIMARY KEY(id) 
);

CREATE TABLE IF NOT EXISTS WORK_TYPE(
  id INT(2) NOT NULL,
  type VARCHAR NOT NULL
  
);

CREATE TABLE IF NOT EXISTS WORKING_HOUR(
  id INT(2) NOT NULL AUTO_INCREMENT,
  type_id INT(2),
  stuff_id INT(2),
  work_id INT(2),
  workTime INT(2),
  PRIMARY KEY(id)
);
