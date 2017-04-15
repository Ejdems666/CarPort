create table carport (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  default_price                 integer not null,
  default_width                 integer not null,
  default_length                integer not null,
  constraint pk_carport primary key (id)
);

create table default_material (
  id                            integer auto_increment not null,
  material_id                   integer,
  carport_id                    integer,
  amount                        integer,
  dynamic                       integer,
  constraint pk_default_material primary key (id)
);

create table dynamic_material_variable (
  id                            integer auto_increment not null,
  material_id                   integer,
  carport_id                    integer,
  base_length                   integer not null,
  base_distance                 integer not null,
  constraint pk_dynamic_material_variable primary key (id)
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

alter table default_material add constraint fk_default_material_material_id foreign key (material_id) references material (id) on delete restrict on update restrict;
create index ix_default_material_material_id on default_material (material_id);

alter table default_material add constraint fk_default_material_carport_id foreign key (carport_id) references carport (id) on delete restrict on update restrict;
create index ix_default_material_carport_id on default_material (carport_id);

alter table dynamic_material_variable add constraint fk_dynamic_material_variable_material_id foreign key (material_id) references material (id) on delete restrict on update restrict;
create index ix_dynamic_material_variable_material_id on dynamic_material_variable (material_id);

alter table dynamic_material_variable add constraint fk_dynamic_material_variable_carport_id foreign key (carport_id) references carport (id) on delete restrict on update restrict;
create index ix_dynamic_material_variable_carport_id on dynamic_material_variable (carport_id);

alter table material_dependency add constraint fk_material_dependency_base_material_id foreign key (base_material_id) references material (id) on delete restrict on update restrict;
create index ix_material_dependency_base_material_id on material_dependency (base_material_id);

alter table material_dependency add constraint fk_material_dependency_dependent_material_id foreign key (dependent_material_id) references material (id) on delete restrict on update restrict;
create index ix_material_dependency_dependent_material_id on material_dependency (dependent_material_id);

