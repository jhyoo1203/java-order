package order.controller;

import order.domain.Order;
import order.service.OrderService;
import order.view.InputView;
import order.view.OutputView;

public class OrderController {

    private final OrderService orderService;
    private final InputView inputView;
    private final OutputView outputView;

    public OrderController() {
        this.orderService = new OrderService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        try {
            processOrder();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void processOrder() {
        String orderInput = inputView.readOrder();
        Order order = orderService.createOrder(orderInput);

        outputView.printOrderDetails(
                order.orderItems(),
                order.totalAmount(),
                order.deliveryFee()
        );

        outputView.printServiceDetails(order.serviceDumplingCount());
        outputView.printFinalAmount(order.getFinalAmount());
    }
}
