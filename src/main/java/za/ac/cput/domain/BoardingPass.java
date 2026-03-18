package za.ac.cput.domain;

import java.time.LocalDateTime;

public class BoardingPass {
    private String bookingReference;
    private String flightNumber;
    private String seatNumber;
    private String gate;
    private LocalDateTime boardingTime;
    private String qrCode;

    private BoardingPass(Builder builder) {
        this.bookingReference = builder.bookingReference;
        this.flightNumber = builder.flightNumber;
        this.seatNumber = builder.seatNumber;
        this.gate = builder.gate;
        this.boardingTime = builder.boardingTime;
        this.qrCode = builder.qrCode;
    }

    // Getters
    public String getBookingReference() { return bookingReference; }
    public String getFlightNumber() { return flightNumber; }
    public String getSeatNumber() { return seatNumber; }
    public String getGate() { return gate; }
    public LocalDateTime getBoardingTime() { return boardingTime; }
    public String getQrCode() { return qrCode; }

    // Business methods
    public void generate() {
        this.qrCode = "BP-" + bookingReference + "-" + seatNumber;
        this.boardingTime = LocalDateTime.now().plusHours(2); // 2 hours before flight
    }

    public boolean validate() {
        return qrCode != null && qrCode.startsWith("BP-");
    }

    @Override
    public String toString() {
        return "BoardingPass{" +
                "flightNumber='" + flightNumber + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", gate='" + gate + '\'' +
                ", boardingTime=" + boardingTime +
                '}';
    }

    public static class Builder {
        private String bookingReference;
        private String flightNumber;
        private String seatNumber;
        private String gate;
        private LocalDateTime boardingTime;
        private String qrCode;

        public Builder(FlightBooking flightBooking) {
            this.bookingReference = flightBooking.getBookingReference();
            this.flightNumber = flightBooking.getFlightNumber();
            this.seatNumber = flightBooking.getSeatNumbers();
            this.gate = "A" + (int)(Math.random() * 10); // Random gate for demo
            this.boardingTime = flightBooking.getDepartureTime().minusHours(2);
            generateQR();
        }

        private void generateQR() {
            this.qrCode = "BP-" + bookingReference + "-" + seatNumber;
        }

        public Builder setGate(String gate) {
            this.gate = gate;
            return this;
        }

        public Builder setSeatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
            generateQR();
            return this;
        }

        public Builder copy(BoardingPass pass) {
            this.bookingReference = pass.bookingReference;
            this.flightNumber = pass.flightNumber;
            this.seatNumber = pass.seatNumber;
            this.gate = pass.gate;
            this.boardingTime = pass.boardingTime;
            this.qrCode = pass.qrCode;
            return this;
        }

        public BoardingPass build() {
            return new BoardingPass(this);
        }
    }
}
