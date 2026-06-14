insert into countries (name, continent)
values ('Macedonia', 'Europe'),
       ('Italy', 'Europe'),
       ('Spain', 'Europe'),
       ('Japan', 'Asia'),
       ('United States', 'North America');

insert into hosts (created_at, updated_at, name, surname, country_id)
values (now(), now(), 'Filip', 'Klisarovski', (select id from countries where name = 'Macedonia')),
       (now(), now(), 'Lorenzo', 'Pellegrini',           (select id from countries where name = 'Italy')),
       (now(), now(), 'Sergio', 'LLull',          (select id from countries where name = 'Spain')),
       (now(), now(), 'Yuki', 'Tsunoda',            (select id from countries where name = 'Japan')),
       (now(), now(), 'LeBron', 'James',             (select id from countries where name = 'United States'));

insert into accommodations (created_at, updated_at, name, category, condition, host_id, num_rooms, rented)
values (now(), now(), 'Studio Skopje',    'FLAT',      'GOOD', (select id from hosts where name = 'Filip' and surname = 'Klisarovski'), 1,  false),
       (now(), now(), 'Mountain House Mavrovo','HOUSE',     'GOOD', (select id from hosts where name = 'Filip' and surname = 'Klisarovski'), 4,  false),
       (now(), now(), 'Rome Apartment', 'APARTMENT', 'GOOD', (select id from hosts where name = 'Lorenzo'       and surname = 'Pellegrini'),    2,  true),
       (now(), now(), 'Madrid Luxury Hotel',    'HOTEL',     'GOOD', (select id from hosts where name = 'Sergio'     and surname = 'LLull'),    50, false),
       (now(), now(), 'Tokyo Guest Room',      'ROOM',      'GOOD', (select id from hosts where name = 'Yuki'       and surname = 'Tsunoda'),    1,  false),
       (now(), now(), 'Motel Route 66',    'MOTEL',     'BAD',  (select id from hosts where name = 'LeBron'       and surname = 'James'),     10, false),
       (now(), now(), 'LA Flat',     'FLAT',      'GOOD', (select id from hosts where name = 'LeBron'       and surname = 'James'),     2,  false);