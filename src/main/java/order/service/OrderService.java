package order.service;

import order.domain.Category;
import order.domain.Menu;
import order.domain.Order;
import order.global.exception.ErrorMessage;
import order.global.exception.OrderException;
import order.global.mapper.OrderMapper;

import java.util.Map;

public class OrderService {

    private static final int MINIMUM_ORDER_AMOUNT = 30_000;
    private static final int DELIVERY_FEE_THRESHOLD_FIRST = 50_000;
    private static final int DELIVERY_FEE_THRESHOLD_SECOND = 100_000;
    private static final int DELIVERY_FEE_FIRST = 2_000;
    private static final int DELIVERY_FEE_SECOND = 1_000;
    private static final int NO_DELIVERY_FEE = 0;

    private final OrderMapper orderMapper;

    public OrderService() {
        this.orderMapper = new OrderMapper();
    }

    public Order createOrder(String orderInput) {
        Map<Menu, Integer> orderMap = orderMapper.getOrderMap(orderInput);
        validateOrder(orderMap);

        int totalAmount = calculateTotalAmount(orderMap);
        int deliveryFee = calculateDeliveryFee(totalAmount);
        int serviceDumplingCount = calculateServiceDumplings(orderMap);

        return new Order(orderMap, totalAmount, deliveryFee, serviceDumplingCount);
    }

    private void validateOrder(Map<Menu, Integer> orderItems) {
        validateMinimumOrderAmount(orderItems);
        validateNotOnlyDrinks(orderItems);
    }

    private void validateMinimumOrderAmount(Map<Menu, Integer> orderItems) {
        int totalAmount = calculateTotalAmount(orderItems);
        if (totalAmount < MINIMUM_ORDER_AMOUNT) {
            throw OrderException.from(ErrorMessage.NOT_ENOUGH_MINIMUM_ORDER_AMOUNT);
        }
    }

    private void validateNotOnlyDrinks(Map<Menu, Integer> orderItems) {
        boolean hasDrinks = orderItems.keySet().stream()
                .allMatch(menu -> menu.getCategory() == Category.DRINK);

        if (hasDrinks) {
            throw OrderException.from(ErrorMessage.ONLY_DRINKS);
        }
    }

    private int calculateTotalAmount(Map<Menu, Integer> orderItems) {
        return orderItems.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private int calculateDeliveryFee(int totalAmount) {
        if (totalAmount >= DELIVERY_FEE_THRESHOLD_SECOND) {
            return NO_DELIVERY_FEE;
        }
        if (totalAmount >= DELIVERY_FEE_THRESHOLD_FIRST) {
            return DELIVERY_FEE_SECOND;
        }
        return DELIVERY_FEE_FIRST;
    }

    private int calculateServiceDumplings(Map<Menu, Integer> orderItems) {
        return orderItems.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == Category.MAIN)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
