-- apply changes
alter table carport add column frame_width integer not null;
alter table carport add column frame_length integer not null;
alter table carport add column with_shed tinyint(1) default 0;
alter table carport add column shed_width integer;
alter table carport add column shed_length integer;

alter table frame add column shed_plank_material_id integer not null;

alter table purchase_carport add column with_shed tinyint(1) default 0;
alter table purchase_carport add column shed_width integer;
alter table purchase_carport add column shed_length integer;

alter table frame add constraint fk_frame_shed_plank_material_id foreign key (shed_plank_material_id) references material (id) on delete restrict on update restrict;
create index ix_frame_shed_plank_material_id on frame (shed_plank_material_id);

