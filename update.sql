alter table if exists owner add column apartmentn int8
alter table if exists owner_aud add column apartmentn int8
create table owner (id  bigserial not null, doc_n varchar(255), doc_type varchar(255), name varchar(255), personal_number varchar(255), phone_number varchar(255), post_apartment_n int8, post_city varchar(255), post_home_number varchar(255), post_region varchar(255), post_street varchar(255), ptn_n varchar(255), apartment_n int8, city varchar(255), home_number varchar(255), region varchar(255), street varchar(255), registration_date date, registration_number varchar(255), telegram_number varchar(255), type varchar(255), viber_number varchar(255), whatsapp_number varchar(255), owner_id int8, land_plot_id int8, ownership_id int8, right_of_use_id int8, primary key (id))
alter table if exists owner_aud add column personal_number varchar(255)
alter table if exists owner add constraint FKl2pgxld045yld30ej1ud69xu2 foreign key (owner_id) references state_registration
alter table if exists owner add constraint FKejq7077qo6w9ach66th58koso foreign key (land_plot_id) references land_plot
alter table if exists owner add constraint FKgr22byqp5tlhxqu924btgdkel foreign key (ownership_id) references ownership
alter table if exists owner add constraint FKopdt3udo9q5j84bs4c1tymtmn foreign key (right_of_use_id) references right_of_use
alter table if exists ownership add constraint FKqfbb8q0hyll5tdeovfkf8ate6 foreign key (owner_id) references owner
alter table if exists right_of_use add constraint FKi4lelh22w1fept51eq5g3925i foreign key (owner_id) references owner
alter table if exists state_registration add constraint FKf73w0xe7mh5xommr7welyphwg foreign key (owner_id) references owner
create table owner (id  bigserial not null, doc_n varchar(255), doc_type varchar(255), name varchar(255), personal_number varchar(255), phone_number varchar(255), post_apartment_n int8, post_city varchar(255), post_home_number varchar(255), post_region varchar(255), post_street varchar(255), ptn_n varchar(255), apartment_n int8, city varchar(255), home_number varchar(255), region varchar(255), street varchar(255), registration_date date, registration_number varchar(255), telegram_number varchar(255), type varchar(255), viber_number varchar(255), whatsapp_number varchar(255), owner_id int8, land_plot_id int8, ownership_id int8, right_of_use_id int8, primary key (id))
alter table if exists owner_aud add column personal_number varchar(255)
alter table if exists owner add constraint FKl2pgxld045yld30ej1ud69xu2 foreign key (owner_id) references state_registration
alter table if exists owner add constraint FKejq7077qo6w9ach66th58koso foreign key (land_plot_id) references land_plot
alter table if exists owner add constraint FKgr22byqp5tlhxqu924btgdkel foreign key (ownership_id) references ownership
alter table if exists owner add constraint FKopdt3udo9q5j84bs4c1tymtmn foreign key (right_of_use_id) references right_of_use
alter table if exists ownership add constraint FKqfbb8q0hyll5tdeovfkf8ate6 foreign key (owner_id) references owner
alter table if exists right_of_use add constraint FKi4lelh22w1fept51eq5g3925i foreign key (owner_id) references owner
alter table if exists state_registration add constraint FKf73w0xe7mh5xommr7welyphwg foreign key (owner_id) references owner
create table owner_aud (id int8 not null, rev int4 not null, revtype int2, doc_n varchar(255), doc_type varchar(255), name varchar(255), phone_number varchar(255), post_apartment_n int8, post_city varchar(255), post_home_number varchar(255), post_region varchar(255), post_street varchar(255), ptn_n varchar(255), apartment_n int8, city varchar(255), home_number varchar(255), region varchar(255), street varchar(255), registration_date date, registration_number varchar(255), telegram_number varchar(255), type varchar(255), viber_number varchar(255), whatsapp_number varchar(255), owner_id int8, land_plot_id int8, ownership_id int8, right_of_use_id int8, primary key (id, rev))
alter table if exists owner_aud add constraint FKk6pdnuh7gbuhq1wxsw31w236k foreign key (rev) references revinfo
create table owner_aud (id int8 not null, rev int4 not null, revtype int2, doc_n varchar(255), doc_type varchar(255), name varchar(255), phone_number varchar(255), post_apartment_n int8, post_city varchar(255), post_home_number varchar(255), post_region varchar(255), post_street varchar(255), ptn_n varchar(255), apartment_n int8, city varchar(255), home_number varchar(255), region varchar(255), street varchar(255), registration_date date, registration_number varchar(255), telegram_number varchar(255), type varchar(255), viber_number varchar(255), whatsapp_number varchar(255), owner_id int8, land_plot_id int8, ownership_id int8, right_of_use_id int8, primary key (id, rev))
alter table if exists owner_aud add constraint FKk6pdnuh7gbuhq1wxsw31w236k foreign key (rev) references revinfo
alter table if exists owner_aud add column birth_date date
alter table if exists owner_aud add column birth_date date
create table owner_aud (id int8 not null, rev int4 not null, revtype int2, birth_date date, doc_n varchar(255), doc_type varchar(255), name varchar(255), phone_number varchar(255), post_apartment_n int8, post_city varchar(255), post_home_number varchar(255), post_region varchar(255), post_street varchar(255), ptn_n varchar(255), apartment_n int8, city varchar(255), home_number varchar(255), region varchar(255), street varchar(255), registration_date date, registration_number varchar(255), telegram_number varchar(255), type varchar(255), viber_number varchar(255), whatsapp_number varchar(255), owner_id int8, land_plot_id int8, ownership_id int8, right_of_use_id int8, primary key (id, rev))
alter table if exists owner_aud add constraint FKk6pdnuh7gbuhq1wxsw31w236k foreign key (rev) references revinfo
alter table if exists owner_aud add column birth_date date
alter table if exists land_plot add column dtype varchar(31) not null
alter table if exists land_plot add column rev int4
alter table if exists land_plot add column revtype int2
alter table if exists land_plot_aud add column dtype varchar(31) not null
alter table if exists land_plot add column dtype varchar(31) not null
alter table if exists land_plot add column rev int4
alter table if exists land_plot add column revtype int2
alter table if exists land_plot_aud add column dtype varchar(31) not null
alter table if exists land_plot add column dtype varchar(31) not null
alter table if exists land_plot add column rev int4
alter table if exists land_plot add column revtype int2
alter table if exists land_plot_aud add column dtype varchar(31) not null
alter table if exists land_plot add column dtype varchar(31) not null
alter table if exists land_plot add column rev int4
alter table if exists land_plot add column revtype int2
alter table if exists land_plot_aud add column dtype varchar(31) not null
alter table if exists land_plot add column dtype varchar(31) not null
alter table if exists land_plot add column rev int4
alter table if exists land_plot add column revtype int2
alter table if exists land_plot_aud add column dtype varchar(31) not null
alter table if exists land_plot add column dtype varchar(31) not null
alter table if exists land_plot add column rev int4
alter table if exists land_plot add column revtype int2
alter table if exists land_plot_aud add column dtype varchar(31) not null
alter table if exists land_plot add column dtype varchar(31) not null
alter table if exists land_plot_aud add column dtype varchar(31) not null
