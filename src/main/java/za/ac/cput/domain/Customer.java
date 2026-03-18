package za.ac.cput.domain;

import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.*;

public class Customer extends User {
    private IdentityType identityType;
    private String identityNumber;
    private LocalDateTime dateOfBirth;
    private String nationality;
    private int loyaltyPoints;
    private LanguageType preferredLanguage;

    // Relationships
    private List<Address> addresses;
    private ContactDetails contacts;
    private LoyaltyProgram loyaltyProgram;
    private List<PaymentMethod> savedPayments;
    private List<Booking> bookingHistory;
    private List<Review> reviews;

    private Customer(Builder builder) {
        // User fields
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = UserRole.CUSTOMER;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.lastLogin = builder.lastLogin;

        // Customer specific fields
        this.identityType = builder.identityType;
        this.identityNumber = builder.identityNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.nationality = builder.nationality;
        this.loyaltyPoints = builder.loyaltyPoints;
        this.preferredLanguage = builder.preferredLanguage;

        // Collections
        this.addresses = builder.addresses != null ? builder.addresses : new ArrayList<>();
        this.contacts = builder.contacts;
        this.loyaltyProgram = builder.loyaltyProgram;
        this.savedPayments = builder.savedPayments != null ? builder.savedPayments : new ArrayList<>();
        this.bookingHistory = builder.bookingHistory != null ? builder.bookingHistory : new ArrayList<>();
        this.reviews = builder.reviews != null ? builder.reviews : new ArrayList<>();
    }

    // Getters
    public IdentityType getIdentityType() { return identityType; }
    public String getIdentityNumber() { return identityNumber; }
    public LocalDateTime getDateOfBirth() { return dateOfBirth; }
    public String getNationality() { return nationality; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public LanguageType getPreferredLanguage() { return preferredLanguage; }
    public List<Address> getAddresses() { return addresses; }
    public ContactDetails getContacts() { return contacts; }
    public LoyaltyProgram getLoyaltyProgram() { return loyaltyProgram; }
    public List<PaymentMethod> getSavedPayments() { return savedPayments; }
    public List<Booking> getBookingHistory() { return bookingHistory; }
    public List<Review> getReviews() { return reviews; }

    // Helper methods
    public String getPhoneNumber() {
        return contacts != null ? contacts.getCellNumber() : null;
    }

    public String getEmailAddress() {
        return email;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
    }

    public void addPaymentMethod(PaymentMethod method) {
        this.savedPayments.add(method);
    }

    public int calculateLoyaltyPoints() {
        // Calculate based on booking history
        return loyaltyPoints;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", identityType=" + identityType +
                ", identityNumber='" + identityNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", nationality='" + nationality + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", preferredLanguage=" + preferredLanguage +
                ", addresses=" + addresses.size() +
                ", contacts=" + contacts +
                ", loyaltyProgram=" + loyaltyProgram +
                ", savedPayments=" + savedPayments.size() +
                ", bookingHistory=" + bookingHistory.size() +
                ", reviews=" + reviews.size() +
                '}';
    }

    public static class Builder {
        // Required fields from User
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private boolean isActive;
        private LocalDateTime createdAt;
        private LocalDateTime lastLogin;

        // Customer specific fields
        private IdentityType identityType;
        private String identityNumber;
        private LocalDateTime dateOfBirth;
        private String nationality;
        private int loyaltyPoints;
        private LanguageType preferredLanguage;

        // Collections
        private List<Address> addresses;
        private ContactDetails contacts;
        private LoyaltyProgram loyaltyProgram;
        private List<PaymentMethod> savedPayments;
        private List<Booking> bookingHistory;
        private List<Review> reviews;

        // Required constructor with mandatory fields
        public Builder(String firstName, String lastName, String email, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.userId = IdGenerator.getInstance().generateId();
            this.createdAt = LocalDateTime.now();
            this.isActive = true;
        }

        public Builder setIdentityType(IdentityType identityType) {
            this.identityType = identityType;
            return this;
        }

        public Builder setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
            return this;
        }

        public Builder setDateOfBirth(LocalDateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setNationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder setLoyaltyPoints(int loyaltyPoints) {
            this.loyaltyPoints = loyaltyPoints;
            return this;
        }

        public Builder setPreferredLanguage(LanguageType preferredLanguage) {
            this.preferredLanguage = preferredLanguage;
            return this;
        }

        public Builder setAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public Builder setContacts(ContactDetails contacts) {
            this.contacts = contacts;
            return this;
        }

        public Builder setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
            this.loyaltyProgram = loyaltyProgram;
            return this;
        }

        public Builder setSavedPayments(List<PaymentMethod> savedPayments) {
            this.savedPayments = savedPayments;
            return this;
        }

        public Builder setBookingHistory(List<Booking> bookingHistory) {
            this.bookingHistory = bookingHistory;
            return this;
        }

        public Builder setReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setLastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder copy(Customer customer) {
            this.userId = customer.userId;
            this.firstName = customer.firstName;
            this.lastName = customer.lastName;
            this.email = customer.email;
            this.password = customer.password;
            this.isActive = customer.isActive;
            this.createdAt = customer.createdAt;
            this.lastLogin = customer.lastLogin;
            this.identityType = customer.identityType;
            this.identityNumber = customer.identityNumber;
            this.dateOfBirth = customer.dateOfBirth;
            this.nationality = customer.nationality;
            this.loyaltyPoints = customer.loyaltyPoints;
            this.preferredLanguage = customer.preferredLanguage;
            this.addresses = customer.addresses;
            this.contacts = customer.contacts;
            this.loyaltyProgram = customer.loyaltyProgram;
            this.savedPayments = customer.savedPayments;
            this.bookingHistory = customer.bookingHistory;
            this.reviews = customer.reviews;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
