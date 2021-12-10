package vendingmachine.change;

import vendingmachine.Money;

public class Change implements Money {
	private final int money;

	public Change(String money) {
		this.money = convertToNumber(money);
		validate(this.money);

	}

	public int getChange(){
		return money;
	}
}
