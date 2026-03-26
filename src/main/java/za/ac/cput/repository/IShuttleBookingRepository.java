package za.ac.cput.repository;

import za.ac.cput.domain.ShuttleBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface IShuttleBookingRepository extends IRepository<ShuttleBooking, Long> {
    ShuttleBooking findById(Long id);

    List<ShuttleBooking> findByCustomerId(Long customerId);
    List<ShuttleBooking> findByCompany(String company);
    List<ShuttleBooking> findByPickupLocation(String location);
    List<ShuttleBooking> findByPickupTimeBetween(LocalDateTime start, LocalDateTime end);
}
