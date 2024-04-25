package payment;

/**
 * The PaymentMethod interface represents a generic payment method.
 */
public interface PaymentMethod {

    /**
     * Processes the payment using the specific payment method.
     */
    void process();

    /**
     * Retrieves the name of the payment method.
     *
     * @return The name of the payment method.
     */
    String getName();
}
