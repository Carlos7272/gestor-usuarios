
create table manager.post
(
    id      serial  primary key,
    title   varchar(50) not null,
    content text,
    user_id integer constraint post_usuario_id_fkey references manager."user"
);

create table manager.rol
(
    id          serial primary key,
    name        varchar(50) not null,
    description varchar(100),
    user_id     integer constraint rol_usuario_id_fkey references manager."user"
);

create table manager."user"
(
    id        integer default nextval('manager.usuario_id_seq'::regclass) not null
              constraint usuario_pkey
              primary key,
    full_name varchar(50)                                                 not null,
    email     varchar(50)                                                 not null,
    password  varchar(500)                                                not null
);

create unique index user_full_name_uindex
    on manager."user" (full_name);