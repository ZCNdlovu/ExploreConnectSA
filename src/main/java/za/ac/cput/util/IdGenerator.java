package za.ac.cput.util;

import java.util.concurrent.atomic.AtomicLong;
import java.util.HashMap;
import java.util.Map;

public class IdGenerator {
    private static IdGenerator instance;
    private final Map<String, AtomicLong> counters;
    private final AtomicLong defaultCounter;

    private IdGenerator() {
        this.defaultCounter = new AtomicLong(1);
        this.counters = new HashMap<>();
    }

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    /**
     * Generate default sequential ID
     */
    public long generateId() {
        return defaultCounter.getAndIncrement();
    }

    /**
     * Generate ID for specific entity type
     */
    public long generateId(String entityType) {
        AtomicLong counter = counters.computeIfAbsent(
                entityType,
                k -> new AtomicLong(1)
        );
        return counter.getAndIncrement();
    }

    /**
     * Generate formatted ID with entity prefix
     */
    public String generateFormattedId(String entityType) {
        AtomicLong counter = counters.computeIfAbsent(
                entityType,
                k -> new AtomicLong(1)
        );
        return entityType + "-" + String.format("%06d", counter.getAndIncrement());
    }

    /**
     * Generate customer ID
     */
    public String generateCustomerId() {
        return "CUST-" + String.format("%06d", generateId("CUSTOMER"));
    }

    /**
     * Generate flight booking ID
     */
    public String generateFlightBookingId() {
        return "FLT-" + String.format("%06d", generateId("FLIGHT"));
    }

    /**
     * Generate hotel booking ID
     */
    public String generateHotelBookingId() {
        return "HTL-" + String.format("%06d", generateId("HOTEL"));
    }

    /**
     * Generate shuttle booking ID
     */
    public String generateShuttleBookingId() {
        return "SHT-" + String.format("%06d", generateId("SHUTTLE"));
    }

    /**
     * Generate car rental booking ID
     */
    public String generateCarRentalId() {
        return "CAR-" + String.format("%06d", generateId("CAR"));
    }

    /**
     * Reset all counters (for testing)
     */
    public void reset() {
        defaultCounter.set(1);
        counters.clear();
    }
}