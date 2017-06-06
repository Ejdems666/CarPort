-- apply changes
alter table carport modify shed_width integer not null;
alter table carport modify shed_length integer not null;
alter table purchase_carport modify shed_width integer not null;
alter table purchase_carport modify shed_length integer not null;
