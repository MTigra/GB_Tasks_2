CREATE TABLE students
(
    id              BIGSERIAL PRIMARY KEY,
    name            TEXT NOT NULL,
    passport_ser    NUMERIC(4),
    passport_number NUMERIC(6),
    UNIQUE (passport_ser, passport_number)
);


CREATE TABLE subjects
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);


CREATE TABLE progress
(
    id         BIGSERIAL PRIMARY KEY,
    student_id BIGINT REFERENCES students (id) ON DELETE CASCADE,
    subject_id BIGINT,
    mark       NUMERIC(1)
        CONSTRAINT valid_mark CHECK ( mark >= 2 AND mark <= 5)
);

SELECT s.name
    FROM students AS s
             JOIN progress p ON s.id = p.student_id
    WHERE p.mark > 3
      AND p.subject_id = (SELECT id
                              FROM subjects
                              WHERE name = 'Русский язык');
SELECT avg(mark)
    FROM progress
    WHERE subject_id = (SELECT id
                            FROM subjects
                            WHERE name = 'Информатика');

SELECT avg(mark)
    FROM progress
    WHERE progress.student_id = 2;

SELECT name, count(name)
    FROM subjects AS subj
             JOIN progress ON subj.id = progress.subject_id
    WHERE mark > 2
    GROUP BY name
    ORDER BY count(name) DESC
    LIMIT 3;



INSERT INTO students
    (name, passport_number, passport_ser)
    VALUES
        ('st1', 249843, 2810),
        ('st2', 332003, 2415),
        ('st3', 322992, 4211);

INSERT INTO subjects
    (name)
    VALUES
        ('Математика'),
        ('Русский язык'),
        ('Информатика'),
        ('Японский язык');

INSERT INTO progress
    (student_id, subject_id, mark)
    VALUES
        (1, 1, 3),
        (1, 2, 4),
        (1, 3, 5),
        (1, 4, 2),
        (2, 1, 4),
        (2, 2, 3),
        (2, 3, 2),
        (2, 4, 3),
        (3, 1, 3),
        (3, 2, 4),
        (3, 3, 3);

