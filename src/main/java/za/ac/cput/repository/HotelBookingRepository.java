package za.ac.cput.repository;

import za.ac.cput.domain.HotelBooking;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelBookingRepository implements IHotelBookingRepository{
    private static HotelBookingRepository repo = null;
    private Map<Long, HotelBooking> hotelMap;

    private HotelBookingRepository() {
        hotelMap = new HashMap<>();
    }

    public static HotelBookingRepository getRepository() {
        if (repo == null) {
            repo = new HotelBookingRepository();
        }
        return repo;
    }

    @Override
    public HotelBooking create(HotelBooking hotel) {
        Helper.requireNonNull(hotel, "Hotel Booking");
        if (hotel.getBookingId() == null) {
            throw new IllegalArgumentException("Hotel Booking ID cannot be null");
        }
        if (hotelMap.containsKey(hotel.getBookingId())) {
            throw new IllegalArgumentException("Hotel Booking with ID " + hotel.getBookingId() + " already exists");
        }
        hotelMap.put(hotel.getBookingId(), hotel);
        return hotel;
    }

    @Override
    public HotelBooking read(Long id) {
        Helper.requireNonNull(id, "Hotel Booking ID");
        return hotelMap.get(id);
    }

    @Override
    public HotelBooking update(HotelBooking hotel) {
        Helper.requireNonNull(hotel, "Hotel Booking");
        if (hotel.getBookingId() == null) {
            throw new IllegalArgumentException("Hotel Booking ID cannot be null");
        }
        if (!hotelMap.containsKey(hotel.getBookingId())) {
            throw new IllegalArgumentException("Hotel Booking with ID " + hotel.getBookingId() + " does not exist");
        }
        hotelMap.put(hotel.getBookingId(), hotel);
        return hotel;
    }

    @Override
    public HotelBooking delete(Long id) {
        Helper.requireNonNull(id, "Hotel Booking ID");
        return hotelMap.remove(id);
    }

    @Override
    public List<HotelBooking> getAll() {
        return new ArrayList<>(hotelMap.values());
    }

    @Override
    public HotelBooking findById(Long id) {
        return read(id);
    }

    @Override
    public List<HotelBooking> findByCustomerId(Long customerId) {
        Helper.requireNonNull(customerId, "Customer ID");
        return hotelMap.values().stream()
                .filter(hotel -> hotel.getBookedBy() != null &&
                        hotel.getBookedBy().getUserId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelBooking> findByHotelName(String hotelName) {
        Helper.requireNotEmptyOrNull(hotelName, "Hotel Name");
        return hotelMap.values().stream()
                .filter(hotel -> hotel.getHotelName() != null &&
                        hotel.getHotelName().equalsIgnoreCase(hotelName))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelBooking> findByLocation(String location) {
        Helper.requireNotEmptyOrNull(location, "Location");
        return hotelMap.values().stream()
                .filter(hotel -> hotel.getLocation() != null &&
                        hotel.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelBooking> findByCheckInBetween(LocalDateTime start, LocalDateTime end) {
        Helper.requireNonNull(start, "Start Date");
        Helper.requireNonNull(end, "End Date");
        return hotelMap.values().stream()
                .filter(hotel -> hotel.getCheckIn() != null &&
                        (hotel.getCheckIn().isAfter(start) || hotel.getCheckIn().isEqual(start)) &&
                        (hotel.getCheckIn().isBefore(end) || hotel.getCheckIn().isEqual(end)))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelBooking> findByStarRating(int starRating) {
        if (starRating < 1 || starRating > 5) {
            throw new IllegalArgumentException("Star rating must be between 1 and 5");
        }
        return hotelMap.values().stream()
                .filter(hotel -> hotel.getStarRating() == starRating)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelBooking> findByRoomType(String roomType) {
        Helper.requireNotEmptyOrNull(roomType, "Room Type");
        return hotelMap.values().stream()
                .filter(hotel -> hotel.getRoomType() != null &&
                        hotel.getRoomType().toString().equalsIgnoreCase(roomType))
                .collect(Collectors.toList());
    }
}
