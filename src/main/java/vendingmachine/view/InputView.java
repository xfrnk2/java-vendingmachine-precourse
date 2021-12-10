package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	public static final String MACHINE_HOLDING_MONEY_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
	public static final String ITEM_LIST_MESSAGE = "상품명과 가격, 수량을 입력해 주세요.";
	public static final String PAYMENT_AMOUNT_MESSAGE = "투입 금액을 입력해 주세요.";
	public static final String ITEM_NAME_TO_BUY_MESSAGE = "구매할 상품명을 입력해 주세요.";


	public static String getHoldingTotalChanges() {
		System.out.println(MACHINE_HOLDING_MONEY_MESSAGE);
		return Console.readLine();
	}

	public static String getItemList() {
		System.out.println(ITEM_LIST_MESSAGE);
		return Console.readLine();
	}

	public static String getPaymentAmount() {
		System.out.println(PAYMENT_AMOUNT_MESSAGE);
		return Console.readLine();
	}


	public static String getItemNameToBuy() {
		System.out.printf(ITEM_NAME_TO_BUY_MESSAGE + "%n");
		return Console.readLine();
	}

}
