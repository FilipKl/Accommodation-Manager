create table activity_log (
    id bigserial primary key,
    accommodation_name varchar(255) not null,
    event_time timestamp not null,
    event_type varchar(255) not null
);