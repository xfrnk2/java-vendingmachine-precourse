package vendingmachine.view;

import vendingmachine.type.TextType;

public class OutputView {
	public static void printError(String error){
		System.out.println(TextType.ERROR_PREFIX.getError() + error);
	}
}
