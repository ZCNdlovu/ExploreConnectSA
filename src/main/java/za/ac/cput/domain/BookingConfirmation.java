package za.ac.cput.domain;

import za.ac.cput.util.IdGenerator;

import java.io.File;
import java.time.LocalDateTime;

public class BookingConfirmation {
    private String confirmationNumber;
    private String qrCode;
    private LocalDateTime confirmationTime;
    private Booking booking;
    private String termsAndConditions;

    private BookingConfirmation(Builder builder) {
        this.confirmationNumber = builder.confirmationNumber;
        this.qrCode = builder.qrCode;
        this.confirmationTime = builder.confirmationTime;
        this.booking = builder.booking;
        this.termsAndConditions = builder.termsAndConditions;
    }

    // Getters
    public String getConfirmationNumber() { return confirmationNumber; }
    public String getQrCode() { return qrCode; }
    public LocalDateTime getConfirmationTime() { return confirmationTime; }
    public Booking getBooking() { return booking; }
    public String getTermsAndConditions() { return termsAndConditions; }

    // Business methods
    public File printConfirmation() {
        // Generate PDF
        return new File(confirmationNumber + ".pdf");
    }

    public void emailConfirmation() {
        // Send email
        System.out.println("Confirmation emailed to " +
                booking.getBookedBy().getEmail());
    }

    public File downloadVoucher() {
        return new File(confirmationNumber + "_voucher.pdf");
    }

    public void addToCalendar() {
        // Add to calendar
        System.out.println("Added to calendar: " + booking.getBookingDetails());
    }

    @Override
    public String toString() {
        return "BookingConfirmation{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", confirmationTime=" + confirmationTime +
                ", booking=" + booking.getBookingReference() +
                '}';
    }

    public static class Builder {
        private String confirmationNumber;
        private String qrCode;
        private LocalDateTime confirmationTime;
        private Booking booking;
        private String termsAndConditions;

        public Builder(Booking booking) {
            this.confirmationNumber = "CNF-" + IdGenerator.getInstance().toString().substring(0, 8).toUpperCase();
            this.qrCode = "QR-" + IdGenerator.getInstance().toString();
            this.confirmationTime = LocalDateTime.now();
            this.booking = booking;
            this.termsAndConditions = "Standard terms and conditions apply.";
        }

        public Builder setTermsAndConditions(String termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
            return this;
        }

        public Builder copy(BookingConfirmation confirmation) {
            this.confirmationNumber = confirmation.confirmationNumber;
            this.qrCode = confirmation.qrCode;
            this.confirmationTime = confirmation.confirmationTime;
            this.booking = confirmation.booking;
            this.termsAndConditions = confirmation.termsAndConditions;
            return this;
        }

        public BookingConfirmation build() {
            return new BookingConfirmation(this);
        }
    }
}
