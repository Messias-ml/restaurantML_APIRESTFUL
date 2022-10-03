create table demand (
    id_demand bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    tax_frete decimal(10,2) not null,
    total_value decimal(10,2) not null,

    ID_RESTAURANT bigint not null,
    ID_CLIENT bigint not null,
    ID_FORM_PAYMENT bigint not null,

    address_complement varchar(50) null,
    address_neighborhood varchar(255) not null,
    address_number smallint(5) not null,
    address_patio varchar(100) not null,
    address_zip_cod varchar(10) not null,
    address_id_city bigint(20) not null,

    status varchar(10) not null,
    creation_date datetime not null,
    confirmation_date datetime null,
    cancellation_date datetime null,
    delivery_date datetime null,

    primary key (id_demand),

    constraint fk_demand_address_city foreign key (address_id_city) references city (id_city),
    constraint fk_demand_restaurant foreign key (id_restaurant) references restaurant (id_restaurant),
    constraint fk_demand_user_client foreign key (id_client) references user_entity (id_user),
    constraint fk_demand_form_payment foreign key (id_form_payment) references form_payment (id_form_payment)
) engine=InnoDB default charset=utf8;

create table item_demand (
    id_item_demand bigint not null auto_increment,
    quantity smallint(6) not null,
    unitary_price decimal(10,2) not null,
    total_price decimal(10,2) not null,
    observation varchar(255) null,
    id_demand bigint not null,
    id_product bigint not null,

    primary key (id_item_demand),
    unique key uk_item_pedido_produto (id_demand, id_product),

    constraint fk_item_demand_demand foreign key (id_demand) references demand (id_demand),
    constraint fk_item_demand_product foreign key (id_product) references product (id_product)
) engine=InnoDB default charset=utf8;