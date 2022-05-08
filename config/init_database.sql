-- auto-generated definition
create table resumes
(
    uuid      char(36) not null
        constraint resumes_pk
            primary key,
    full_name text     not null
);

alter table resumes
    owner to postgres;

-- auto-generated definition
create table contacts
(
    id          serial
        constraint contacts_pk
            primary key,
    resume_uuid char(36) not null
        constraint contacts_resume_uuid_fk
            references resumes
            on delete cascade,
    type        text     not null,
    value       text     not null
);

alter table contacts
    owner to postgres;

create unique index contacts_uuid_type_index
    on contacts (resume_uuid, type);