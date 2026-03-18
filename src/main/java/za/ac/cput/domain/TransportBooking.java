package za.ac.cput.domain;

import java.time.LocalDateTime;

public abstract class TransportBooking extends Booking {
    protected String transportId;
    protected String provider;
    protected VehicleType vehicleType;
    protected LocalDateTime bookingTime;
    protected double distance;
    protected String specialInstructions;

    // Getters
    public String getTransportId() { return transportId; }
    public String getProvider() { return provider; }
    public VehicleType getVehicleType() { return vehicleType; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public double getDistance() { return distance; }
    public String getSpecialInstructions() { return specialInstructions; }

    @Override
    public String toString() {
        return "TransportBooking{" +
                "transportId='" + transportId + '\'' +
                ", provider='" + provider + '\'' +
                ", vehicleType=" + vehicleType +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
