package vendingmachine.paymentAmount;

import vendingmachine.Money;

public class PaymentAmount implements Money {
	private int money;

	public PaymentAmount(String money) {
		this.money = convertToNumber(money);
		validate(this.money);

	}

	public int getPaymentAmount(){
		return money;
	}

	public void payMoney(int payedAmount) {
		this.money -= payedAmount;
	}
}
