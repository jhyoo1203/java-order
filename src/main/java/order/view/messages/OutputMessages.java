package order.view.messages;

public class OutputMessages {

    public static final String ORDER_DETAILS_PREFIX = System.lineSeparator() + "[주문 내역]";
    public static final String ORDER_DETAILS_FORMAT = "%s(%d개): %,d원";
    public static final String TOTAL_ORDER_AMOUNT_FORMAT = "총 주문 금액: %,d원%n";
    public static final String DELIVERY_FEE_FORMAT = "배달비: %,d원%n";
    public static final String SERVICE_DETAILS_PREFIX = System.lineSeparator() + "[서비스]";
    public static final String SERVICE_DETAILS_FORMAT = "서비스 만두: (%d개)";
    public static final String FINAL_PAYMENT_AMOUNT_PREFIX = System.lineSeparator() + "[최종 결제 금액]";
    public static final String FINAL_PAYMENT_AMOUNT_FORMAT = "%,d원";
}
