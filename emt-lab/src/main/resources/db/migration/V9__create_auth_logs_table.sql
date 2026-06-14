create table auth_logs (
    id bigserial primary key,
    username varchar(255) not null,
    token varchar(512) not null,
    issued_at timestamp not null,
    expires_at timestamp not null
);