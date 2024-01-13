package com.gridnine.testing.main;

import com.gridnine.testing.main.model.Flight;
import com.gridnine.testing.main.service.FlightBuilder;
import com.gridnine.testing.main.service.FlightExcludingFilter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Все перелеты:\n" + flights);

        System.out.println();
        List<Flight> result = FlightExcludingFilter.departuresBeforeNow(flights);
        System.out.println("Исключены перелеты с вылетом до текущего момента времени:\n" + result);

        System.out.println();
        result = FlightExcludingFilter.segmentArriveBeforeDeparture(result);
        System.out.println("Исключены перелеты с сегментом, у которого дата прилёта раньше даты вылета:\n" + result);

        System.out.println();
        result = FlightExcludingFilter.groundTimeMoreHoursThan(result, 2);
        System.out.println("Исключены перелеты, где общее время, проведённое на земле, превышает два часа:\n" + result);
    }
}