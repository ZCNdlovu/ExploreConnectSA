package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import java.time.LocalDateTime;
import za.ac.cput.util.IdGenerator;


public class CustomerFactory {

    private static final IdGenerator idGenerator = new IdGenerator();

    /**
     * Creates a basic customer with minimal required fields
     */
    public static Customer createCustomer(String firstName, String lastName,
                                          String email, String password,
                                          String phoneNumber) {
        // Validate required fields
        Helper.requireNotEmptyOrNull(firstName, "First Name");
        Helper.requireNotEmptyOrNull(lastName, "Last Name");
        Helper.requireValidEmail(email, "Email");
        Helper.requireNotEmptyOrNull(password, "Password");
        Helper.requireValidSouthAfricanPhone(phoneNumber, "Phone Number");

        // Generate ID
        String customerId = idGenerator.generateId("CUS");

        return new Customer.Builder()
                .setUserId(Long.parseLong(customerId.substring(3)))
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .build();
    }

    /**
     * Creates a customer with identity details
     */
    public static Customer createCustomerWithIdentity(String firstName, String lastName,
                                                      String email, String password,
                                                      String phoneNumber,
                                                      IdentityType identityType,
                                                      String identityNumber,
                                                      LocalDateTime dateOfBirth,
                                                      String nationality) {
        Customer customer = createCustomer(firstName, lastName, email, password, phoneNumber);

        // Validate identity details
        if (identityType == IdentityType.RSA_ID) {
            Helper.requireValidSouthAfricanId(identityNumber, "RSA ID");
        } else if (identityType == IdentityType.PASSPORT) {
            Helper.requireValidPassportNumber(identityNumber, "Passport Number");
        }
        Helper.requireNonNullDate(dateOfBirth, "Date of Birth");
        Helper.requireNotEmptyOrNull(nationality, "Nationality");

        return new Customer.Builder()
                .copy(customer)
                .setIdentityType(identityType)
                .setIdentityNumber(identityNumber)
                .setDateOfBirth(dateOfBirth)
                .setNationality(nationality)
                .build();
    }

    /**
     * Creates a full customer with all details
     */
    public static Customer createFullCustomer(String firstName, String lastName,
                                              String email, String password,
                                              String phoneNumber,
                                              IdentityType identityType,
                                              String identityNumber,
                                              LocalDateTime dateOfBirth,
                                              String nationality,
                                              LanguageType preferredLanguage) {
        Customer customer = createCustomerWithIdentity(firstName, lastName, email,
                password, phoneNumber, identityType, identityNumber, dateOfBirth, nationality);

        return new Customer.Builder()
                .copy(customer)
                .setPreferredLanguage(preferredLanguage)
                .build();
    }
}
