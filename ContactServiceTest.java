/**
 * 
 * Vincent Esposito
 * 7/16/2026
 * Southern New Hampshire University 
 * * 
 * JUnit 5 unit tests for ContactService.
 * Tests cover:
 * - Adding contacts (valid, duplicate ID, null)
 * - Deleting contacts (valid, non-existent)
 * - Updating contacts (partial updates, invalid data, non-existent contact)
 * 
 * These tests uncover errors by verifying exceptions are thrown for bad operations
 * and that valid operations produce expected results.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class ContactServiceTest {
    
    private ContactService service;
    private Contact validContact;
    
    @BeforeEach
    void setUp() {
        service = new ContactService();
        validContact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main Street");
    }
    
    // ==================== ADD CONTACT TESTS ====================
    
    @Test
    @DisplayName("Adding a valid contact should succeed and increase count")
    void testAddValidContact() {
        service.addContact(validContact);
        
        assertEquals(1, service.getContactCount());
        assertNotNull(service.getContact("1234567890"));
        assertEquals("John", service.getContact("1234567890").getFirstName());
    }
    
    @Test
    @DisplayName("Adding contact with duplicate ID should throw IllegalArgumentException")
    void testAddDuplicateContactId() {
        service.addContact(validContact);
        
        Contact duplicate = new Contact("1234567890", "Jane", "Smith", "9876543210", "456 Oak Ave");
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(duplicate);
        });
        
        // Original contact should still be there, unchanged
        assertEquals(1, service.getContactCount());
        assertEquals("John", service.getContact("1234567890").getFirstName());
    }
    
    @Test
    @DisplayName("Adding null contact should throw IllegalArgumentException")
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
    }
    
    // ==================== DELETE CONTACT TESTS ====================
    
    @Test
    @DisplayName("Deleting an existing contact should succeed and decrease count")
    void testDeleteExistingContact() {
        service.addContact(validContact);
        assertEquals(1, service.getContactCount());
        
        service.deleteContact("1234567890");
        
        assertEquals(0, service.getContactCount());
        assertNull(service.getContact("1234567890"));
    }
    
    @Test
    @DisplayName("Deleting non-existent contact ID should throw IllegalArgumentException")
    void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("9999999999");
        });
    }
    
    @Test
    @DisplayName("Deleting with null ID should throw IllegalArgumentException")
    void testDeleteNullContactId() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact(null);
        });
    }
    
    // ==================== UPDATE CONTACT TESTS ====================
    
    @Test
    @DisplayName("Updating all fields of existing contact should succeed")
    void testUpdateAllFields() {
        service.addContact(validContact);
        
        service.updateContact("1234567890", "Jane", "Smith", "9876543210", "456 Oak Avenue");
        
        Contact updated = service.getContact("1234567890");
        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("9876543210", updated.getPhone());
        assertEquals("456 Oak Avenue", updated.getAddress());
    }
    
    @Test
    @DisplayName("Partial update (only firstName) should work by passing null for others")
    void testPartialUpdate() {
        service.addContact(validContact);
        
        // Only update first name
        service.updateContact("1234567890", "UpdatedFirst", null, null, null);
        
        Contact updated = service.getContact("1234567890");
        assertEquals("UpdatedFirst", updated.getFirstName());
        // Other fields unchanged
        assertEquals("Doe", updated.getLastName());
        assertEquals("1234567890", updated.getPhone());
    }
    
    @Test
    @DisplayName("Updating non-existent contact should throw IllegalArgumentException")
    void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("9999999999", "New", "Name", "1234567890", "New Address");
        });
    }
    
    @Test
    @DisplayName("Updating with invalid data (e.g. too long name) should throw from setter")
    void testUpdateWithInvalidData() {
        service.addContact(validContact);
        
        // This should throw because "ThisNameIsWayTooLongForTheField" > 10 chars
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("1234567890", "ThisNameIsWayTooLongForTheField", null, null, null);
        });
        
        // Original data should remain unchanged
        assertEquals("John", service.getContact("1234567890").getFirstName());
    }
    
    @Test
    @DisplayName("Updating phone to invalid format should throw")
    void testUpdateInvalidPhone() {
        service.addContact(validContact);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("1234567890", null, null, "12345", null);  // too short
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("1234567890", null, null, "12345678901", null);  // too long
        });
    }
}