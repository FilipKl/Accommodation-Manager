package mk.ukim.finki.emtlab.model.projection;

public interface AccommodationExtendedProjection {
    Long getId();
    String getName();
    String getCategory();
    Integer getNumRooms();
    String getHostFullName();
    String getCountryName();
}