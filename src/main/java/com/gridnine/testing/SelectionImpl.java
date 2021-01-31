package com.gridnine.testing;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SelectionImpl implements Selection {

    private List<Flight> flights;

    /**
     * <b>1 point</b>
     */
    @Override
    public List<Flight> beforeCurrentTime() {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> !segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    /**
     * <b>2 point</b>
     */
    @Override
    public List<Flight> arrivalBeforeDeparture() {
        return flights.stream().filter(flight -> flight.getSegments().stream().anyMatch(
                segment -> !segment.getArrivalDate().isBefore(segment.getDepartureDate())
                )
        ).collect(Collectors.toList());
    }

    /**
     * <b>3 point</b>
     */
    @Override
    public List<Flight> differentLandTime() {
        return flights.stream().filter(
                flight -> !(flight.getSegments().size() > 1
                        &&
                        flight.getSegments().stream().reduce(
                                ChronoUnit.HOURS.between(
                                        flight.getSegments().get(0).getDepartureDate(),
                                        flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate()
                                ),
                                (period, s) -> {
                                    long temp = Math.abs(ChronoUnit.HOURS.between(s.getArrivalDate(), s.getDepartureDate()));
                                    return period - temp;
                                },
                                Long::sum
                        ) > 2)).collect(Collectors.toList());
    }
}
