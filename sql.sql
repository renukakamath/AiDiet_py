/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - ai_diet
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ai_diet` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `ai_diet`;

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`sender_id`,`receiver_id`,`message`,`date`) values 
(1,2,1,'goof','2023-07-27'),
(2,2,1,'hai','2023-07-27'),
(3,2,1,'hi','2023-07-27'),
(4,2,1,'hello','2023-07-27'),
(5,1,2,'ggjj','2023-07-27'),
(6,2,1,'hi','2023-07-27'),
(7,2,5,'good','2023-07-27'),
(8,2,1,'good','2023-07-28'),
(9,9,2,'hi','2023-07-28'),
(10,2,5,'hi','2023-07-28'),
(11,2,2,'hai','2023-07-28'),
(12,2,2,'what can i do for u','2023-07-28'),
(13,2,2,'i want to lose weight','2023-07-28'),
(14,2,2,'ok','2023-07-29'),
(15,2,2,'hello ','2023-07-29'),
(16,2,2,'good morning','2023-07-29'),
(17,12,2,'hai','2023-07-29'),
(18,2,1,'ok','2023-08-07'),
(19,2,1,'good','2023-08-07'),
(20,2,4,'good','2023-08-07'),
(21,2,10,'good','2023-08-07'),
(22,2,5,'hau','2023-08-07'),
(23,2,11,'good','2023-08-07'),
(24,2,11,'hello','2023-08-07'),
(25,14,5,'good','2023-08-07'),
(26,14,5,'hello','2023-08-07'),
(29,5,14,'bla','2023-08-07'),
(30,14,5,'ft ff','2023-08-07'),
(31,2,5,'hello','2023-08-07'),
(32,2,5,'bla','2023-08-07'),
(33,2,4,'hai','2023-08-07'),
(34,14,5,'hello','2023-08-07'),
(35,5,14,'hai','2023-08-07'),
(36,2,5,'hai','2023-08-07'),
(37,2,5,'ok','2023-08-08');

/*Table structure for table `dietsrequest` */

DROP TABLE IF EXISTS `dietsrequest`;

CREATE TABLE `dietsrequest` (
  `dietsrequest_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `request` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dietsrequest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `dietsrequest` */

insert  into `dietsrequest`(`dietsrequest_id`,`userid`,`request`,`date`,`status`) values 
(1,1,'I want diet suggests ','2023-07-27','accept'),
(2,1,'I want diet suggests ','2023-07-27','accept'),
(9,1,'hii','2023-07-28','accept'),
(10,2,'weight loss','2023-07-29','accept'),
(11,10,'Renuka Kamath','2023-08-09','pending'),
(12,10,'dft','2023-08-09','pending');

/*Table structure for table `dietsuggested` */

DROP TABLE IF EXISTS `dietsuggested`;

