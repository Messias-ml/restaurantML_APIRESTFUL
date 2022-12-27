create table photo_product (
  id bigint not null,
  name_file varchar(150) not null,
  description varchar(150),
  content_type varchar(80) not null,
  size_file int not null,

  primary key (id),
  constraint fk_photo_product_product foreign key (id) references product (id_product)
) engine=InnoDB default charset=utf8;