--
-- Database: `schoolmanagementsystem_db`
--

CREATE DATABASE IF NOT EXISTS `schoolmanagementsystem_db`;
USE `schoolmanagementsystem_db`;


-- ENTITIES

--
-- Struttura della tabella `course`
--

CREATE TABLE IF NOT EXISTS `course` (
	`name` varchar(130)  NOT NULL,
	
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);


--
-- Struttura della tabella `exam`
--

CREATE TABLE IF NOT EXISTS `exam` (
	`place` varchar(130) ,
	`score` numeric ,
	`valid` bool ,
	
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);


--
-- Struttura della tabella `student`
--

CREATE TABLE IF NOT EXISTS `student` (
	`dateofbirth` date ,
	`name` varchar(130) ,
	`surname` varchar(130) ,
	
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);


--
-- Struttura della tabella `teacher`
--

CREATE TABLE IF NOT EXISTS `teacher` (
	`name` varchar(130) ,
	`surname` varchar(130) ,
	
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);





-- relation 1:m _course exam - course
ALTER TABLE `exam` ADD COLUMN `_course` int(11)  REFERENCES course(_id);

-- relation 1:m _student exam - student
ALTER TABLE `exam` ADD COLUMN `_student` int(11)  REFERENCES student(_id);

-- relation 1:m _teacher exam - teacher
ALTER TABLE `exam` ADD COLUMN `_teacher` int(11)  REFERENCES teacher(_id);

-- relation m:m _courses student - course
CREATE TABLE IF NOT EXISTS `student__courses` (
    `_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `id_student` int(11)  NOT NULL REFERENCES student(_id),
    `id_course` int(11)  NOT NULL REFERENCES course(_id)
);

-- relation m:m _courses teacher - course
CREATE TABLE IF NOT EXISTS `teacher__courses` (
    `_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `id_teacher` int(11)  NOT NULL REFERENCES teacher(_id),
    `id_course` int(11)  NOT NULL REFERENCES course(_id)
);