CREATE TABLE `dietsuggested` (
  `suggested_id` int(11) NOT NULL AUTO_INCREMENT,
  `dietsrequest_id` int(11) DEFAULT NULL,
  `meal_id` int(11) DEFAULT NULL,
  `foodee_id` int(11) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `calories` varchar(100) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`suggested_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `dietsuggested` */

insert  into `dietsuggested`(`suggested_id`,`dietsrequest_id`,`meal_id`,`foodee_id`,`quantity`,`calories`,`comments`,`date`) values 
(5,10,1,1,2,'167','proteins =2% , fat= 3%, carbs= 2%,fiber= 1%','2023-08-07'),
(6,10,1,1,1,'95','proteins 1% , fat 2%, carbs 2%,fiber 1%','2023-08-07'),
(7,10,1,2,1,'123','	proteins =2% , fat= 3%, carbs= 2%,fiber= 1%','2023-08-07'),
(8,10,1,3,1,'79','	proteins =2% , fat= 3%, carbs= 2%,fiber= 1%','2023-08-07'),
(9,10,3,16,1,'134','	proteins =2% , fat= 3%, carbs= 3%,fiber= 3%','2023-08-07'),
(10,10,2,21,1,'156','	proteins =3% , fat= 3%, carbs= 3%,fiber= 3%','2023-08-07'),
(11,10,4,2,1,'123','	proteins =2% , fat= 3%, carbs= 2%,fiber= 1%','2023-08-07'),
(12,10,5,20,1,'123','	proteins =3% , fat= 3%, carbs= 3%,fiber= 3%','2023-08-07');

/*Table structure for table `discussiondetails` */

DROP TABLE IF EXISTS `discussiondetails`;

CREATE TABLE `discussiondetails` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `master_id` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `comment_description` varchar(55) DEFAULT NULL,
  `details_date` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `discussiondetails` */

insert  into `discussiondetails`(`detail_id`,`master_id`,`userid`,`comment_description`,`details_date`) values 
(1,1,1,'egh','1/1/20'),
(2,1,1,'ghh','2023-07-26');

/*Table structure for table `discussionmaster` */

DROP TABLE IF EXISTS `discussionmaster`;

CREATE TABLE `discussionmaster` (
  `master_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(55) DEFAULT NULL,
  `created_date` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `discussionmaster` */

insert  into `discussionmaster`(`master_id`,`title`,`created_date`) values 
(1,'related to food','2020-01-30'),
(2,'related to food','2020-01-30');

/*Table structure for table `donors` */

DROP TABLE IF EXISTS `donors`;

CREATE TABLE `donors` (
  `donorid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(55) DEFAULT NULL,
  `lname` varchar(55) DEFAULT NULL,
  `blood_group` varchar(55) DEFAULT NULL,
  `Dob` varchar(55) DEFAULT NULL,
  `gender` varchar(55) DEFAULT NULL,
  `phone` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`donorid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `donors` */

insert  into `donors`(`donorid`,`fname`,`lname`,`blood_group`,`Dob`,`gender`,`phone`) values 
(1,'neenu','rose','A+ve','28-6-1996','female','9876543214');

/*Table structure for table `excersice` */

DROP TABLE IF EXISTS `excersice`;

CREATE TABLE `excersice` (
  `video_id` int(11) NOT NULL AUTO_INCREMENT,
  `excersicesrequest_id` int(11) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `video` varchar(5000) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `ename` varchar(30) DEFAULT NULL,
  `edesc` varchar(100) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `count` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `excersice` */

insert  into `excersice`(`video_id`,`excersicesrequest_id`,`agent_id`,`video`,`date`,`ename`,`edesc`,`time`,`count`) values 
(5,1,2,'static/img8964dbe2-d2ae-41e4-8544-808212faff08istockphoto-1412651783-640_adpp_is.mp4','2023-07-29','push up','do 3 sets of 20 reps ,on morning and evening - everday','11:40','50'),
(6,1,2,'static/imga338709c-de20-4c11-90ca-f1b17d770cdcwar_-_61412 (360p).mp4','2023-08-07','expence name','dfsgfdgd','11:40','100');

/*Table structure for table `excersicesrequest` */

DROP TABLE IF EXISTS `excersicesrequest`;

CREATE TABLE `excersicesrequest` (
  `excersicesrequest_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `request` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`excersicesrequest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `excersicesrequest` */

insert  into `excersicesrequest`(`excersicesrequest_id`,`userid`,`request`,`date`,`status`) values 
(1,1,'exicisece request','2023-07-27','accept'),
(2,5,'ghjj','2023-07-27','accept'),
(3,10,'cvgh','2023-08-09','pending');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feed_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `Description` varchar(55) DEFAULT NULL,
  `reply` varchar(55) DEFAULT NULL,
  `feedback_date` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`feed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feed_id`,`userid`,`Description`,`reply`,`feedback_date`) values 
(1,1,'pls be fast','ok','30-1-2023'),
(2,2,'great app','pending','27-07-2023');

/*Table structure for table `food` */

DROP TABLE IF EXISTS `food`;

CREATE TABLE `food` (
  `food_id` int(11) NOT NULL AUTO_INCREMENT,
  `in_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `quantity` varchar(100) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `food` */

insert  into `food`(`food_id`,`in_id`,`user_id`,`quantity`,`time`,`status`,`date`) values 
(1,1,1,'2','night','confirm',NULL),
(2,1,NULL,'1','12.30','confirm','2023-07-28'),
(3,1,NULL,'2','noon','confirm','2023-07-28'),
(4,2,NULL,'2','breakfast','confirm','2023-07-29');

/*Table structure for table `foodee` */

DROP TABLE IF EXISTS `foodee`;

CREATE TABLE `foodee` (
  `foodee_id` int(11) NOT NULL AUTO_INCREMENT,
  `food` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`foodee_id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

/*Data for the table `foodee` */

insert  into `foodee`(`foodee_id`,`food`) values 
(1,'tea with milk and sugar'),
(2,'cow milk'),
(3,'boiled egg'),
(4,'almond'),
(5,'roti'),
(6,'apple'),
(7,'brownbread'),
(8,'oats'),
(9,'green tea '),
(10,'orange'),
(11,'banana ripe'),
(12,'marie gold ,britannia'),
(13,'walnut'),
(14,'red gram dal'),
(15,'green gram dal'),
(16,'mixed vegetable'),
(17,'cucumber'),
(18,'sambar'),
(19,'tomato cucumber salad'),
(20,'sauted vegitables'),
(21,'curd rice'),
(22,'curds');

/*Table structure for table `foodintakes` */

DROP TABLE IF EXISTS `foodintakes`;

CREATE TABLE `foodintakes` (
  `in_id` int(11) NOT NULL AUTO_INCREMENT,
  `fooddetails` varchar(55) DEFAULT NULL,
  `Quantity` varchar(55) DEFAULT NULL,
  `intime` varchar(55) DEFAULT NULL,
  `Calories` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`in_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `foodintakes` */

insert  into `foodintakes`(`in_id`,`fooddetails`,`Quantity`,`intime`,`Calories`) values 
(1,'biriyani','1','noon','667'),
(2,'yogurt','1','breakfast','350');

/*Table structure for table `generaldiet` */

DROP TABLE IF EXISTS `generaldiet`;

CREATE TABLE `generaldiet` (
  `gen_diet_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(55) DEFAULT NULL,
  `description` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`gen_diet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `generaldiet` */

insert  into `generaldiet`(`gen_diet_id`,`title`,`description`) values 
(1,'food control','keep fresh vegitables'),
(2,'food control','keep fresh vegitables'),
(3,'yoga','daily two times');

/*Table structure for table `healthagent` */

DROP TABLE IF EXISTS `healthagent`;

CREATE TABLE `healthagent` (
  `healthagent_id` int(11) NOT NULL AUTO_INCREMENT,
  `logid` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `dob` varchar(11) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `qualification` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`healthagent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `healthagent` */

insert  into `healthagent`(`healthagent_id`,`logid`,`fname`,`lname`,`dob`,`gender`,`phone`,`qualification`) values 
(1,4,'kfn','jhb','1999-12-08','female','9877888888','vsdh'),
(2,5,'lkn','kjn','1999-12-31','female','8766777888','jhb'),
(3,10,'xavier','jones','2000-01-01','Male','5473231231','Nutritionist');

/*Table structure for table `healthprofile` */

DROP TABLE IF EXISTS `healthprofile`;

CREATE TABLE `healthprofile` (
  `profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `gender` varchar(55) DEFAULT NULL,
  `Dob` varchar(55) DEFAULT NULL,
  `blood_pressure` varchar(55) DEFAULT NULL,
  `sugar_level` varchar(55) DEFAULT NULL,
  `cholesterol_level` varchar(55) DEFAULT NULL,
  `body_weight` varchar(55) DEFAULT NULL,
  `Height` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `healthprofile` */

insert  into `healthprofile`(`profile_id`,`userid`,`gender`,`Dob`,`blood_pressure`,`sugar_level`,`cholesterol_level`,`body_weight`,`Height`) values 
(1,2,'Male','28','200','105','300','56','180'),
(2,1,NULL,NULL,'55525','885','5565','45','1231'),
(8,9,'Male','12/42/1231','','','','',''),
(9,10,'Male','12/12/1234','','','','','');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `logid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(55) DEFAULT NULL,
  `password` varchar(55) DEFAULT NULL,
  `type` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`logid`,`username`,`password`,`type`) values 
(1,'admin','admin','admin'),
(2,'user','user','user'),
(3,'yyy','yyy','agents'),
(4,'yyy','yyy','reject'),
(5,'agent','agent','agent'),
(8,'hai','hai','user'),
(9,'hee','hee','user'),
(10,'xav12','abc','agent'),
(11,'hey','abc','user'),
(12,'hari12','hello','user'),
(13,'arjun','abc','user'),
(14,'ren','ren','user'),
(15,'renu','renu','user');

/*Table structure for table `mealtype` */

DROP TABLE IF EXISTS `mealtype`;

CREATE TABLE `mealtype` (
  `meal_id` int(11) NOT NULL AUTO_INCREMENT,
  `meal` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`meal_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `mealtype` */

insert  into `mealtype`(`meal_id`,`meal`) values 
(1,'breakfast'),
(2,'morning snack'),
(3,'lunch'),
(4,'evening snack'),
(5,'dinner');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`user_id`,`amount`,`date`,`status`) values 
(15,10,'1000','2023-08-09','paid'),
(14,9,'1000','2023-08-07','paid'),
(13,1,'1000','2023-08-07','paid');

/*Table structure for table `sampleviceo` */

DROP TABLE IF EXISTS `sampleviceo`;

CREATE TABLE `sampleviceo` (
  `samplevideo_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`samplevideo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sampleviceo` */

insert  into `sampleviceo`(`samplevideo_id`,`name`,`path`) values 
(2,'kjd','static/img8a36f8fe-4c10-4a4d-b3d1-6e9c2b49fe7bwar_-_61412 (360p).mp4');

/*Table structure for table `sugg` */

DROP TABLE IF EXISTS `sugg`;

CREATE TABLE `sugg` (
  `sugg_id` int(11) NOT NULL AUTO_INCREMENT,
  `suggested_id` int(11) DEFAULT NULL,
  `cal` varchar(100) DEFAULT NULL,
  `total` varchar(100) DEFAULT NULL,
  `sdate` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sugg_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sugg` */

insert  into `sugg`(`sugg_id`,`suggested_id`,`cal`,`total`,`sdate`,`user_id`) values 
(1,10,'156','1503','2023-08-08','1'),
(2,6,'95','1659','2023-08-09','1'),
(3,11,'123','1754','2023-08-09','1'),
(4,12,'123','1877','2023-08-09','1'),
(5,9,'134','1866','2023-08-09','10');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `logid` int(11) DEFAULT NULL,
  `fname` varchar(55) DEFAULT NULL,
  `lname` varchar(55) DEFAULT NULL,
  `place` varchar(55) DEFAULT NULL,
  `phone` varchar(55) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`userid`,`logid`,`fname`,`lname`,`place`,`phone`,`email`) values 
(1,2,'neenu','rose','vnj','9876543223','neenu@gmail.com'),
(2,6,'Renuka','Kamath','ekm','1234567890','renukakamath2@gmail.com'),
(4,8,'Renuka','Kamath','fgh','1234567890','renukakamath2@gmail.com'),
(5,9,'Renuka','Kamath','gzhj','1234567891','renukakamath2@gmail.com'),
(6,11,'gdhwh','gshw','aludvh','8848671456','gshw@gmail.com'),
(7,12,'hari','manu','aluva','8848671450','hari1@gmail.com'),
(8,13,'arjun','arjun','sjwj','8848671484','sgh@gmail.com'),
(9,14,'Renuka','Kamath','ekm','1234567891','renukakamath2@gmail.com'),
(10,15,'Renuka','Kamath','ekm','1234567890','renukakamath2@gmail.com');

/*Table structure for table `userupload` */

DROP TABLE IF EXISTS `userupload`;

CREATE TABLE `userupload` (
  `userupload_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `path` varchar(1000) DEFAULT NULL,
  `udate` varchar(1000) DEFAULT NULL,
  `uscount` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `video_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`userupload_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `userupload` */

insert  into `userupload`(`userupload_id`,`user_id`,`path`,`udate`,`uscount`,`description`,`video_id`) values 
(1,1,'static/imge3e23e0f-0e01-4d16-9d3a-26dfb68e8f33abc.mp4','2023-08-07',NULL,NULL,NULL),
(2,1,'static/img4df9f83b-ffb9-41d6-8aac-289dcf5c48a0abc.mp4','2023-08-07','60','hdudjdj',NULL),
(3,1,'static/img5ef80cc9-2f01-4bd8-a01d-ec2ea205132cabc.mp4','2023-08-07','60','hdudjdj',NULL),
(4,1,'static/img9dda77ad-716b-4c00-a89e-99ef6249d5f3abc.mp4','2023-08-08','30','vjkm',5),
(5,10,'static/img6e0f9af4-3241-48d7-bbf3-dea738a14c54abc.mp4','2023-08-09','10','cjjkk',6);

/*Table structure for table `walkingdetails` */

DROP TABLE IF EXISTS `walkingdetails`;

CREATE TABLE `walkingdetails` (
  `walkingid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `steps_count` varchar(55) DEFAULT NULL,
  `walkingdate` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`walkingid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `walkingdetails` */

insert  into `walkingdetails`(`walkingid`,`userid`,`steps_count`,`walkingdate`) values 
(24,10,'45','2023-08-09');

/*Table structure for table `water` */

DROP TABLE IF EXISTS `water`;

CREATE TABLE `water` (
  `water_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `glass` varchar(100) DEFAULT NULL,
  `total` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `balance` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`water_id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

/*Data for the table `water` */

insert  into `water`(`water_id`,`user_id`,`glass`,`total`,`date`,`balance`) values 
(28,10,'100','7900','2023-08-09','0'),
(27,1,'100','7900','2023-08-07','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
