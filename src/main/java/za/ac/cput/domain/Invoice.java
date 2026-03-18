package za.ac.cput.domain;

import za.ac.cput.util.IdGenerator;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

public class Invoice {
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    private Booking booking;
    private BillingAddress billingAddress;
    private List<LineItem> items;
    private double subtotal;
    private double tax;
    private double total;
    private String paymentTerms;

    private Invoice(Builder builder) {
        this.invoiceNumber = builder.invoiceNumber;
        this.issueDate = builder.issueDate;
        this.dueDate = builder.dueDate;
        this.booking = builder.booking;
        this.billingAddress = builder.billingAddress;
        this.items = builder.items != null ? builder.items : new ArrayList<>();
        this.subtotal = builder.subtotal;
        this.tax = builder.tax;
        this.total = builder.total;
        this.paymentTerms = builder.paymentTerms;

        calculateTotals();
    }

    // Getters
    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDateTime getIssueDate() { return issueDate; }
    public LocalDateTime getDueDate() { return dueDate; }
    public Booking getBooking() { return booking; }
    public BillingAddress getBillingAddress() { return billingAddress; }
    public List<LineItem> getItems() { return items; }
    public double getSubtotal() { return subtotal; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }
    public String getPaymentTerms() { return paymentTerms; }

    // Business methods
    public void calculateTotals() {
        this.subtotal = items.stream()
                .mapToDouble(LineItem::getTotal)
                .sum();
        this.tax = subtotal * 0.15; // 15% VAT
        this.total = subtotal + tax;
    }

    public File generatePDF() {
        return new File(invoiceNumber + ".pdf");
    }

    public void sendViaEmail() {
        System.out.println("Invoice " + invoiceNumber + " emailed to " +
                booking.getBookedBy().getEmail());
    }

    public void addItem(LineItem item) {
        this.items.add(item);
        calculateTotals();
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }

    public static class Builder {
        private String invoiceNumber;
        private LocalDateTime issueDate;
        private LocalDateTime dueDate;
        private Booking booking;
        private BillingAddress billingAddress;
        private List<LineItem> items;
        private double subtotal;
        private double tax;
        private double total;
        private String paymentTerms;

        public Builder(Booking booking) {
            this.invoiceNumber = "INV-" + IdGenerator.getInstance().toString().substring(0, 8).toUpperCase();
            this.issueDate = LocalDateTime.now();
            this.dueDate = issueDate.plusDays(7);
            this.booking = booking;
            this.paymentTerms = "Due within 7 days";
            this.items = new ArrayList<>();

            // Add default line item for the booking
            LineItem defaultItem = new LineItem.Builder(
                    "Booking: " + booking.getBookingDetails(),
                    1,
                    booking.getSubtotal()
            ).build();
            this.items.add(defaultItem);
        }

        public Builder setBillingAddress(BillingAddress billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        public Builder setItems(List<LineItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(LineItem item) {
            if (this.items == null) {
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return this;
        }

        public Builder setPaymentTerms(String paymentTerms) {
            this.paymentTerms = paymentTerms;
            return this;
        }

        public Builder copy(Invoice invoice) {
            this.invoiceNumber = invoice.invoiceNumber;
            this.issueDate = invoice.issueDate;
            this.dueDate = invoice.dueDate;
            this.booking = invoice.booking;
            this.billingAddress = invoice.billingAddress;
            this.items = invoice.items;
            this.subtotal = invoice.subtotal;
            this.tax = invoice.tax;
            this.total = invoice.total;
            this.paymentTerms = invoice.paymentTerms;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }
}
