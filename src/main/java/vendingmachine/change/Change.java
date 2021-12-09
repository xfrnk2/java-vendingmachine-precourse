package vendingmachine.change;

import vendingmachine.type.TextType;

public class Change {
	private final int money;

	public Change(String money) {
		this.money = convertToNumber(money);
		validate(this.money);

	}

	private int convertToNumber(String money) {
		try {
			return Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format(TextType.ERROR_IS_NOT_NUMBER.getError(), "자판기 보유 금액"));
		}
	}

	private void validate(int money){
		if (money < 0){
			throw new IllegalArgumentException(TextType.ERROR_IS_NEGATIVE.getError());
		}
	}

	public int getChange(){
		return money;
	}
}
