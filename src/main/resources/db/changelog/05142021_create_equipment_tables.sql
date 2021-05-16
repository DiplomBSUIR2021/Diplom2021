create table equipment (id  bigserial not null, model varchar(255), number int4, validity int4, primary key (id));
create table equipment_aud (id int8 not null, rev int4 not null, revtype int2, model varchar(255), number int4, validity int4, primary key (id, rev));
create table installed_equipment_aud (id int8 not null, rev int4 not null, revtype int2, installation_date date, model varchar(255), number int4, placement int4, replacement_date date, validity int4, primary key (id, rev));
create table installed_equipment (id  bigserial not null, installation_date date, model varchar(255), number int4, placement int4, replacement_date date, validity int4, primary key (id));
alter table if exists equipment_aud add constraint FK122t31k6i770ouvof6h94u8fs foreign key (rev) references revinfo;
alter table if exists installed_equipment_aud add constraint FKrs6vkjmy4e2nm4ngh7s4jxvc8 foreign key (rev) references revinfo;