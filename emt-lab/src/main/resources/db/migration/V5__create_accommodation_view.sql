create view accommodation_summary_view as
select
    a.id as id,
    a.name as accommodation_name,
    a.category as category,
    a.num_rooms as num_rooms,
    a.rented as rented,
    h.name || ' ' || h.surname as host_full_name,
    c.name as country_name
from accommodations a
         join hosts h on a.host_id = h.id
         join countries c on h.country_id = c.id;