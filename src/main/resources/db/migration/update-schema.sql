ALTER TABLE events
DROP
FOREIGN KEY fk_events_company1;

ALTER TABLE incident
DROP
FOREIGN KEY fk_incident_employee1;

DROP TABLE company;

DROP TABLE employee;

ALTER TABLE events
DROP
COLUMN companyid;

ALTER TABLE incident
DROP
COLUMN employeeid;

ALTER TABLE company
DROP
COLUMN id;

ALTER TABLE employee
DROP
COLUMN id;