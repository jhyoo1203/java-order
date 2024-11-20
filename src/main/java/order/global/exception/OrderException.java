package order.global.exception;

public class OrderException extends IllegalArgumentException {

    private OrderException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static OrderException from(ErrorMessage errorMessage) {
        return new OrderException(errorMessage);
    }
}
