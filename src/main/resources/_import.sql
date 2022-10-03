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
insert into Restaurant (ID_RESTAURANT, NAME_RESTAURANT, TAX_FRETE, ID_KITCHEN, CREATION_DATE, UPDATE_DATE) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into Restaurant (ID_RESTAURANT, NAME_RESTAURANT, TAX_FRETE, ID_KITCHEN, CREATION_DATE, UPDATE_DATE) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

insert into Permition (ID_PERMITION, NAME_PERMITION, DESCRIPTION) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into Permition (ID_PERMITION, NAME_PERMITION, DESCRIPTION) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into Form_Payment (ID_FORM_PAYMENT, DESCRIPTION) values (1, 'Cartão de crédito');
insert into Form_Payment (ID_FORM_PAYMENT, DESCRIPTION) values (2, 'Cartão de débito');
insert into Form_Payment (ID_FORM_PAYMENT, DESCRIPTION) values (3, 'Dinheiro');

insert into Form_Payment_Restaurant (ID_RESTAURANT, ID_FORM_PAYMENT) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);