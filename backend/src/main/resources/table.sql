CREATE TABLE students (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    district VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE files (
    id UUID NOT NULL PRIMARY KEY,
    studentId UUID NOT NULL,
    file LONGVARCHAR
)