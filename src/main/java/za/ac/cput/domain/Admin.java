package za.ac.cput.domain;

import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.*;

public class Admin  extends User {
    private String empId;
    private String department;
    private String accessLevel;
    private LocalDateTime hireDate;

    // Relationships
    private List<Report> generatedReports;
    private List<Booking> managedBookings;

    private Admin(Builder builder) {
        // User fields
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = UserRole.ADMIN;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.lastLogin = builder.lastLogin;

        // Admin specific fields
        this.empId = builder.empId;
        this.department = builder.department;
        this.accessLevel = builder.accessLevel;
        this.hireDate = builder.hireDate;

        // Collections
        this.generatedReports = builder.generatedReports != null ? builder.generatedReports : new ArrayList<>();
        this.managedBookings = builder.managedBookings != null ? builder.managedBookings : new ArrayList<>();
    }

    // Getters
    public String getEmpId() { return empId; }
    public String getDepartment() { return department; }
    public String getAccessLevel() { return accessLevel; }
    public LocalDateTime getHireDate() { return hireDate; }
    public List<Report> getGeneratedReports() { return generatedReports; }
    public List<Booking> getManagedBookings() { return managedBookings; }

    // Admin methods
    public void manageUsers() {
        // Implementation for user management
    }

    public List<Booking> viewAllBookings() {
        return managedBookings;
    }

    public Report generateReport(Report report) {
        this.generatedReports.add(report);
        return report;
    }

    public void updateSystemSettings() {
        // Implementation for system settings
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", empId='" + empId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", hireDate=" + hireDate +
                ", generatedReports=" + generatedReports.size() +
                ", managedBookings=" + managedBookings.size() +
                '}';
    }

    public static class Builder {
        // Required fields
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private boolean isActive;
        private LocalDateTime createdAt;
        private LocalDateTime lastLogin;
        private String empId;

        // Optional fields
        private String department;
        private String accessLevel;
        private LocalDateTime hireDate;
        private List<Report> generatedReports;
        private List<Booking> managedBookings;

        public Builder(String firstName, String lastName, String email, String password, String empId) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.empId = empId;
            this.userId = IdGenerator.getInstance().generateId();
            this.createdAt = LocalDateTime.now();
            this.isActive = true;
            this.hireDate = LocalDateTime.now();
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setAccessLevel(String accessLevel) {
            this.accessLevel = accessLevel;
            return this;
        }

        public Builder setHireDate(LocalDateTime hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Builder setGeneratedReports(List<Report> generatedReports) {
            this.generatedReports = generatedReports;
            return this;
        }

        public Builder setManagedBookings(List<Booking> managedBookings) {
            this.managedBookings = managedBookings;
            return this;
        }

        public Builder setLastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder copy(Admin admin) {
            this.userId = admin.userId;
            this.firstName = admin.firstName;
            this.lastName = admin.lastName;
            this.email = admin.email;
            this.password = admin.password;
            this.isActive = admin.isActive;
            this.createdAt = admin.createdAt;
            this.lastLogin = admin.lastLogin;
            this.empId = admin.empId;
            this.department = admin.department;
            this.accessLevel = admin.accessLevel;
            this.hireDate = admin.hireDate;
            this.generatedReports = admin.generatedReports;
            this.managedBookings = admin.managedBookings;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
