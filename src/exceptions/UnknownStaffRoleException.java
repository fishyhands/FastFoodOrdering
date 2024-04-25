package exceptions;

//a new exception for if staff role is not found in system. 
public class UnknownStaffRoleException extends Exception {
    public UnknownStaffRoleException(String message) {
        super(message);
    }
}
