create table act
(
    id  bigserial not null,
    date date,
    name varchar(255),
    number varchar(255),
    organization varchar(255),
    restriction_id int8,
    primary key (id)
);
create table act_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    date date,
    name varchar(255),
    number varchar(255),
    organization varchar(255),
    restriction_id int8,
    primary key (id, rev)
);
create table building
(
    id  bigserial not null,
    floor_n int4,
    individual_number varchar(255),
    underground_floor_n int4,
    primary key (id)
);
create table building_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    floor_n int4,
    individual_number varchar(255),
    underground_floor_n int4,
    primary key (id, rev)
);
create table contract
(
    id  bigserial not null,
    agreement_participant_1 varchar(255),
    agreement_participant_2 varchar(255),
    date timestamp,
    date_time timestamp,
    name varchar(255),
    land_plot_id int8,
    primary key (id)
);
create table contract_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    agreement_participant_1 varchar(255),
    agreement_participant_2 varchar(255),
    date timestamp,
    date_time timestamp,
    name varchar(255),
    land_plot_id int8,
    primary key (id, rev)
);
create table isolated_room
(
    id  bigserial not null,
    inventory_number varchar(255),
    surface float8,
    building_id int8,
    primary key (id)
);
create table isolated_room_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    inventory_number varchar(255),
    surface float8,
    building_id int8,
    primary key (id, rev)
);
create table land_plot
(
    id  bigserial not null,
    apartmentn int8,
    city varchar(255),
    home_number varchar(255),
    region varchar(255),
    street varchar(255),
    cadastral_number varchar(255),
    category varchar(255),
    current_marks varchar(255),
    intended_use varchar(255),
    land_plot_purpose varchar(255),
    notes varchar(255),
    surface float8,
    primary key (id)
);
create table land_plot_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    apartmentn int8,
    city varchar(255),
    home_number varchar(255),
    region varchar(255),
    street varchar(255),
    cadastral_number varchar(255),
    category varchar(255),
    current_marks varchar(255),
    intended_use varchar(255),
    land_plot_purpose varchar(255),
    notes varchar(255),
    surface float8,
    primary key (id, rev)
);
create table organization
(
    id  bigserial not null,
    apartmentn int8,
    city varchar(255),
    home_number varchar(255),
    region varchar(255),
    street varchar(255),
    name varchar(255),
    registration_date date,
    type varchar(255),
    unp varchar(255),
    right_of_use_id int8,
    primary key (id)
);
create table organization_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    apartmentn int8,
    city varchar(255),
    home_number varchar(255),
    region varchar(255),
    street varchar(255),
    name varchar(255),
    registration_date date,
    type varchar(255),
    unp varchar(255),
    right_of_use_id int8,
    primary key (id, rev)
);
create table owner
(
    type varchar(31) not null,
    id  bigserial not null,
    name varchar(255),
    unp varchar(255),
    birth_date date,
    doc_n varchar(255),
    doc_type varchar(255),
    personal_number varchar(255),
    land_plot_id int8,
    ownership_id int8,
    right_of_use_id int8,
    owner_id int8,
    primary key (id)
);
create table owner_aud
(
    id int8 not null,
    rev int4 not null,
    type varchar(31),
    revtype int2,
    name varchar(255),
    land_plot_id int8,
    ownership_id int8,
    right_of_use_id int8,
    primary key (id, rev)
);
create table ownership
(
    id  bigserial not null,
    proportion float8,
    registration_date date,
    owner_id int8,
    primary key (id)
);
create table ownership_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    proportion float8,
    registration_date date,
    owner_id int8,
    primary key (id, rev)
);
create table restriction
(
    id  bigserial not null,
    description varchar(255),
    land_plot_id int8,
    primary key (id)
);
create table restriction_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    description varchar(255),
    land_plot_id int8,
    primary key (id, rev)
);
create table revinfo
(
    rev int4 not null,
    revtstmp int8,
    primary key (rev)
);
create table right_of_use
(
    id  bigserial not null,
    cost_desceription varchar(255),
    description varchar(255),
    proportion float8,
    validity_period date,
    owner_id int8,
    primary key (id)
);
create table right_of_use_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    cost_desceription varchar(255),
    description varchar(255),
    proportion float8,
    validity_period date,
    owner_id int8,
    primary key (id, rev)
);
create table state_registration
(
    id  bigserial not null,
    date date,
    issue_organization varchar(255),
    land_plot_id int8,
    right_of_use_id int8,
    primary key (id)
);
create table state_registration_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    date date,
    issue_organization varchar(255),
    land_plot_id int8,
    right_of_use_id int8,
    primary key (id, rev)
);
create table uninsulated_room
(
    id  bigserial not null,
    inventory_number varchar(255),
    surface float8,
    building_id int8,
    primary key (id)
);
create table uninsulated_room_aud
(
    id int8 not null,
    rev int4 not null,
    revtype int2,
    inventory_number varchar(255),
    surface float8,
    building_id int8,
    primary key (id, rev)
);
create sequence hibernate_sequence start 1 increment 1;
alter table if exists act add constraint FKgmvldqvsrtco8bvy3y5tdfdd foreign key (restriction_id) references restriction;
alter table if exists act_aud add constraint FKb0f5itylsi3afb4veji5rw797 foreign key (rev) references revinfo;
alter table if exists building_aud add constraint FKersxpff8csaifhkdpal2j5o3o foreign key (rev) references revinfo;
alter table if exists contract add constraint FKko8a44r28j78aa8jj4fa1fl20 foreign key (land_plot_id) references land_plot;
alter table if exists contract_aud add constraint FKdwmknd8t7wjko72bg4ka0gtnb foreign key (rev) references revinfo;
alter table if exists isolated_room add constraint FKkhhhdungiib10yfokw3bqf28m foreign key (building_id) references building;
alter table if exists isolated_room_aud add constraint FKjg7il9ng2uhcpcyhxxnym89u9 foreign key (rev) references revinfo;
alter table if exists land_plot_aud add constraint FKkbj9eipintn9rb0iuaqyu6ybm foreign key (rev) references revinfo;
alter table if exists organization add constraint FKoebwm3jqsqo1vhbt0okpmqdhy foreign key (right_of_use_id) references right_of_use;
alter table if exists organization_aud add constraint FKjc7pc3ombplinmhjykfmu9ckg foreign key (rev) references revinfo;
alter table if exists owner add constraint FKejq7077qo6w9ach66th58koso foreign key (land_plot_id) references land_plot;
alter table if exists owner add constraint FKgr22byqp5tlhxqu924btgdkel foreign key (ownership_id) references ownership;
alter table if exists owner add constraint FKopdt3udo9q5j84bs4c1tymtmn foreign key (right_of_use_id) references right_of_use;
alter table if exists owner add constraint FKl2pgxld045yld30ej1ud69xu2 foreign key (owner_id) references state_registration;
alter table if exists owner_aud add constraint FKk6pdnuh7gbuhq1wxsw31w236k foreign key (rev) references revinfo;
alter table if exists ownership add constraint FKqfbb8q0hyll5tdeovfkf8ate6 foreign key (owner_id) references owner;
alter table if exists ownership_aud add constraint FK8djbvmwrerffnsarbda8nnxms foreign key (rev) references revinfo;
alter table if exists restriction add constraint FK5t45svr3smfe945q7f3xepxyp foreign key (land_plot_id) references land_plot;
alter table if exists restriction_aud add constraint FKpkpk001dlg69s03m3e2mj02io foreign key (rev) references revinfo;
alter table if exists right_of_use add constraint FKi4lelh22w1fept51eq5g3925i foreign key (owner_id) references owner;
alter table if exists right_of_use_aud add constraint FKo2jmy086mowd2ejxvvqyxk93u foreign key (rev) references revinfo;
alter table if exists state_registration add constraint FKmgtl4b1xrod1ybh9y2l7dv6uq foreign key (land_plot_id) references land_plot;
alter table if exists state_registration add constraint FKs9bqha09m7f58x04rqygd28kh foreign key (right_of_use_id) references right_of_use;
alter table if exists state_registration_aud add constraint FK8cbakwoxwm46e9v9n4cghnju0 foreign key (rev) references revinfo;
alter table if exists uninsulated_room add constraint FK5wy3wp6k0xuu3v49epktxl40w foreign key (building_id) references building;
alter table if exists uninsulated_room_aud add constraint FKft5ul2gs96mp28sac83fmejwp foreign key (rev) references revinfo;
;