create table opportunity (
    id bigint auto_increment not null,
    name_company varchar(80) not null,
    description varchar(200) not null,
    value_company decimal(10,2),

    primary key (id)
);
