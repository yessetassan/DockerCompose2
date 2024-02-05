-- Create table for Student
CREATE TABLE student (
   id serial PRIMARY KEY,
   username VARCHAR(255) UNIQUE NOT NULL,
   email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE teacher (
  id serial PRIMARY KEY,
  username VARCHAR(255) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  birthday DATE
);

-- Create join table for the many-to-many relationship between Student and Teacher
CREATE TABLE student_teacher (
   student_id INT,
   teacher_id INT,
   PRIMARY KEY (student_id, teacher_id),
   CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(id),
   CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);
