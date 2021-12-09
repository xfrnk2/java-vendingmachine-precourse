package vendingmachine.view;

import vendingmachine.type.TextType;

public class OutputView {
	private static final String REMAINING_PAYMENT_AMOUNT = "투입 금액 : %s원%n";
	private static final String CHANGE = "잔돈";
	private static final String CHANGE_COIN_QUANTITY = "%s - %s개%n";


	public static void printError(String error, Object ...args){
		System.out.printf(TextType.ERROR_PREFIX.getError() + error, args);
	}

	public static void printRemainingPaymentAmount(int amount){
		System.out.printf(REMAINING_PAYMENT_AMOUNT, amount);
	}

	public static void printChangeStatus(int coin, int amount){
		System.out.printf(CHANGE_COIN_QUANTITY, coin, amount);
	}

	public static void printChangeResult(){
		System.out.println(CHANGE);
	}
}
