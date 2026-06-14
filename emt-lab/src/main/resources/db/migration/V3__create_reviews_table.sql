create table reviews (
    id bigserial primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    comment varchar(1024),
    grade integer not null,
    accommodation_id bigint not null references accommodations(id) on delete cascade
);
