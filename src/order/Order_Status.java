package order;

/**
 * The Order_Status enum represents the status of an order.
 * It includes four possible states: PENDING, PREPARING_NOW, READY, and COMPLETE.
 */
public enum Order_Status {
    /** The order is pending and has not yet been processed. */
    PENDING,
    /** The order is currently being prepared. */
    PREPARING_NOW,
    /** The order is ready for collection. */
    READY,
    /** The order is complete and has been collected. */
    COMPLETE
}
