package order.domain;

import java.util.Map;

public record Order(
        Map<Menu, Integer> orderItems,
        int totalAmount,
        int deliveryFee,
        int serviceDumplingCount
) {
    public int getFinalAmount() {
        return totalAmount + deliveryFee;
    }
}