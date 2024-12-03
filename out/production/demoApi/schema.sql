DROP TABLE IF EXISTS characters;

CREATE TABLE characters
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    status  VARCHAR(255),
    species VARCHAR(255),
    type    VARCHAR(255),
    gender  VARCHAR(255)
);

INSERT INTO characters (name, status, species, type, gender)
VALUES
    ('Rick Sanchez', 'Alive', 'Human', 'Scientist', 'Male'),
    ('Morty Smith', 'Alive', 'Human', 'Student', 'Male'),
    ('Summer Smith', 'Alive', 'Human', 'Student', 'Female');