insert into user(userID,email,creationDate,lastUpdateDate) values('sourav','sourav@gmail.com',current_timestamp(),current_timestamp());

insert into appointment(id,startDate,creationDate,lastUpdateDate,endDate,userID)
values('10001','2018-04-21 15:05:05', current_timestamp(),current_timestamp(),'2018-04-21 15:05:05','sourav');