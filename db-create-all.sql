create table assembly_material (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  description                   varchar(255) not null,
  price                         integer not null,
  stock                         integer not null,
  constraint pk_assembly_material primary key (id)
);

create table carport (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  default_price                 integer not null,
  profit_from_materials         integer not null,
  default_width                 integer not null,
  default_length                integer not null,
  description                   varchar(255),
  thumbnail_id                  integer not null,
  frame_id                      integer not null,
  roof_tile_id                  integer not null,
  constraint pk_carport primary key (id)
);

create table frame (
  id                            integer auto_increment not null,
  upper_pillar_material_id      integer not null,
  lower_pillar_material_id      integer not null,
  vertical_pillar_material_id   integer not null,
  roof_plank_material_id        integer not null,
  vertical_pillar_front_reserve integer not null,
  vertical_pillar_back_reserve  integer not null,
  vertical_pillar_distance      integer not null,
  roof_plank_distance           integer not null,
  constraint pk_frame primary key (id)
);

create table material (
  id                            integer auto_increment not null,
  width                         integer not null,
  height                        integer not null,
  name                          varchar(255) not null,
  description                   varchar(255) not null,
  constraint pk_material primary key (id)
);

create table material_dependency (
  id                            integer auto_increment not null,
  material_id                   integer not null,
  assembly_material_id          integer not null,
  amount_per_unit               integer not null,
  constraint pk_material_dependency primary key (id)
);

create table material_length (
  id                            integer auto_increment not null,
  length                        integer not null,
  price                         integer not null,
  stock                         integer not null,
  material_id                   integer not null,
  constraint pk_material_length primary key (id)
);

create table picture (
  id                            integer auto_increment not null,
  url                           varchar(255) not null,
  carport_id                    integer,
  constraint pk_picture primary key (id)
);

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
  pdf_catalogue                 varchar(255),
  price                         integer not null,
  purchase_id                   integer not null,
  constraint pk_purchase_carport primary key (id)
);

create table roof_tile (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  width                         integer not null,
  width_overlap                 integer not null,
  length_overlap                integer not null,
  length                        integer not null,
  description                   varchar(255) not null,
  price                         integer not null,
  stock                         integer not null,
  constraint pk_roof_tile primary key (id)
);

create table roof_tile_dependency (
  id                            integer auto_increment not null,
  roof_tile_id                  integer not null,
  assembly_material_id          integer not null,
  amount_per_unit               integer not null,
  constraint pk_roof_tile_dependency primary key (id)
);

create table user (
  id                            integer auto_increment not null,
  name                          varchar(255) not null,
  surname                       varchar(255) not null,
  created_at                    date not null,
  status                        integer not null,
  type                          integer not null,
  email                         varchar(255) not null,
  password                      varchar(255) not null,
  salt                          varchar(255) not null,
  constraint pk_user primary key (id)
);

alter table carport add constraint fk_carport_thumbnail_id foreign key (thumbnail_id) references picture (id) on delete restrict on update restrict;
create index ix_carport_thumbnail_id on carport (thumbnail_id);

alter table carport add constraint fk_carport_frame_id foreign key (frame_id) references frame (id) on delete restrict on update restrict;
create index ix_carport_frame_id on carport (frame_id);

alter table carport add constraint fk_carport_roof_tile_id foreign key (roof_tile_id) references roof_tile (id) on delete restrict on update restrict;
create index ix_carport_roof_tile_id on carport (roof_tile_id);

alter table frame add constraint fk_frame_upper_pillar_material_id foreign key (upper_pillar_material_id) references material (id) on delete restrict on update restrict;
create index ix_frame_upper_pillar_material_id on frame (upper_pillar_material_id);

alter table frame add constraint fk_frame_lower_pillar_material_id foreign key (lower_pillar_material_id) references material (id) on delete restrict on update restrict;
create index ix_frame_lower_pillar_material_id on frame (lower_pillar_material_id);

alter table frame add constraint fk_frame_vertical_pillar_material_id foreign key (vertical_pillar_material_id) references material (id) on delete restrict on update restrict;
create index ix_frame_vertical_pillar_material_id on frame (vertical_pillar_material_id);

alter table frame add constraint fk_frame_roof_plank_material_id foreign key (roof_plank_material_id) references material (id) on delete restrict on update restrict;
create index ix_frame_roof_plank_material_id on frame (roof_plank_material_id);

alter table material_dependency add constraint fk_material_dependency_material_id foreign key (material_id) references material (id) on delete restrict on update restrict;
create index ix_material_dependency_material_id on material_dependency (material_id);

alter table material_dependency add constraint fk_material_dependency_assembly_material_id foreign key (assembly_material_id) references assembly_material (id) on delete restrict on update restrict;
create index ix_material_dependency_assembly_material_id on material_dependency (assembly_material_id);

alter table material_length add constraint fk_material_length_material_id foreign key (material_id) references material (id) on delete restrict on update restrict;
create index ix_material_length_material_id on material_length (material_id);

alter table picture add constraint fk_picture_carport_id foreign key (carport_id) references carport (id) on delete restrict on update restrict;
create index ix_picture_carport_id on picture (carport_id);

alter table purchase add constraint fk_purchase_customer_id foreign key (customer_id) references user (id) on delete restrict on update restrict;
create index ix_purchase_customer_id on purchase (customer_id);

alter table purchase_carport add constraint fk_purchase_carport_carport_id foreign key (carport_id) references carport (id) on delete restrict on update restrict;
create index ix_purchase_carport_carport_id on purchase_carport (carport_id);

alter table purchase_carport add constraint fk_purchase_carport_purchase_id foreign key (purchase_id) references purchase (id) on delete restrict on update restrict;
create index ix_purchase_carport_purchase_id on purchase_carport (purchase_id);

alter table roof_tile_dependency add constraint fk_roof_tile_dependency_roof_tile_id foreign key (roof_tile_id) references roof_tile (id) on delete restrict on update restrict;
create index ix_roof_tile_dependency_roof_tile_id on roof_tile_dependency (roof_tile_id);

alter table roof_tile_dependency add constraint fk_roof_tile_dependency_assembly_material_id foreign key (assembly_material_id) references assembly_material (id) on delete restrict on update restrict;
create index ix_roof_tile_dependency_assembly_material_id on roof_tile_dependency (assembly_material_id);

