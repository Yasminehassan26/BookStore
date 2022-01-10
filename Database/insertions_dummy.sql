USE Library_schema;
INSERT INTO user VALUES(default,'menna','111111','Menna','Samir','menna.samir@gmail.com','0120120120','Alexandria',false);
INSERT INTO user VALUES(default,'yasmine','111111','Yasmine','Hassan','yasmine@gmail.com','0120120120','Alexandria',false);
INSERT INTO user VALUES(default,'hazem','111111','Ahmed','Hazem','AHMED.samir@gmail.com','0120120120','Alexandria',true);
INSERT INTO user VALUES(default,'mariam','111111','Mariam','Ghazi','MARIAM.samir@gmail.com','0120120120','Alexandria',false);
INSERT INTO user VALUES(default,'mariem','111111','Mariem','Youssri','MARIEM.samir@gmail.com','0120120120','Alexandria',false);
INSERT INTO user VALUES(default,'youssef','111111','Youssef','Hussein','YOUSSEF.samir@gmail.com','0120120120','Alexandria',false);

INSERT INTO Publisher VALUES('Hachette','Paris','0120120120');
INSERT INTO Publisher VALUES('HarperCollins','London','0120120120');
INSERT INTO Publisher VALUES('Macmillan','New York','0120120120');
INSERT INTO Publisher VALUES('Time Square','Washington','0120120120');
INSERT INTO Publisher VALUES('Alexandria Publishing','Alexandria','0120120120');
INSERT INTO Publisher VALUES('Kayan','Cairo','0120120120');
INSERT INTO Publisher VALUES('testpublisher','testpublisher','testpublisher');

INSERT INTO Book VALUES(default,'Sherlock Holmes','HarperCollins','1980','Art',50,50,51);
INSERT INTO Book VALUES(default,'Divergent','Hachette','2005','Art',50,50,100);
INSERT INTO Book VALUES(default,'The Maze Runner','Macmillan','2003','Geography',50,50,100);
INSERT INTO Book VALUES(default,'CLRS','Time Square','1990','Science',50,50,100);
INSERT INTO Book VALUES(default,'Amareta','Alexandria Publishing','2010','History',50,50,100);
INSERT INTO Book VALUES(default,'Malek','Kayan','2012','Art',50,50,100);
INSERT INTO Book VALUES(default,'testbook1','testpublisher','2001','Art',10,10,100);
INSERT INTO Book VALUES(default,'testbook2','testpublisher','2002','Art',20,20,100);
INSERT INTO Book VALUES(default,'testbook3','testpublisher','2003','Art',30,30,100);
INSERT INTO Book VALUES(default,'testbook4','testpublisher','2004','Geography',40,40,100);
INSERT INTO Book VALUES(default,'testbook5','testpublisher','2005','Geography',50,50,100);
INSERT INTO Book VALUES(default,'testbook6','testpublisher','2006','History',60,60,100);
INSERT INTO Book VALUES(default,'testbook7','testpublisher','2007','History',70,70,100);
INSERT INTO Book VALUES(default,'testbook8','testpublisher','2008','Science',80,80,100);
INSERT INTO Book VALUES(default,'testbook9','testpublisher','2009','Religion',90,90,100);
INSERT INTO Book VALUES(default,'testbook10','testpublisher','2010','Religion',100,100,100);

INSERT INTO Authors VALUES(1,'Harper');
INSERT INTO Authors VALUES(1,'Collins');
INSERT INTO Authors VALUES(2,'Agatha');
INSERT INTO Authors VALUES(3,'Conan');
INSERT INTO Authors VALUES(4,'Ahmed');
INSERT INTO Authors VALUES(5,'Younis');
