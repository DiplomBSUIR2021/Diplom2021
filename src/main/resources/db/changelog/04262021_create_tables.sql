create table land_plot (
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
    the_year smallint ,
    primary key (id, the_year)) PARTITION BY LIST (the_year);

CREATE TABLE land_plot_y2021 PARTITION OF land_plot
    FOR VALUES IN ('2021');

CREATE TABLE land_plot_y2022 PARTITION OF land_plot
    FOR VALUES IN ('2022');
/*alter table if exists land_plot ALTER COLUMN the_year set Default extract(current_date);*/

create table land_plot_aud (
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
    the_year smallint,
    primary key (id, rev));

alter table if exists land_plot ALTER COLUMN the_year set Default 0;
/*alter table if exists land_plot_aud add foreign key (id) references land_plot;
*/
create table owner (
    type varchar(31) not null,
    id  bigserial not null,
    name varchar(255),
     unp varchar(255),
     birth_date date,
     doc_n varchar(255),
     doc_type varchar(255),
     personal_number varchar(255),
     the_year smallint,
     land_plot_id int8,
     ownership_id int8,
     right_of_use_id int8,
     owner_id int8,
     primary key (id)
);
alter table if exists owner add constraint FKejq7077qo6w9ach66th58koso foreign key (land_plot_id, the_year) references land_plot;

/*ALTER TABLE land_plot DETACH PARTITION land_plot_y2022;*/


create table owner_aud (id int8 not null, rev int4 not null, type varchar(31), revtype int2, name varchar(255), land_plot_id int8, ownership_id int8, right_of_use_id int8, primary key (id, rev));
create table revinfo (rev int4 not null, revtstmp int8, primary key (rev));
create sequence hibernate_sequence start 1 increment 1;
alter table if exists land_plot_aud add constraint FKkbj9eipintn9rb0iuaqyu6ybm foreign key (rev) references revinfo;

alter table if exists owner_aud add constraint FKk6pdnuh7gbuhq1wxsw31w236k foreign key (rev) references revinfo;