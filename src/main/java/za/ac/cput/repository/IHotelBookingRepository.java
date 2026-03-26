package za.ac.cput.repository;

import za.ac.cput.domain.HotelBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface IHotelBookingRepository extends IRepository<HotelBooking, Long> {
    HotelBooking findById(Long id);

    List<HotelBooking> findByCustomerId(Long customerId);
    List<HotelBooking> findByHotelName(String hotelName);
    List<HotelBooking> findByLocation(String location);
    List<HotelBooking> findByCheckInBetween(LocalDateTime start, LocalDateTime end);
    List<HotelBooking> findByStarRating(int starRating);
    List<HotelBooking> findByRoomType(String roomType);
}
