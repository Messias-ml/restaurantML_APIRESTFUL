create table user_restaurant (id_user bigint not null, id_restaurant bigint not null) engine=InnoDB;
alter table user_restaurant add constraint FK_user_restaurant_restaurant foreign key (id_restaurant) references restaurant (id_restaurant);
alter table user_restaurant add constraint FK_user_restaurant_user foreign key (id_user) references user_entity (id_user);