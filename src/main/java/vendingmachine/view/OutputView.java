package vendingmachine.view;

import vendingmachine.type.TextType;

public class OutputView {
	public static void printError(String error, Object ...args){
		System.out.printf(TextType.ERROR_PREFIX.getError() + error, args);
	}
}
