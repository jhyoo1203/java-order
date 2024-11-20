package order.view;

import order.view.messages.InputMessages;

import java.util.Scanner;

public class InputView {

    public String readOrder() {
        Scanner sc = new Scanner(System.in);
        System.out.println(InputMessages.ASK_MENU_AND_QUANTITY);
        return sc.nextLine();
    }
}
