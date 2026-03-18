package za.ac.cput.domain;

import java.time.LocalDateTime;

public class Location {
    private double latitude;
    private double longitude;
    private String address;
    private LocalDateTime timestamp;

    private Location(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.address = builder.address;
        this.timestamp = builder.timestamp;
    }

    // Getters
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getAddress() { return address; }
    public LocalDateTime getTimestamp() { return timestamp; }

    // Business methods
    public double calculateDistance(Location other) {
        // Haversine formula for distance between two coordinates
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(other.latitude - this.latitude);
        double lonDistance = Math.toRadians(other.longitude - this.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public static class Builder {
        private double latitude;
        private double longitude;
        private String address;
        private LocalDateTime timestamp;

        public Builder(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.timestamp = LocalDateTime.now();
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder copy(Location location) {
            this.latitude = location.latitude;
            this.longitude = location.longitude;
            this.address = location.address;
            this.timestamp = location.timestamp;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}
