package com.gridnine.testing;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Check implements Selection {

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
        List<Flight> checkedFlights = new ArrayList<>();
        long period;
        for (Flight f :
                flights) {
            if (f.getSegments().size() / 2 > 0) {
                period = ChronoUnit.HOURS.between(f.getSegments().get(0).getDepartureDate(),
                        f.getSegments().get(f.getSegments().size() - 1).getArrivalDate());
                for (Segment s : f.getSegments()) {
                    long temp = Math.abs(ChronoUnit.HOURS.between(s.getArrivalDate(), s.getDepartureDate()));
                    period -= temp;
                }
                if (period >= 2) {
                    checkedFlights.add(f);
                }
            }
        }

        return checkedFlights;
    }
}
