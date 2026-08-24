/**
 * 
 * Vincent Esposito
 * 7/16/2026
 * Southern New Hampshire University 
 * 
 * JUnit 5 unit tests for the Contact class.
 * These tests are designed to "uncover errors" by testing both happy paths and error conditions
 * (null values, lengths too long, phone not exactly 10 digits, etc.).
 * 
 * Uses assertThrows to verify that IllegalArgumentException is thrown for invalid inputs.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class ContactTest {
    
    // Validation Contact Tests
    
    @Test
    @DisplayName("Valid contact creation should succeed")
    void testValidContactCreation() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main Street");
        
        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }
    
    @Test
    @DisplayName("Contact ID at max length (10 chars) is valid")
    void testContactIdMaxLength() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals(10, contact.getContactId().length());
    }
    
    // Contact ID invalid Tests
    
    @Test
    @DisplayName("Null contact ID should throw IllegalArgumentException")
    void testNullContactId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    @DisplayName("Contact ID longer than 10 characters should throw IllegalArgumentException")
    void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }
    
    // First name invalid tests
    
    @Test
    @DisplayName("Null first name should throw IllegalArgumentException")
    void testNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", null, "Doe", "1234567890", "123 Main St");
        });
    }
    
    @Test
    @DisplayName("First name longer than 10 characters should throw IllegalArgumentException")
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "VeryLongName", "Doe", "1234567890", "123 Main St");
        });
    }
    
    // Last name invalid tests
    
    @Test
    @DisplayName("Null last name should throw IllegalArgumentException")
    void testNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", null, "1234567890", "123 Main St");
        });
    }
    
    @Test
    @DisplayName("Last name longer than 10 characters should throw IllegalArgumentException")
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "VeryLongLastName", "1234567890", "123 Main St");
        });
    }
    
    // Phone Num invalid tests
    
    @Test
    @DisplayName("Null phone should throw IllegalArgumentException")
    void testNullPhone() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", null, "123 Main St");
        });
    }
    
    @Test
    @DisplayName("Phone not exactly 10 digits should throw IllegalArgumentException")
    void testPhoneNotExactlyTenDigits() {
        // Too short
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "12345", "123 Main St");
        });
        
        // Too long
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "12345678901", "123 Main St");
        });
        
        // Contains non-digits
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "123456789A", "123 Main St");
        });
    }
    
    // Address invalid tests
    
    @Test
    @DisplayName("Null address should throw IllegalArgumentException")
    void testNullAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890", null);
        });
    }
    
    @Test
    @DisplayName("Address longer than 30 characters should throw IllegalArgumentException")
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1234567890", "John", "Doe", "1234567890", 
                "This address is way too long and exceeds the thirty character limit easily");
        });
    }
    
    // Setter tests
    
    @Test
    @DisplayName("Valid setter updates should work")
    void testValidSetters() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        
        contact.setFirstName("Jane");
        contact.setLastName("Smith");
        contact.setPhone("9876543210");
        contact.setAddress("456 Oak Avenue");
        
        assertEquals("Jane", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("9876543210", contact.getPhone());
        assertEquals("456 Oak Avenue", contact.getAddress());
    }
    
    @Test
    @DisplayName("Invalid setter values should throw IllegalArgumentException")
    void testInvalidSetters() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("TooLongFirstName"));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("TooLongLastName"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("This address string is definitely over thirty characters long"));
    }
    
    @Test
    @DisplayName("Contact ID should not be updatable (no setter exists)")
    void testContactIdNotUpdatable() {
        // Since there is no setContactId method and field is final, it cannot be changed after construction.
        // This test documents that requirement. We simply verify the ID remains as set.
        Contact contact = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("ABC123", contact.getContactId());
        // No way to call a setter that doesn't exist - this satisfies "shall not be updatable"
    }
}