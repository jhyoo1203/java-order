package order.repository;

import order.domain.Menu;
import order.global.exception.ErrorMessage;
import order.global.exception.OrderException;

import java.util.Arrays;

public class MenuRepository {

    public Menu findByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> OrderException.from(ErrorMessage.INVALID_ORDER_FORMAT));
    }
}
