package za.ac.cput.repository;

import za.ac.cput.domain.CarRentalBooking;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarRentalBookingRepository  implements ICarRentalBookingRepository{
    private static CarRentalBookingRepository repo = null;
    private Map<Long, CarRentalBooking> carMap;

    private CarRentalBookingRepository() {
        carMap = new HashMap<>();
    }

    public static CarRentalBookingRepository getRepository() {
        if (repo == null) {
            repo = new CarRentalBookingRepository();
        }
        return repo;
    }

    @Override
    public CarRentalBooking create(CarRentalBooking car) {
        Helper.requireNonNull(car, "Car Rental Booking");
        if (car.getBookingId() == null) {
            throw new IllegalArgumentException("Car Rental Booking ID cannot be null");
        }
        if (carMap.containsKey(car.getBookingId())) {
            throw new IllegalArgumentException("Car Rental Booking with ID " + car.getBookingId() + " already exists");
        }
        carMap.put(car.getBookingId(), car);
        return car;
    }

    @Override
    public CarRentalBooking read(Long id) {
        Helper.requireNonNull(id, "Car Rental Booking ID");
        return carMap.get(id);
    }

    @Override
    public CarRentalBooking update(CarRentalBooking car) {
        Helper.requireNonNull(car, "Car Rental Booking");
        if (car.getBookingId() == null) {
            throw new IllegalArgumentException("Car Rental Booking ID cannot be null");
        }
        if (!carMap.containsKey(car.getBookingId())) {
            throw new IllegalArgumentException("Car Rental Booking with ID " + car.getBookingId() + " does not exist");
        }
        carMap.put(car.getBookingId(), car);
        return car;
    }

    @Override
    public CarRentalBooking delete(Long id) {
        Helper.requireNonNull(id, "Car Rental Booking ID");
        return carMap.remove(id);
    }

    @Override
    public List<CarRentalBooking> getAll() {
        return new ArrayList<>(carMap.values());
    }

    @Override
    public CarRentalBooking findById(Long id) {
        return read(id);
    }

    @Override
    public List<CarRentalBooking> findByCustomerId(Long customerId) {
        Helper.requireNonNull(customerId, "Customer ID");
        return carMap.values().stream()
                .filter(car -> car.getBookedBy() != null &&
                        car.getBookedBy().getUserId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarRentalBooking> findByRentalCompany(String rentalCompany) {
        Helper.requireNotEmptyOrNull(rentalCompany, "Rental Company");
        return carMap.values().stream()
                .filter(car -> car.getRentalCompany() != null &&
                        car.getRentalCompany().equalsIgnoreCase(rentalCompany))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarRentalBooking> findByCarModel(String carModel) {
        Helper.requireNotEmptyOrNull(carModel, "Car Model");
        return carMap.values().stream()
                .filter(car -> car.getCarModel() != null &&
                        car.getCarModel().equalsIgnoreCase(carModel))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarRentalBooking> findByPickupDateBetween(LocalDateTime start, LocalDateTime end) {
        Helper.requireNonNull(start, "Start Date");
        Helper.requireNonNull(end, "End Date");
        return carMap.values().stream()
                .filter(car -> car.getPickupDate() != null &&
                        (car.getPickupDate().isAfter(start) || car.getPickupDate().isEqual(start)) &&
                        (car.getPickupDate().isBefore(end) || car.getPickupDate().isEqual(end)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarRentalBooking> findByReturnDateBetween(LocalDateTime start, LocalDateTime end) {
        Helper.requireNonNull(start, "Start Date");
        Helper.requireNonNull(end, "End Date");
        return carMap.values().stream()
                .filter(car -> car.getReturnDate() != null &&
                        (car.getReturnDate().isAfter(start) || car.getReturnDate().isEqual(start)) &&
                        (car.getReturnDate().isBefore(end) || car.getReturnDate().isEqual(end)))
                .collect(Collectors.toList());
    }
}
