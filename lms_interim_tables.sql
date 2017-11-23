CREATE TABLE course (
    course_id INTEGER NOT NULL
    GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    course_name VARCHAR(80),
    semester VARCHAR(10),
    CONSTRAINT course_pk PRIMARY KEY (course_id)
);

CREATE TABLE user_role ( 
    user_role_id INTEGER NOT NULL
    GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    user_role_name VARCHAR(20) NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (user_role_id)
);

CREATE TABLE t_user (
    user_id INTEGER NOT NULL
    GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    user_name VARCHAR(80) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INTEGER NOT NULL REFERENCES user_role(user_role_id),
    CONSTRAINT user_pk PRIMARY KEY (user_id)
);

CREATE TABLE user_course (
    user_id INTEGER NOT NULL REFERENCES t_user(user_id),
    course_id INTEGER NOT NULL REFERENCES course(course_id),
    CONSTRAINT user_course_pk PRIMARY KEY (user_id, course_id)
);

CREATE TABLE assignment_type (
    assignment_type_id INTEGER NOT NULL
    GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    course_id INTEGER NOT NULL REFERENCES course(course_id),
    assignment_type_name VARCHAR(20) NOT NULL,
    CONSTRAINT assignment_type_pk PRIMARY KEY (assignment_type_id)
);

CREATE TABLE assignment (
    assignment_id INTEGER NOT NULL
    GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    course_id INTEGER NOT NULL REFERENCES course(course_id),
    assignment_name VARCHAR(255) NOT NULL,
    assignment_description VARCHAR(4000),
    assignment_type_id INTEGER REFERENCES assignment_type(assignment_type_id),
    graded BOOLEAN NOT NULL,
    potential_score INTEGER,
    CONSTRAINT assignment_pk PRIMARY KEY (assignment_id)
);

CREATE TABLE user_assignment (
    assignment_id INTEGER NOT NULL
    REFERENCES assignment(assignment_id),
    user_id INTEGER NOT NULL REFERENCES t_user(user_id),
    user_assignment_submission VARCHAR(4000) NOT NULL,
    score INTEGER,
    submitted BOOLEAN,
    late BOOLEAN,
    CONSTRAINT user_assignment_pk PRIMARY KEY (assignment_id, user_id)
);