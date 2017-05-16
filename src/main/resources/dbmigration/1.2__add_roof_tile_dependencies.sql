-- apply changes
create table roof_tile_dependency (
  id                            integer auto_increment not null,
  roof_tile_id                  integer not null,
  assembly_material_id          integer not null,
  amount_per_unit               integer not null,
  constraint pk_roof_tile_dependency primary key (id)
);

alter table roof_tile_dependency add constraint fk_roof_tile_dependency_roof_tile_id foreign key (roof_tile_id) references roof_tile (id) on delete restrict on update restrict;
create index ix_roof_tile_dependency_roof_tile_id on roof_tile_dependency (roof_tile_id);

alter table roof_tile_dependency add constraint fk_roof_tile_dependency_assembly_material_id foreign key (assembly_material_id) references assembly_material (id) on delete restrict on update restrict;
create index ix_roof_tile_dependency_assembly_material_id on roof_tile_dependency (assembly_material_id);

