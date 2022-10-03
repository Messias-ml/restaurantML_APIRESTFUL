create table kitchen (id_kitchen bigint not null auto_increment, name_kitchen varchar(150) not null, primary key (id_kitchen)) engine=InnoDB;

create table state (id_state bigint not null auto_increment, name_state varchar(80) not null, primary key (id_state)) engine=InnoDB;

create table city (id_city bigint not null auto_increment, name_city varchar(30) not null, id_state bigint not null, primary key (id_city)) engine=InnoDB;

create table restaurant (id_restaurant bigint not null auto_increment,
address_complement varchar(50) null,
    address_neighborhood varchar(255) not null,
    address_number smallint(5) not null,
    address_patio varchar(100) not null,
    address_zip_cod varchar(10) not null,
    address_id_city bigint(20) not null,
    creation_date datetime not null,
    name_restaurant varchar(150) not null, tax_frete decimal(19,2) not null,
    update_date datetime not null,
    id_kitchen bigint not null,
    primary key (id_restaurant)) engine=InnoDB;

create table form_payment (id_form_payment bigint not null auto_increment, description varchar(150) not null, primary key (id_form_payment)) engine=InnoDB;

create table form_payment_restaurant (id_restaurant bigint not null, id_form_payment bigint not null) engine=InnoDB;

create table Group_Collection (id_group bigint not null auto_increment, name varchar(50) not null, primary key (id_group)) engine=InnoDB;

create table permition (id_permition bigint not null auto_increment, description varchar(150) not null, name_permition varchar(80) not null, primary key (id_permition)) engine=InnoDB;

create table permition_group (id_group bigint not null, id_permition bigint not null) engine=InnoDB;

create table product (id_product bigint not null auto_increment, active bit not null, description varchar(255) not null, name varchar(80) not null, price decimal(19,2) not null, id_restaurant bigint not null, primary key (id_product)) engine=InnoDB;

create table user_group (id_user bigint not null, id_group bigint not null) engine=InnoDB;

create table user_entity (id_user bigint not null auto_increment, creation_date datetime not null, email varchar(120) not null, name varchar(80) not null, password varchar(70) not null, primary key (id_user)) engine=InnoDB;

alter table city add constraint FK_state foreign key (id_state) references state (id_state);
alter table form_payment_restaurant add constraint FK_form_payment foreign key (id_form_payment) references form_payment (id_form_payment);
alter table form_payment_restaurant add constraint FK_restaurant_payment foreign key (id_restaurant) references restaurant (id_restaurant);
alter table permition_group add constraint FK_permition foreign key (id_permition) references permition (id_permition);
alter table permition_group add constraint FK_group_permition foreign key (id_group) references Group_Collection (id_group);
alter table product add constraint FK_restaurant foreign key (id_restaurant) references restaurant (id_restaurant);
alter table restaurant add constraint FK_city foreign key (address_id_city) references city (id_city);
alter table restaurant add constraint FK_kitchen foreign key (id_kitchen) references kitchen (id_kitchen);
alter table user_group add constraint FK_group_user foreign key (id_group) references Group_Collection (id_group);
alter table user_group add constraint FK_user foreign key (id_user) references user_entity (id_user);
