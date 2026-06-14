package mk.ukim.finki.emtlab.model.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long id) {
        super("A review with id %d does not exist.".formatted(id));
    }
}