package mk.ukim.finki.emtlab.model.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long id) {
        super("A country with id %d does not exist.".formatted(id));
    }
}
