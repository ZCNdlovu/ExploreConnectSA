package za.ac.cput.domain;

import java.time.LocalDateTime;

public abstract class User {
    protected Long userId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected UserRole role;
    protected boolean isActive;
    protected LocalDateTime createdAt;
    protected LocalDateTime lastLogin;

    // Abstract builder will be in concrete classes
    public Long getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }
    public boolean isActive() { return isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastLogin() { return lastLogin; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                '}';
    }

}

