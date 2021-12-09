package vendingmachine.change;

import vendingmachine.type.TextType;

public class Change {
	private final int money;

	public Change(String money) {
		this.money = isNumber(money);
	}

	private int isNumber(String money) {
		try {
			return Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(TextType.ERROR_IS_NOT_NUMBER.getError());
		}
	}

	public int getChange(){
		return money;
	}
}
