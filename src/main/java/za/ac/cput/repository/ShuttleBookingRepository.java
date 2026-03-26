package za.ac.cput.repository;

import za.ac.cput.domain.ShuttleBooking;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShuttleBookingRepository  implements IShuttleBookingRepository {
    private static ShuttleBookingRepository repo = null;
    private Map<Long, ShuttleBooking> shuttleMap;

    private ShuttleBookingRepository() {
        shuttleMap = new HashMap<>();
    }

    public static ShuttleBookingRepository getRepository() {
        if (repo == null) {
            repo = new ShuttleBookingRepository();
        }
        return repo;
    }

    @Override
    public ShuttleBooking create(ShuttleBooking shuttle) {
        Helper.requireNonNull(shuttle, "Shuttle Booking");
        if (shuttle.getBookingId() == null) {
            throw new IllegalArgumentException("Shuttle Booking ID cannot be null");
        }
        if (shuttleMap.containsKey(shuttle.getBookingId())) {
            throw new IllegalArgumentException("Shuttle Booking with ID " + shuttle.getBookingId() + " already exists");
        }
        shuttleMap.put(shuttle.getBookingId(), shuttle);
        return shuttle;
    }

    @Override
    public ShuttleBooking read(Long id) {
        Helper.requireNonNull(id, "Shuttle Booking ID");
        return shuttleMap.get(id);
    }

    @Override
    public ShuttleBooking update(ShuttleBooking shuttle) {
        Helper.requireNonNull(shuttle, "Shuttle Booking");
        if (shuttle.getBookingId() == null) {
            throw new IllegalArgumentException("Shuttle Booking ID cannot be null");
        }
        if (!shuttleMap.containsKey(shuttle.getBookingId())) {
            throw new IllegalArgumentException("Shuttle Booking with ID " + shuttle.getBookingId() + " does not exist");
        }
        shuttleMap.put(shuttle.getBookingId(), shuttle);
        return shuttle;
    }

    @Override
    public ShuttleBooking delete(Long id) {
        Helper.requireNonNull(id, "Shuttle Booking ID");
        return shuttleMap.remove(id);
    }

    @Override
    public List<ShuttleBooking> getAll() {
        return new ArrayList<>(shuttleMap.values());
    }

    @Override
    public ShuttleBooking findById(Long id) {
        return read(id);
    }

    @Override
    public List<ShuttleBooking> findByCustomerId(Long customerId) {
        Helper.requireNonNull(customerId, "Customer ID");
        return shuttleMap.values().stream()
                .filter(shuttle -> shuttle.getBookedBy() != null &&
                        shuttle.getBookedBy().getUserId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShuttleBooking> findByCompany(String company) {
        Helper.requireNotEmptyOrNull(company, "Company");
        return shuttleMap.values().stream()
                .filter(shuttle -> shuttle.getCompany() != null &&
                        shuttle.getCompany().toString().equalsIgnoreCase(company))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShuttleBooking> findByPickupLocation(String location) {
        Helper.requireNotEmptyOrNull(location, "Pickup Location");
        return shuttleMap.values().stream()
                .filter(shuttle -> shuttle.getPickUpLocation() != null &&
                        shuttle.getPickUpLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShuttleBooking> findByPickupTimeBetween(LocalDateTime start, LocalDateTime end) {
        Helper.requireNonNull(start, "Start Time");
        Helper.requireNonNull(end, "End Time");
        return shuttleMap.values().stream()
                .filter(shuttle -> shuttle.getPickupTime() != null &&
                        (shuttle.getPickupTime().isAfter(start) || shuttle.getPickupTime().isEqual(start)) &&
                        (shuttle.getPickupTime().isBefore(end) || shuttle.getPickupTime().isEqual(end)))
                .collect(Collectors.toList());
    }
}
