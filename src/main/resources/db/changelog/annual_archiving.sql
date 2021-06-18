create table testPartion
(
    id       bigserial not null,
    city     varchar(255),
    the_year smallint,
    primary key (id, the_year)
) PARTITION BY LIST (the_year);

CREATE TABLE testPartion2020 PARTITION OF testPartion FOR VALUES IN ('2020');
CREATE TABLE testPartion2021 PARTITION OF testPartion FOR VALUES IN ('2021');