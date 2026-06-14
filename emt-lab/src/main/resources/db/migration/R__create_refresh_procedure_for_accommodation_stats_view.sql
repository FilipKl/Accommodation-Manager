create or replace procedure refresh_accommodation_stats_view()
    language sql
as $$
refresh materialized view concurrently accommodation_stats_view;
$$;