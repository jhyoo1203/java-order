package order.global.mapper;

import order.domain.Menu;
import order.global.exception.ErrorMessage;
import order.global.exception.OrderException;
import order.global.validation.InputValidator;
import order.repository.MenuRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderMapper {

    private static final Pattern MENU_PATTERN = Pattern.compile("([^(]+)\\((\\d+)개\\)");
    private static final Pattern ORDER_SPLITTER = Pattern.compile("\\s*,\\s*");

    public static final int MENU_INDEX = 1;
    public static final int QUANTITY_INDEX = 2;

    private final MenuRepository menuRepository;

    public OrderMapper() {
        this.menuRepository = new MenuRepository();
    }

    public Map<Menu, Integer> getOrderMap(String input) {
        InputValidator.validateEmptyInput(input);
        return parseOrderItems(input.trim());
    }

    private Map<Menu, Integer> parseOrderItems(String input) {
        Map<Menu, Integer> orderMap = new LinkedHashMap<>();
        for (String item : ORDER_SPLITTER.split(input)) {
            addOrderItem(orderMap, item.trim());
        }
        return orderMap;
    }

    private void addOrderItem(Map<Menu, Integer> orderMap, String item) {
        Matcher matcher = MENU_PATTERN.matcher(item);
        validateOrderFormat(matcher);
        addToMap(orderMap, matcher);
    }

    private void validateOrderFormat(Matcher matcher) {
        if (!matcher.matches()) {
            throw OrderException.from(ErrorMessage.INVALID_ORDER_FORMAT);
        }
    }

    private void addToMap(Map<Menu, Integer> orderMap, Matcher matcher) {
        String menuName = matcher.group(MENU_INDEX).trim();
        Menu menu = menuRepository.findByName(menuName);
        int quantity = parseQuantity(matcher.group(QUANTITY_INDEX));
        orderMap.merge(menu, quantity, Integer::sum);
    }

    private int parseQuantity(String quantityStr) {
        try {
            return Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            throw OrderException.from(ErrorMessage.INVALID_ORDER_FORMAT);
        }
    }
}