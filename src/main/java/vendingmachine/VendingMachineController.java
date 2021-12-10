package vendingmachine;

import vendingmachine.change.ChangeAmount;
import vendingmachine.item.Item;
import vendingmachine.item.Items;
import vendingmachine.payment.PaymentAmount;
import vendingmachine.change.ChangeService;
import vendingmachine.item.ItemsService;
import vendingmachine.payment.PaymentService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

	public void run() {
		ChangeAmount changeAmount = ChangeService.initializeChange();
		Items items = ItemsService.initializeItems();
		PaymentAmount paymentAmount = PaymentService.initializePaymentAmount();

		buyItem(items, paymentAmount);
		OutputView.printChangeResult();
		ChangeService.showFinalChangeStatus(changeAmount.getChangeAmount());
	}

	private void buyItem(Items items, PaymentAmount paymentAmount) {
		int leastCost = items.findLeastCostItem().getCost();
		while (paymentAmount.getPaymentAmount() >= leastCost && !items.isAllOutOfOrder()) {
			try {
				OutputView.printRemainingPaymentAmount(paymentAmount.getPaymentAmount());
				String input = InputView.getItemNameToBuy();
				Item item = items.findItem(input);
				paymentAmount.payMoney(item.getCost());
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
			OutputView.printNewLine();
		}
		OutputView.printRemainingPaymentAmount(paymentAmount.getPaymentAmount());
	}
}
