create table carport (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  default_price                 integer not null,
  default_width                 integer not null,
  default_length                integer not null,
  description                   varchar(255),
  picture_id                    integer,
  constraint pk_carport primary key (id)
);

create table dynamic_material (
  id                            integer auto_increment not null,
  material_id                   integer,
  carport_id                    integer,
  base_distance                 integer not null,
  affected_by                   integer not null,
  constraint pk_dynamic_material primary key (id)
);

create table material (
  id                            integer auto_increment not null,
  price                         integer not null,
  name                          varchar(255) not null,
  constraint pk_material primary key (id)
);

create table material_dependency (
  id                            integer auto_increment not null,
  base_material_id              integer,
  dependent_material_id         integer,
  amount_per_unit               integer not null,
  constraint pk_material_dependency primary key (id)
);

create table thumbnail (
  id                            integer auto_increment not null,
  url                           varchar(255) not null,
  carport_id                    integer,
  constraint pk_picture primary key (id)
);

create table static_material (
  id                            integer auto_increment not null,
  material_id                   integer,
  carport_id                    integer,
  amount                        integer,
  constraint pk_static_material primary key (id)
);

create table user (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  surname                       varchar(255),
  created_at                    date,
  status                        integer,
  type                          integer,
  email                         varchar(255),
  password                      varchar(255),
  salt                          varchar(255),
  constraint pk_user primary key (id)
);

alter table carport add constraint fk_carport_picture_id foreign key (picture_id) references thumbnail (id) on delete restrict on update restrict;
create index ix_carport_picture_id on carport (picture_id);

alter table dynamic_material add constraint fk_dynamic_material_material_id foreign key (material_id) references material (id) on delete restrict on update restrict;
create index ix_dynamic_material_material_id on dynamic_material (material_id);

alter table dynamic_material add constraint fk_dynamic_material_carport_id foreign key (carport_id) references carport (id) on delete restrict on update restrict;
create index ix_dynamic_material_carport_id on dynamic_material (carport_id);

alter table material_dependency add constraint fk_material_dependency_base_material_id foreign key (base_material_id) references material (id) on delete restrict on update restrict;
create index ix_material_dependency_base_material_id on material_dependency (base_material_id);

alter table material_dependency add constraint fk_material_dependency_dependent_material_id foreign key (dependent_material_id) references material (id) on delete restrict on update restrict;
create index ix_material_dependency_dependent_material_id on material_dependency (dependent_material_id);

alter table thumbnail add constraint fk_picture_carport_id foreign key (carport_id) references carport (id) on delete restrict on update restrict;
create index ix_picture_carport_id on thumbnail (carport_id);

alter table static_material add constraint fk_static_material_material_id foreign key (material_id) references material (id) on delete restrict on update restrict;
create index ix_static_material_material_id on static_material (material_id);

alter table static_material add constraint fk_static_material_carport_id foreign key (carport_id) references carport (id) on delete restrict on update restrict;
create index ix_static_material_carport_id on static_material (carport_id);

