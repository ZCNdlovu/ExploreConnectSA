package za.ac.cput.domain;

import java.time.LocalDateTime;

public abstract class Booking {
    protected Long bookingId;
    protected String bookingReference;
    protected LocalDateTime bookingDate;
    protected LocalDateTime lastModified;
    protected BookingStatus status;
    protected double subtotal;
    protected double taxes;
    protected double totalPrice;
    protected String currency;

    // Relationships
    protected Customer bookedBy;
    protected Traveler travelers;
    protected PaymentDetails payment;
    protected CancellationPolicy cancellationPolicy;

    // Getters
    public Long getBookingId() { return bookingId; }
    public String getBookingReference() { return bookingReference; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public LocalDateTime getLastModified() { return lastModified; }
    public BookingStatus getStatus() { return status; }
    public double getSubtotal() { return subtotal; }
    public double getTaxes() { return taxes; }
    public double getTotalPrice() { return totalPrice; }
    public String getCurrency() { return currency; }
    public Customer getBookedBy() { return bookedBy; }
    public Traveler getTravelers() { return travelers; }
    public PaymentDetails getPayment() { return payment; }
    public CancellationPolicy getCancellationPolicy() { return cancellationPolicy; }

    // Business methods
    public double calculateTotal() {
        this.totalPrice = subtotal + taxes;
        return totalPrice;
    }

    public BookingConfirmation confirmBooking() {
        this.status = BookingStatus.CONFIRMED;
        this.lastModified = LocalDateTime.now();
        return new BookingConfirmation.Builder(this).build();
    }

    public boolean cancelBooking() {
        if (cancellationPolicy != null && cancellationPolicy.isEligible(bookingDate, LocalDateTime.now())) {
            this.status = BookingStatus.CANCELLED;
            this.lastModified = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public abstract Booking modifyBooking();
    public abstract String getBookingDetails();
    public abstract Invoice generateInvoice();

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingReference='" + bookingReference + '\'' +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", bookedBy=" + (bookedBy != null ? bookedBy.getFullName() : null) +
                '}';
    }
}
