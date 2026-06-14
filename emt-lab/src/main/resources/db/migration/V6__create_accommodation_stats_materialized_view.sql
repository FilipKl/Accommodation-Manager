create materialized view accommodation_stats_view as
select
    a.category as category,
    count(a.id) as total_accommodations,
    sum(a.num_rooms) as total_rooms,
    avg(a.num_rooms) as average_rooms
from accommodations a
group by a.category;

create unique index idx_accommodation_stats_view_category
    on accommodation_stats_view(category);