alter table carport drop foreign key fk_carport_picture_id;
drop index ix_carport_picture_id on carport;

alter table dynamic_material drop foreign key fk_dynamic_material_material_id;
drop index ix_dynamic_material_material_id on dynamic_material;

alter table dynamic_material drop foreign key fk_dynamic_material_carport_id;
drop index ix_dynamic_material_carport_id on dynamic_material;

alter table material_dependency drop foreign key fk_material_dependency_base_material_id;
drop index ix_material_dependency_base_material_id on material_dependency;

alter table material_dependency drop foreign key fk_material_dependency_dependent_material_id;
drop index ix_material_dependency_dependent_material_id on material_dependency;

alter table picture drop foreign key fk_picture_carport_id;
drop index ix_picture_carport_id on picture;

alter table static_material drop foreign key fk_static_material_material_id;
drop index ix_static_material_material_id on static_material;

alter table static_material drop foreign key fk_static_material_carport_id;
drop index ix_static_material_carport_id on static_material;

drop table if exists carport;

drop table if exists dynamic_material;

drop table if exists material;

drop table if exists material_dependency;

drop table if exists picture;

drop table if exists static_material;

drop table if exists user;

