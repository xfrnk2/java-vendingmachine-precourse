package vendingmachine.paymentAmount;

import vendingmachine.type.ErrorType;

public class PaymentAmount {
	private int money;

	public PaymentAmount(String money) {
		this.money = convertToNumber(money);
		validate(this.money);

	}

	private int convertToNumber(String money) {
		try {
			return Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format(ErrorType.ERROR_IS_NOT_NUMBER.getError(), "투입 금액"));
		}
	}

	private void validate(int money){
		if (money < 0){
			throw new IllegalArgumentException(ErrorType.ERROR_IS_NEGATIVE.getError());
		}
	}

	public int getPaymentAmount(){
		return money;
	}

	public void payMoney(int payedAmount) {
		this.money -= payedAmount;
	}
}
