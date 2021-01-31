package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());

        System.out.println("Список перелётов");
        flights.forEach(System.out::println);
        System.out.println("--------------------------------------");

        Selection check = new SelectionImpl(flights);
        
        List<Flight> beforeDepartureCurrent = check.beforeCurrentTime();        //1 пункт
        List<Flight> arrivalBeforeDeparture = check.arrivalBeforeDeparture();   //2 пункт
        List<Flight> differentLandTime = check.differentLandTime();             //3 пункт

        System.out.println("Исключены перелёты: вылеты до текущего момента времени");
        beforeDepartureCurrent.forEach(System.out::println);
        System.out.println("--------------------------------------");

        System.out.println("Исключены перелёты: с датой прилёта раньше даты вылета");
        arrivalBeforeDeparture.forEach(System.out::println);
        System.out.println("--------------------------------------");

        System.out.println("Исключены перелёты: общее время на земле превышает 2 часа");
        differentLandTime.forEach(System.out::println);
    }
}
