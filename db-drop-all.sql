alter table carport drop foreign key fk_carport_thumbnail_id;
drop index ix_carport_thumbnail_id on carport;

alter table carport drop foreign key fk_carport_frame_id;
drop index ix_carport_frame_id on carport;

alter table carport drop foreign key fk_carport_roof_tile_id;
drop index ix_carport_roof_tile_id on carport;

alter table frame drop foreign key fk_frame_upper_pillar_material_id;
drop index ix_frame_upper_pillar_material_id on frame;

alter table frame drop foreign key fk_frame_lower_pillar_material_id;
drop index ix_frame_lower_pillar_material_id on frame;

alter table frame drop foreign key fk_frame_vertical_pillar_material_id;
drop index ix_frame_vertical_pillar_material_id on frame;

alter table frame drop foreign key fk_frame_roof_plank_material_id;
drop index ix_frame_roof_plank_material_id on frame;

alter table material_dependency drop foreign key fk_material_dependency_material_id;
drop index ix_material_dependency_material_id on material_dependency;

alter table material_dependency drop foreign key fk_material_dependency_assembly_material_id;
drop index ix_material_dependency_assembly_material_id on material_dependency;

alter table material_length drop foreign key fk_material_length_material_id;
drop index ix_material_length_material_id on material_length;

alter table picture drop foreign key fk_picture_carport_id;
drop index ix_picture_carport_id on picture;

alter table roof_tile_dependency drop foreign key fk_roof_tile_dependency_roof_tile_id;
drop index ix_roof_tile_dependency_roof_tile_id on roof_tile_dependency;

alter table roof_tile_dependency drop foreign key fk_roof_tile_dependency_assembly_material_id;
drop index ix_roof_tile_dependency_assembly_material_id on roof_tile_dependency;

drop table if exists assembly_material;

drop table if exists carport;

drop table if exists frame;

drop table if exists material;

drop table if exists material_dependency;

drop table if exists material_length;

drop table if exists picture;

drop table if exists roof_tile;

drop table if exists roof_tile_dependency;

drop table if exists user;

