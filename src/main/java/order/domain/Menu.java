package order.domain;

public enum Menu {

    PIZZA("피자", 25_000, Category.MAIN),
    HAMBURGER("햄버거", 10_000, Category.MAIN),
    CHICKEN("치킨", 23_000, Category.MAIN),

    FRENCH_FRIES("감자튀김", 5_000, Category.SIDE),
    CHEESE_STICK("치즈스틱", 7_000, Category.SIDE),
    SALAD("샐러드", 8_000, Category.SIDE),

    COLA("콜라", 2_000, Category.DRINK),
    ZERO_COLA("제로 콜라", 2_500, Category.DRINK),
    ORANGE_JUICE("오렌지 주스", 3_000, Category.DRINK),

    SERVICE_DUMPLING("서비스 만두", 0, Category.SERVICE);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
