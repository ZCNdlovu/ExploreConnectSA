package za.ac.cput.util;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Pattern;

public class Helper {
    public static void requireNotEmptyOrNull(String value, String fieldName) {
        if(value==null || value.isEmpty()){
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }
    public static boolean requireNotNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return true;
    }
    public static boolean isValidEmail(String email) {
        if (!isValidEmailWithRegex(email)) {
            throw new IllegalArgumentException(" is an invalid format");
        }
        return true;
    }

    public static boolean isValidEmailWithRegex(String email) {
        String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern PATTERN = Pattern.compile(REGEX_EMAIL);
        requireNotEmptyOrNull(email, "Email");
        if (!PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email");
        }
        return true;
    }

    public static boolean isValidEmailWithApacheCommons(String email){
        return EmailValidator.getInstance().isValid(email);
    }

}
