package vendingmachine.change;

import vendingmachine.MoneyInterface;

public class Change implements MoneyInterface {
	private final int money;

	public Change(String money) {
		this.money = convertToNumber(money);
		validate(this.money);

	}

	public int getChange(){
		return money;
	}
}
