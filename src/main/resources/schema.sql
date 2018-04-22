create table user
(
   userID varchar(10) not null,
   email varchar(50) not null,
   creationDate datetime not null,
   lastUpdateDate datetime not null,
   primary key(userID)
);


create table appointment
(
   id varchar(10) auto_increment,
   startDate datetime not null,
   endDate datetime not null,
   creationDate datetime not null,
   lastUpdateDate datetime not null,
   userID varchar(10) not null,
   primary key(id),
   FOREIGN KEY (userID) REFERENCES user(userID)
);