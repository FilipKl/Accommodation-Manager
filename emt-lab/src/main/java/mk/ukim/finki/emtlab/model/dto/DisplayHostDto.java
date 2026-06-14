package mk.ukim.finki.emtlab.model.dto;

import java.util.List;
import mk.ukim.finki.emtlab.model.domain.Host;

public record DisplayHostDto(
        Long id,
        String name,
        String surname,
        Long countryId,
        String countryName
) {
    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getId(),
                host.getCountry().getName()
        );
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream()
                .map(DisplayHostDto::from)
                .toList();
    }
}