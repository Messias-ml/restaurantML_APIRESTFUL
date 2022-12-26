
set foreign_key_checks = 0;

delete from city;
delete from kitchen;
delete from state;
delete from form_payment;
delete from Group_Collection;
delete from permition_group;
delete from permition;
delete from product;
delete from restaurant;
delete from form_payment_restaurant;
delete from user_entity;
delete from user_group;
delete from user_restaurant;
delete from demand;
delete from item_demand;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table state auto_increment = 1;
alter table form_payment auto_increment = 1;
alter table Group_Collection auto_increment = 1;
alter table permition auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table user_entity auto_increment = 1;
alter table demand auto_increment = 1;
alter table item_demand auto_increment = 1;

insert into Kitchen (ID_KITCHEN, NAME_KITCHEN) values (1, 'Tailandesa');
insert into Kitchen (ID_KITCHEN, NAME_KITCHEN) values (2, 'Indiana');

insert into State (ID_STATE, NAME_STATE) values (1, 'Minas Gerais');
insert into State (ID_STATE, NAME_STATE) values (2, 'São Paulo');
insert into State (ID_STATE, NAME_STATE) values (3, 'Ceará');

insert into City (ID_CITY, NAME_CITY, ID_STATE) values (1, 'Uberlândia', 1);
insert into City (ID_CITY, NAME_CITY, ID_STATE) values (2, 'Belo Horizonte', 1);
insert into City (ID_CITY, NAME_CITY, ID_STATE) values (3, 'São Paulo', 2);
insert into City (ID_CITY, NAME_CITY, ID_STATE) values (4, 'Campinas', 2);
insert into City (ID_CITY, NAME_CITY, ID_STATE) values (5, 'Fortaleza', 3);

insert into Restaurant (ID_RESTAURANT, NAME_RESTAURANT, TAX_FRETE, ID_KITCHEN, CREATION_DATE, UPDATE_DATE, ADDRESS_ID_CITY, ADDRESS_ZIP_COD, ADDRESS_PATIO, ADDRESS_NUMBER, ADDRESS_NEIGHBORHOOD) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into Restaurant (ID_RESTAURANT, NAME_RESTAURANT, TAX_FRETE, ID_KITCHEN, CREATION_DATE, UPDATE_DATE, ADDRESS_ID_CITY, ADDRESS_ZIP_COD, ADDRESS_PATIO, ADDRESS_NUMBER, ADDRESS_NEIGHBORHOOD) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 2, '38400-888', 'Rua Maria Pinheira', '1100', 'Centro');
insert into Restaurant (ID_RESTAURANT, NAME_RESTAURANT, TAX_FRETE, ID_KITCHEN, CREATION_DATE, UPDATE_DATE, ADDRESS_ID_CITY, ADDRESS_ZIP_COD, ADDRESS_PATIO, ADDRESS_NUMBER, ADDRESS_NEIGHBORHOOD) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp,  1, '36400-222', 'Rua Santa Clara', '870', 'Santo Agostinho');

insert into Permition (ID_PERMITION, NAME_PERMITION, DESCRIPTION) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into Permition (ID_PERMITION, NAME_PERMITION, DESCRIPTION) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into Form_Payment (ID_FORM_PAYMENT, DESCRIPTION) values (1, 'Cartão de crédito');
insert into Form_Payment (ID_FORM_PAYMENT, DESCRIPTION) values (2, 'Cartão de débito');
insert into Form_Payment (ID_FORM_PAYMENT, DESCRIPTION) values (3, 'Dinheiro');

insert into Form_Payment_Restaurant (ID_RESTAURANT, ID_FORM_PAYMENT) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into group_collection (name) values ('USER');
insert into permition_group (id_group, id_permition) values (1, 1);

insert into group_collection (name) values ('MANAGER');
insert into permition_group (id_group, id_permition) values (2, 1);
insert into permition_group (id_group, id_permition) values (2, 2);

insert into user_entity (creation_date, email, name, password) values (utc_timestamp, 'messias.teste@hotmail.com', 'Messias', '1234');
insert into user_group (id_user, id_group) values (1, 1);

insert into user_restaurant (id_restaurant, id_user) values (1, 1);
insert into product (active, name, description, price, id_restaurant) values (true,'comida japonesa', 'sushi e sashimi', 18.2, 2);
insert into product (active, name, description, price, id_restaurant) values (true,'comida tailandeza', 'tailandeza foods', 12.2, 1);

INSERT INTO demand (`id_demand`,`subtotal`,`tax_frete`,`total_value`,`ID_RESTAURANT`,`ID_CLIENT`,`ID_FORM_PAYMENT`,`address_complement`,`address_neighborhood`,`address_number`,`address_patio`,`address_zip_cod`,`address_id_city`,`status`,`creation_date`,`confirmation_date`,`cancellation_date`,`delivery_date`) VALUES (1,48.60,10.00,58.60,1,1,2,'ap 103','Cidade Jardim',2050,'Avenida Jucelino Kubscheck','38400-999',1,'CRIADO','2022-12-05 21:03:50',NULL,NULL,NULL);
INSERT INTO item_demand (`id_item_demand`,`quantity`,`unitary_price`,`total_price`,`observation`,`id_demand`,`id_product`) VALUES (2,2,18.20,36.40,'sem wasabi',1,1);
