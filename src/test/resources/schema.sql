create table wish (
                      id int AUTO_INCREMENT primary key,
                      user_id int not null,
                      wishName varchar (30) not null,
                      descriptions varchar (200),
                      prices double,
                      link varchar (255)
);