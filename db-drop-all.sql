alter table default_material drop foreign key fk_default_material_material_id;
drop index ix_default_material_material_id on default_material;

alter table default_material drop foreign key fk_default_material_carport_id;
drop index ix_default_material_carport_id on default_material;

alter table dynamic_material_variable drop foreign key fk_dynamic_material_variable_material_id;
drop index ix_dynamic_material_variable_material_id on dynamic_material_variable;

alter table dynamic_material_variable drop foreign key fk_dynamic_material_variable_carport_id;
drop index ix_dynamic_material_variable_carport_id on dynamic_material_variable;

alter table material_dependency drop foreign key fk_material_dependency_base_material_id;
drop index ix_material_dependency_base_material_id on material_dependency;

alter table material_dependency drop foreign key fk_material_dependency_dependent_material_id;
drop index ix_material_dependency_dependent_material_id on material_dependency;

drop table if exists carport;

drop table if exists default_material;

drop table if exists dynamic_material_variable;

drop table if exists material;

drop table if exists material_dependency;

drop table if exists user;

