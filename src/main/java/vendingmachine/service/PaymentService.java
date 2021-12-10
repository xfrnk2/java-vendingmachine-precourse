package vendingmachine.service;

import vendingmachine.payment.PaymentAmount;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class PaymentService {
	public static PaymentAmount initializePaymentAmount() {
		try {
			String input = InputView.getPaymentAmount();
			PaymentAmount paymentAmount = new PaymentAmount(input);
			OutputView.printNewLine();
			return paymentAmount;
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return initializePaymentAmount();
		}
	}
}
