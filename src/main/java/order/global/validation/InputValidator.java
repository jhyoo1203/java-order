package order.global.validation;

import order.global.exception.ErrorMessage;
import order.global.exception.OrderException;

public class InputValidator {

    public static void validateEmptyInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw OrderException.from(ErrorMessage.EMPTY_INPUT);
        }
    }
}
