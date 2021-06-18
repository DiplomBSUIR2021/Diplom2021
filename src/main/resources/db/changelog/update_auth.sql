create table auth_roles (
    id  bigserial not null,
    name varchar(255),
    primary key (id)
);

create table auth_users (
    id  bigserial not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table auth_users_roles (
    user_id  int8 not null,
    roles_id int8 not null
);


alter table if exists auth_users_roles add constraint FKc0icxqyw4g0a4r7aot5rrcbi1 foreign key (roles_id) references auth_roles;
alter table if exists auth_users_roles add constraint FKptxbe0rfpj7wffab0089q0egl foreign key (user_id) references auth_users;

INSERT INTO  auth_roles  (id, name)  VALUES  (1, 'ROLE_ADMIN');
INSERT INTO  auth_roles  (id, name)  VALUES  (2, 'ROLE_SUPERVISOR');
INSERT INTO  auth_roles  (id, name)  VALUES  (3, 'ROLE_WORKER');
