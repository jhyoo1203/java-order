package order.view;

import order.domain.Menu;
import order.view.messages.OutputMessages;

import java.util.Map;

public class OutputView {

    private static final int HAS_SERVICE_DUMPLINGS = 0;

    public void printOrderDetails(Map<Menu, Integer> orderItems, int totalAmount, int deliveryFee) {
        System.out.println(OutputMessages.ORDER_DETAILS_PREFIX);
        orderItems.forEach((menu, quantity) ->
                System.out.printf(OutputMessages.ORDER_DETAILS_FORMAT + System.lineSeparator(),
                        menu.getName(), quantity, menu.getPrice() * quantity));
        System.out.printf(OutputMessages.TOTAL_ORDER_AMOUNT_FORMAT, totalAmount);
        System.out.printf(OutputMessages.DELIVERY_FEE_FORMAT, deliveryFee);
    }

    public void printServiceDetails(int serviceDumplingCount) {
        if (serviceDumplingCount > HAS_SERVICE_DUMPLINGS) {
            System.out.println(OutputMessages.SERVICE_DETAILS_PREFIX);
            System.out.printf(OutputMessages.SERVICE_DETAILS_FORMAT + System.lineSeparator(), serviceDumplingCount);
        }
    }

    public void printFinalAmount(int finalAmount) {
        System.out.println(OutputMessages.FINAL_PAYMENT_AMOUNT_PREFIX);
        System.out.printf(OutputMessages.FINAL_PAYMENT_AMOUNT_FORMAT + System.lineSeparator(), finalAmount);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
