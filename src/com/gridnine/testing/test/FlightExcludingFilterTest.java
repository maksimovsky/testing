package com.gridnine.testing.test;

import com.gridnine.testing.main.model.Flight;
import com.gridnine.testing.main.service.FlightBuilder;
import com.gridnine.testing.main.service.FlightExcludingFilter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class FlightExcludingFilterTest {
    List<Flight> flights = FlightBuilder.createFlights();
    @Test
    public void departuresBeforeNow() {
        List<Flight> expected =
                List.of(flights.get(0), flights.get(1), flights.get(3), flights.get(4), flights.get(5));
        List<Flight> actual = FlightExcludingFilter.departuresBeforeNow(flights);
        assertEquals(expected, actual);
    }

    @Test
    public void segmentArriveBeforeDeparture() {
        List<Flight> expected =
                List.of(flights.get(0), flights.get(1), flights.get(2), flights.get(4), flights.get(5));
        List<Flight> actual = FlightExcludingFilter.segmentArriveBeforeDeparture(flights);
        assertEquals(expected, actual);
    }

    @Test
    public void groundTimeMoreHoursThan() {
        List<Flight> expected =
                List.of(flights.get(0), flights.get(1), flights.get(2), flights.get(3));
        List<Flight> actual = FlightExcludingFilter.groundTimeMoreHoursThan(flights, 2);
        assertEquals(expected, actual);
    }
}
