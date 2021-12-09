package vendingmachine.view;

import vendingmachine.type.TextType;

public class OutputView {
	private static final String REMAINING_PAYMENT_AMOUNT = "투입 금액 : %s원%n";


	public static void printError(String error, Object ...args){
		System.out.printf(TextType.ERROR_PREFIX.getError() + error, args);
	}

	public static void printRemainingPaymentAmount(int amount){
		System.out.printf(REMAINING_PAYMENT_AMOUNT, amount);
	}
}
