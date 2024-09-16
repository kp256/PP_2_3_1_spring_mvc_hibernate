CREATE TABLE users
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(50),
    surname VARCHAR(50),
    email   VARCHAR(100) UNIQUE
);
