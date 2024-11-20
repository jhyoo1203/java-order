package order.global.exception;

public enum ErrorMessage {

    INVALID_ORDER_FORMAT("주문 형식이 잘못되었습니다."),
    NOT_ENOUGH_MINIMUM_ORDER_AMOUNT("최소 주문 금액을 만족하지 못했습니다."),
    EMPTY_INPUT("주문 내역이 비어있습니다."),
    INPUT_NEGATIVE_NUMBER("주문 수량은 1개 이상이어야 합니다."),
    ONLY_DRINKS("음료만으로는 주문할 수 없습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
