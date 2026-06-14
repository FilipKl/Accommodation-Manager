create table countries (
                           id bigserial primary key,
                           name varchar(255) not null unique,
                           continent varchar(255) not null
);

create table hosts (
                       id bigserial primary key,
                       created_at timestamp not null,
                       updated_at timestamp not null,
                       name varchar(255) not null,
                       surname varchar(255) not null,
                       country_id bigint not null references countries(id)
);

create table accommodations (
                                id bigserial primary key,
                                created_at timestamp not null,
                                updated_at timestamp not null,
                                name varchar(255) not null,
                                category varchar(50) not null,
                                condition varchar(50) not null,
                                host_id bigint not null references hosts(id),
                                num_rooms integer not null,
                                rented boolean not null default false
);