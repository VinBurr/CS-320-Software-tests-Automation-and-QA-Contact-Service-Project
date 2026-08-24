/**
 * Vincent Esposito
 * 7/16/2026
 * Southern New Hampshire University 
 */

public class Contact {
    
    // Private fields
    private final String contactId;  // Not updatable - made final
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    
    /**
     * Constructor with full validation.
     * Throws IllegalArgumentException for any invalid input (this allows tests to verify error conditions).
     */
    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        // Validate contactId: not null, not empty, and <= 10 characters
        if (contactId == null || contactId.isEmpty() || contactId.length() > 10) {
            throw new IllegalArgumentException("Contact ID cannot be null or empty and must be 10 characters or less.");
        }
        
        // Validate firstName: not null, not empty, and <= 10 characters
        if (firstName == null || firstName.isEmpty() || firstName.length() > 10) {
            throw new IllegalArgumentException("First name cannot be null or empty and must be 10 characters or less.");
        }
        
        // Validate lastName: not null, not empty, and <= 10 characters
        if (lastName == null || lastName.isEmpty() || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be null or empty and must be 10 characters or less.");
        }
        
        // Validate phone: not null, exactly 10 digits
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits and cannot be null.");
        }
        
        // Validate address: not null, not empty, and <= 30 characters
        if (address == null || address.isEmpty() || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null or empty and must be 30 characters or less.");
        }
        
        // Assign values (contactId is final)
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }
    
    // Getters - Read Values
    
    public String getContactId() {
        return contactId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    // Setters - Update Values (only for updatable fields) 
    // Each setter validates before updating. This centralizes validation logic.
    
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty() || firstName.length() > 10) {
            throw new IllegalArgumentException("First name cannot be null or empty and must be 10 characters or less.");
        }
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty() || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be null or empty and must be 10 characters or less.");
        }
        this.lastName = lastName;
    }
    
    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits and cannot be null.");
        }
        this.phone = phone;
    }
    
    public void setAddress(String address) {
        if (address == null || address.isEmpty() || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null or empty and must be 30 characters or less.");
        }
        this.address = address;
    }
}