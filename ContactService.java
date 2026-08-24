 /**
 * Vincent Esposito
 * 7/16/2026
 * Southern New Hampshire University
 *  
 * Manages Contact objects in memory using a HashMap for fast lookup by unique contactId.
 * 
 */

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    
    // In-memory storage: key = contactId, value = Contact object
    private final Map<String, Contact> contacts = new HashMap<>();
    
    /**
     * Adds a new contact. 
     * Requirement: unique ID.
     * Throws IllegalArgumentException if contact is null, has no ID, or ID already exists.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        
        String id = contact.getContactId();
        if (id == null || contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID is invalid or already exists. IDs must be unique.");
        }
        
        contacts.put(id, contact);
    }
    
    /**
     * Deletes a contact by ID.
     * Throws IllegalArgumentException if ID is null or contact does not exist.
     */
    public void deleteContact(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID cannot be null and must exist to delete.");
        }
        contacts.remove(contactId);
    }
    
    /**
     * Updates contact fields for the given contactId.
     * Only fields that are non-null will be updated (supports partial updates).
     * Validation is handled by the Contact setters (which throw IllegalArgumentException on bad data).
     * 
     * Throws IllegalArgumentException if contactId does not exist.
     */
    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact contact = contacts.get(contactId);
        
        if (contact == null) {
            throw new IllegalArgumentException("Contact with ID " + contactId + " not found. Cannot update.");
        }
        
        // Only update fields that are provided (not null). This allows flexible partial updates.
        if (firstName != null) {
            contact.setFirstName(firstName);
        }
        if (lastName != null) {
            contact.setLastName(lastName);
        }
        if (phone != null) {
            contact.setPhone(phone);
        }
        if (address != null) {
            contact.setAddress(address);
        }
    }
    
    /**
     * Helper method to retrieve a contact by ID (useful for testing and verification).
     * Returns null if not found.
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
    
    /**
     * Returns the number of contacts currently stored (useful for tests).
     */
    public int getContactCount() {
        return contacts.size();
    }
}