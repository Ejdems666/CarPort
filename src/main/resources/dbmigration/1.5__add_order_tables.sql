-- apply changes
create table purchase (
  id                            integer auto_increment not null,
  final_price                   integer not null,
  ordered_on                    date,
  customer_id                   integer,
  constraint pk_purchase primary key (id)
);

create table purchase_carport (
  id                            integer auto_increment not null,
  carport_id                    integer not null,
  frame_width                   integer not null,
  frame_length                  integer not null,
  purchase_id                   integer not null,
  constraint pk_purchase_carport primary key (id)
);

alter table purchase add constraint fk_purchase_customer_id foreign key (customer_id) references user (id) on delete restrict on update restrict;
create index ix_purchase_customer_id on purchase (customer_id);

alter table purchase_carport add constraint fk_purchase_carport_carport_id foreign key (carport_id) references carport (id) on delete restrict on update restrict;
create index ix_purchase_carport_carport_id on purchase_carport (carport_id);

alter table purchase_carport add constraint fk_purchase_carport_purchase_id foreign key (purchase_id) references purchase (id) on delete restrict on update restrict;
create index ix_purchase_carport_purchase_id on purchase_carport (purchase_id);

