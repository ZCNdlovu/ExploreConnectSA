package za.ac.cput.domain;

import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class PaymentDetails {
    private Long paymentId;
    private String transactionId;
    private PaymentMethod method;
    private PaymentStatus status;
    private double amount;
    private String currency;
    private LocalDateTime paymentDate;
    private LocalDateTime lastUpdated;
    private BillingAddress billingAddress;
    private CreditCardDetails creditCardDetails;
    private String receiptUrl;

    private PaymentDetails(Builder builder) {
        this.paymentId = builder.paymentId;
        this.transactionId = builder.transactionId;
        this.method = builder.method;
        this.status = builder.status;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.paymentDate = builder.paymentDate;
        this.lastUpdated = builder.lastUpdated;
        this.billingAddress = builder.billingAddress;
        this.creditCardDetails = builder.creditCardDetails;
        this.receiptUrl = builder.receiptUrl;
    }

    // Getters
    public Long getPaymentId() { return paymentId; }
    public String getTransactionId() { return transactionId; }
    public PaymentMethod getMethod() { return method; }
    public PaymentStatus getStatus() { return status; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public BillingAddress getBillingAddress() { return billingAddress; }
    public CreditCardDetails getCreditCardDetails() { return creditCardDetails; }
    public String getReceiptUrl() { return receiptUrl; }

    // Business methods
    public boolean processPayment() {
        this.status = PaymentStatus.PAID;
        this.transactionId = "TXN-" + IdGenerator.generateId().toString().substring(0, 10);
        this.paymentDate = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        return true;
    }

    public boolean refund() {
        if (status == PaymentStatus.PAID) {
            this.status = PaymentStatus.REFUNDED;
            this.lastUpdated = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public String generateReceipt() {
        this.receiptUrl = "/receipts/" + transactionId + ".pdf";
        return receiptUrl;
    }

    public boolean validatePayment() {
        return amount > 0 && method != null &&
                (method != PaymentMethod.CREDIT_CARD || creditCardDetails != null);
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "paymentId=" + paymentId +
                ", transactionId='" + transactionId + '\'' +
                ", method=" + method +
                ", status=" + status +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }

    public static class Builder {
        private Long paymentId;
        private String transactionId;
        private PaymentMethod method;
        private PaymentStatus status;
        private double amount;
        private String currency;
        private LocalDateTime paymentDate;
        private LocalDateTime lastUpdated;
        private BillingAddress billingAddress;
        private CreditCardDetails creditCardDetails;
        private String receiptUrl;

        public Builder(PaymentMethod method, double amount, String currency) {
            this.paymentId = IdGenerator.getInstance().generateId();
            this.method = method;
            this.amount = amount;
            this.currency = currency;
            this.status = PaymentStatus.PENDING;
            this.lastUpdated = LocalDateTime.now();
        }

        public Builder setTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder setStatus(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Builder setPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setBillingAddress(BillingAddress billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        public Builder setCreditCardDetails(CreditCardDetails creditCardDetails) {
            this.creditCardDetails = creditCardDetails;
            return this;
        }

        public Builder setReceiptUrl(String receiptUrl) {
            this.receiptUrl = receiptUrl;
            return this;
        }

        public Builder copy(PaymentDetails payment) {
            this.paymentId = payment.paymentId;
            this.transactionId = payment.transactionId;
            this.method = payment.method;
            this.status = payment.status;
            this.amount = payment.amount;
            this.currency = payment.currency;
            this.paymentDate = payment.paymentDate;
            this.lastUpdated = payment.lastUpdated;
            this.billingAddress = payment.billingAddress;
            this.creditCardDetails = payment.creditCardDetails;
            this.receiptUrl = payment.receiptUrl;
            return this;
        }

        public PaymentDetails build() {
            return new PaymentDetails(this);
        }
    }
}
