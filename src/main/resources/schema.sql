drop table if exists SERVICE_USER;
create table if not exists SERVICE_USER
(
    USER_ID   CHARACTER VARYING not null,
    USER_NAME CHARACTER VARYING,
    constraint SERVICE_USER_PK
        primary key (USER_ID)
);
