package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        flights.forEach(System.out::println);
        System.out.println("--------------------------------------");

        Selection check = new Check(flights);
        List<Flight> beforeDepartureCurrent = check.beforeCurrentTime();
        List<Flight> arrivalBeforeDeparture = check.arrivalBeforeDeparture();
        List<Flight> differentLandTime = check.differentLandTime();
        beforeDepartureCurrent.forEach(System.out::println);
        System.out.println("--------------------------------------");
        arrivalBeforeDeparture.forEach(System.out::println);
        System.out.println("--------------------------------------");
        differentLandTime.forEach(System.out::println);
    }
}
