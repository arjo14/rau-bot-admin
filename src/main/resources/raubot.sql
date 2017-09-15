
DROP TABLE IF EXISTS dayoff;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS userEntity;
DROP TABLE IF EXISTS `group`;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS institute;
DROP TABLE IF EXISTS lessontype;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS subject;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS timetable;
DROP TABLE IF EXISTS weekDay;


CREATE TABLE `Institute` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `instituteId` INT NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `deptId` INT NOT NULL,
  `groupNum` INT NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `groupId` INT NOT NULL,
  `subId` INT NOT NULL,
  `teacherId` INT NOT NULL,
  `weekDay` INT NOT NULL,
  `lessonId` INT NOT NULL,
  `roomId` INT NOT NULL,
  `lessonType` INT NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL ,
  PRIMARY KEY (`id`)
);

CREATE TABLE `TimeTable` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lesson` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `WeekDay` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `day` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Teacher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `Room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(64),
  PRIMARY KEY (`id`)
);

CREATE TABLE `LessonType` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `DayOff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(64),
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `groupId` INT NOT NULL,
  `chatId` INT,
  PRIMARY KEY (`id`)
);


ALTER TABLE `Department` ADD CONSTRAINT `Department_fk0` FOREIGN KEY (`instituteId`) REFERENCES `Institute`(`id`);

ALTER TABLE `Group` ADD CONSTRAINT `Group_fk0` FOREIGN KEY (`deptId`) REFERENCES `Department`(`id`);

ALTER TABLE `Schedule` ADD CONSTRAINT `Schedule_fk0` FOREIGN KEY (`groupId`) REFERENCES `Group`(`id`);

ALTER TABLE `Schedule` ADD CONSTRAINT `Schedule_fk1` FOREIGN KEY (`subId`) REFERENCES `Subject`(`id`);

ALTER TABLE `Schedule` ADD CONSTRAINT `Schedule_fk2` FOREIGN KEY (`teacherId`) REFERENCES `Teacher`(`id`);

ALTER TABLE `Schedule` ADD CONSTRAINT `Schedule_fk3` FOREIGN KEY (`lessonId`) REFERENCES `TimeTable`(`id`);

ALTER TABLE `Schedule` ADD CONSTRAINT `Schedule_fk4` FOREIGN KEY (`weekDay`) REFERENCES `WeekDay`(`id`);

ALTER TABLE `Schedule` ADD CONSTRAINT `Schedule_fk5` FOREIGN KEY (`roomId`) REFERENCES `Room`(`id`);

ALTER TABLE `Schedule` ADD CONSTRAINT `Schedule_fk6` FOREIGN KEY (`lessonType`) REFERENCES `LessonType`(`id`);

ALTER TABLE `User` ADD CONSTRAINT `User_fk0` FOREIGN KEY (`groupId`) REFERENCES `Group`(`id`);

insert into institute (name) values('ИМВТ');
insert into institute (name) values('ИНЭКБИЗ');
insert into institute (name) values('ИПП');
insert into institute (name) values('ИМРК');
insert into institute (name) values('ИГН');

insert into department (instituteId , name) values(1,'ПМИ');

insert into subject (name) value ('Специальный курс(Введение в теорию нейронных сетей)');
insert into subject (name) value ('Яз.прогр.и мет. трансляции(проработка)');
insert into subject (name) value ('Яз. и мет. прогр.(Mat 5)');
insert into subject (name) value ('Прогр. в среде Linux');
insert into subject (name) value ('Физика(лекция)');
insert into subject (name) value ('Комбинаторные алгоритмы(лекция)');
insert into subject (name) value ('Теория вероятности (лекция)');
insert into subject (name) value ('Теория вероятности (проработка)');
insert into subject (name) value ('Специальный курс(мат. методы анализа алгоритмов)');
insert into subject (name) value ('Яз.прогр.и мет. трансляции(лекция)');
insert into subject (name) value ('Диф. ур (лекция)');
insert into subject (name) value ('Диф. ур (проработка)');
insert into subject (name) value ('Физика(проработка)');
insert into subject (name) value ('Комбинаторные алгоритмы(проработка)');
insert into subject (name) value ('Экономика');
insert into subject (name) value ('Спец. курс  MMM (Big Data)');

insert into teacher (name) value('Ваградян В.Г.');
insert into teacher (name) value('Варданян Г.А.');
insert into teacher (name) value('Саркисян М.А.');
insert into teacher (name) value('Маилян С.С.');
insert into teacher (name) value('Беджанян А.Р.');
insert into teacher (name) value('Тоноян Р.Н.');
insert into teacher (name) value('Арамян Р.Н.');
insert into teacher (name) value('Нигиян С.А.');
insert into teacher (name) value('Казарян Г.Г.');
insert into teacher (name) value('Дарбинян А.А.');
insert into teacher (name) value('Атаян К.И.');
insert into teacher (name) value('Пилипосян Т.Э.');
insert into teacher (name) value('Чахоян Э.М.');
insert into teacher (name) value('Бардахчян В.Г.');
insert into teacher (name) value('Манукян М.М.');
insert into teacher (name) value('Микилян М.А.');
insert into teacher (name) value('Арутюнян К.В.');
insert into teacher (name) value('Агаджанян А.В.');


insert into timetable (lesson) value('1 пара');
insert into timetable (lesson) value('2 пара');
insert into timetable (lesson) value('3 пара');
insert into timetable (lesson) value('4 пара');
insert into timetable (lesson) value('5 пара');
insert into timetable (lesson) value('6 пара');


insert into room (name) value('200');
insert into room (name) value('229');
insert into room (name) value('300');
insert into room (name) value('301');
insert into room (name) value('303');
insert into room (name) value('305');
insert into room (name) value('307');
insert into room (name) value('309');
insert into room (name) value('310');
insert into room (name) value('312');
insert into room (name) value('313');
insert into room (name) value('315');
insert into room (name) value('319');
insert into room (name) value('310');
insert into room (name) value('319');
insert into room (name) value('322');
insert into room (name) value('конф зал имени departmentСаркисяна Ф.Т.');

insert into weekDay (day) value('Понедельник');
insert into weekDay (day) value('Вторник');
insert into weekDay (day) value('Среда');
insert into weekDay (day) value('Четверг');
insert into weekDay (day) value('Пятница');
insert into weekDay (day) value('Суббота');


insert into lessonType (name) value('Every week');
insert into lessonType (name) value('In every even week');
insert into lessonType (name) value('In every odd week');
insert into lessonType (name) value('Liquidity');

insert into `group` (deptId,groupNum) values(1,1);
insert into `group` (deptId,groupNum) values(1,2);
insert into `group` (deptId,groupNum) values(1,3);
insert into `group` (deptId,groupNum) values(1,4);
insert into `group` (deptId,groupNum) values(1,5);



insert into `userEntity` (name,groupId) values('John Vahanyan',3);


