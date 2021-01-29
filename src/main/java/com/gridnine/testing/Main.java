package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());

        System.out.println("Список перелётов");
        flights.forEach(System.out::println);
        System.out.println("--------------------------------------");

        Selection check = new Check(flights);

        List<Flight> beforeDepartureCurrent = check.beforeCurrentTime();
        List<Flight> arrivalBeforeDeparture = check.arrivalBeforeDeparture();
        List<Flight> differentLandTime = check.differentLandTime();

        System.out.println("Вылет до текущего момента времени");
        beforeDepartureCurrent.forEach(System.out::println);
        System.out.println("--------------------------------------");

        System.out.println("Сегменты с датой прилёта раньше даты вылета");
        arrivalBeforeDeparture.forEach(System.out::println);
        System.out.println("--------------------------------------");

        System.out.println("Общее время на земле превышает 2 часа");
        differentLandTime.forEach(System.out::println);
    }
}
