package com.gridnine.testing.main.service;

import com.gridnine.testing.main.model.Flight;
import com.gridnine.testing.main.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightExcludingFilter {
    public static List<Flight> departuresBeforeNow(List<Flight> flights) {
        List<Flight> filtered = new ArrayList<>();
        for (Flight flight : flights) {
            Segment firstSegment = flight.getSegments().get(0);
            if (firstSegment.getDepartureDate().isBefore(LocalDateTime.now())) {
                filtered.add(flight);
            }
        }
        return exclude(flights, filtered);
    }

    public static List<Flight> segmentArriveBeforeDeparture(List<Flight> flights) {
        List<Flight> filtered = new ArrayList<>();
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    filtered.add(flight);
                    break;
                }
            }
        }
        return exclude(flights, filtered);
    }

    public static List<Flight> groundTimeMoreHoursThan(List<Flight> flights, int groundHours) {
        List<Flight> filtered = new ArrayList<>();
        long duration;
        for (Flight flight : flights) {
            Segment[] segments = flight.getSegments().toArray(new Segment[0]);
            if (segments.length == 1) {
                continue;
            }
            duration = 0;
            for (int i = 0; i < segments.length - 1; i++) {
                duration += Duration.between(segments[i].getArrivalDate(),
                        segments[i + 1].getDepartureDate()).abs().toHours();
            }
            if (duration > groundHours) {
                filtered.add(flight);
            }
        }
        return exclude(flights, filtered);
    }

    private static List<Flight> exclude(List<Flight> all, List<Flight> excluding) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : all) {
            if (!excluding.contains(flight)) {
                result.add(flight);
            }
        }
        return result;
    }
}
