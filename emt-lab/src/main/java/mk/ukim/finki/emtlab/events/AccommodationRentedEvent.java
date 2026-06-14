package mk.ukim.finki.emtlab.events;

public record AccommodationRentedEvent(
        Long accommodationId,
        String accommodationName
) {
}