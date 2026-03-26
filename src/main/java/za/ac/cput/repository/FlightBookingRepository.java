package za.ac.cput.repository;

import za.ac.cput.domain.FlightBooking;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightBookingRepository  implements IFlightBookingRepository {
    private static FlightBookingRepository repo = null;
    private Map<Long, FlightBooking> flightMap;

    private FlightBookingRepository() {
        flightMap = new HashMap<>();
    }

    public static FlightBookingRepository getRepository() {
        if (repo == null) {
            repo = new FlightBookingRepository();
        }
        return repo;
    }

    @Override
    public FlightBooking create(FlightBooking flight) {
        Helper.requireNonNull(flight, "Flight Booking");
        if (flight.getBookingId() == null) {
            throw new IllegalArgumentException("Flight Booking ID cannot be null");
        }
        if (flightMap.containsKey(flight.getBookingId())) {
            throw new IllegalArgumentException("Flight Booking with ID " + flight.getBookingId() + " already exists");
        }
        flightMap.put(flight.getBookingId(), flight);
        return flight;
    }

    @Override
    public FlightBooking read(Long id) {
        Helper.requireNonNull(id, "Flight Booking ID");
        return flightMap.get(id);
    }

    @Override
    public FlightBooking update(FlightBooking flight) {
        Helper.requireNonNull(flight, "Flight Booking");
        if (flight.getBookingId() == null) {
            throw new IllegalArgumentException("Flight Booking ID cannot be null");
        }
        if (!flightMap.containsKey(flight.getBookingId())) {
            throw new IllegalArgumentException("Flight Booking with ID " + flight.getBookingId() + " does not exist");
        }
        flightMap.put(flight.getBookingId(), flight);
        return flight;
    }

    @Override
    public FlightBooking delete(Long id) {
        Helper.requireNonNull(id, "Flight Booking ID");
        return flightMap.remove(id);
    }

    @Override
    public List<FlightBooking> getAll() {
        return new ArrayList<>(flightMap.values());
    }

    @Override
    public FlightBooking findById(Long id) {
        return read(id);
    }

    @Override
    public List<FlightBooking> findByCustomerId(Long customerId) {
        Helper.requireNonNull(customerId, "Customer ID");
        return flightMap.values().stream()
                .filter(flight -> flight.getBookedBy() != null &&
                        flight.getBookedBy().getUserId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightBooking> findByFlightNumber(String flightNumber) {
        Helper.requireNotEmptyOrNull(flightNumber, "Flight Number");
        return flightMap.values().stream()
                .filter(flight -> flight.getFlightNumber() != null &&
                        flight.getFlightNumber().equalsIgnoreCase(flightNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightBooking> findByAirline(String airline) {
        Helper.requireNotEmptyOrNull(airline, "Airline");
        return flightMap.values().stream()
                .filter(flight -> flight.getAirline() != null &&
                        flight.getAirline().equalsIgnoreCase(airline))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightBooking> findByFromLocationAndToLocation(String from, String to) {
        Helper.requireNotEmptyOrNull(from, "From Location");
        Helper.requireNotEmptyOrNull(to, "To Location");
        return flightMap.values().stream()
                .filter(flight -> flight.getFromLocation() != null &&
                        flight.getToLocation() != null &&
                        flight.getFromLocation().equalsIgnoreCase(from) &&
                        flight.getToLocation().equalsIgnoreCase(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightBooking> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end) {
        Helper.requireNonNull(start, "Start Date");
        Helper.requireNonNull(end, "End Date");
        return flightMap.values().stream()
                .filter(flight -> flight.getDepartureTime() != null &&
                        (flight.getDepartureTime().isAfter(start) || flight.getDepartureTime().isEqual(start)) &&
                        (flight.getDepartureTime().isBefore(end) || flight.getDepartureTime().isEqual(end)))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightBooking> findByBookingClass(String bookingClass) {
        Helper.requireNotEmptyOrNull(bookingClass, "Booking Class");
        return flightMap.values().stream()
                .filter(flight -> flight.getBookingClass() != null &&
                        flight.getBookingClass().toString().equalsIgnoreCase(bookingClass))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightBooking> findByStatus(String status) {
        Helper.requireNotEmptyOrNull(status, "Status");
        return flightMap.values().stream()
                .filter(flight -> flight.getStatus() != null &&
                        flight.getStatus().toString().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}
