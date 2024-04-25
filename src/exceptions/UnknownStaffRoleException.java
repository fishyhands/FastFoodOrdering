package exceptions;

/**
 * The UnknownStaffRoleException class represents an exception that is thrown
 * when a staff role is not found in the system.
 */
public class UnknownStaffRoleException extends Exception {

    /**
     * Constructs a new UnknownStaffRoleException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public UnknownStaffRoleException(String message) {
        super(message);
    }
}