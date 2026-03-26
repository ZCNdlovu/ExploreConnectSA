package za.ac.cput.repository;

import za.ac.cput.domain.CarRentalBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface ICarRentalBookingRepository extends IRepository<CarRentalBooking, Long> {
    CarRentalBooking findById(Long id);

    List<CarRentalBooking> findByCustomerId(Long customerId);
    List<CarRentalBooking> findByRentalCompany(String rentalCompany);
    List<CarRentalBooking> findByCarModel(String carModel);
    List<CarRentalBooking> findByPickupDateBetween(LocalDateTime start, LocalDateTime end);
    List<CarRentalBooking> findByReturnDateBetween(LocalDateTime start, LocalDateTime end);
}
