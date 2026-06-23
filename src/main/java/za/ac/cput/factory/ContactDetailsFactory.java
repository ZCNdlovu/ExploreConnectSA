package za.ac.cput.factory;

import za.ac.cput.domain.ContactDetails;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

public class ContactDetailsFactory {

    private static final IdGenerator idGenerator = new IdGenerator();

    /**
     * Creates basic contact details
     */
    public static ContactDetails createContactDetails(String cellNumber, String email,String homePhone,
                                                      String workPhone,
                                                      String emergencyContact,
                                                      String emergencyPhone) {
        Helper.requireValidSouthAfricanPhone(cellNumber, "Cell Number");
        Helper.requireValidEmail(email, "Email");

        Long contactId = idGenerator.generateLongId();

        return new ContactDetails.Builder()
                .setContactId(contactId)
                .setCellNumber(cellNumber)
                .setEmail(email)
                .build();

    }

    /**
     * Creates full contact details with optional fields
     */
    public static ContactDetails createFullContactDetails(String cellNumber, String email,
                                                          String homePhone, String workPhone,
                                                          String emergencyContact, String emergencyPhone) {
        ContactDetails contact = createContactDetails(cellNumber, email);

        return new ContactDetails.Builder()
                .copy(contact)
                .setHomePhone(homePhone)
                .setWorkPhone(workPhone)
                .setEmergencyContact(emergencyContact)
                .setEmergencyPhone(emergencyPhone)
                .build();
    }
}
