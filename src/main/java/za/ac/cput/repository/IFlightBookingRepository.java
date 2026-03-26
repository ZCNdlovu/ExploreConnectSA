package za.ac.cput.repository;

import za.ac.cput.domain.FlightBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface IFlightBookingRepository extends IRepository<FlightBooking, Long> {
    FlightBooking findById(Long id);

    List<FlightBooking> findByCustomerId(Long customerId);
    List<FlightBooking> findByFlightNumber(String flightNumber);
    List<FlightBooking> findByAirline(String airline);
    List<FlightBooking> findByFromLocationAndToLocation(String from, String to);
    List<FlightBooking> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
    List<FlightBooking> findByBookingClass(String bookingClass);
    List<FlightBooking> findByStatus(String status);
}
