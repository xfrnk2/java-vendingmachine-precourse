package vendingmachine;

import static vendingmachine.service.ChangeService.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;

import vendingmachine.change.Change;
import vendingmachine.change.ChangeAmount;
import vendingmachine.item.Item;
import vendingmachine.item.Items;
import vendingmachine.payment.PaymentAmount;
import vendingmachine.service.ChangeService;
import vendingmachine.service.ItemsService;
import vendingmachine.service.PaymentService;
import vendingmachine.type.DelimiterType;
import vendingmachine.type.ErrorType;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

	public void run() {

		Map<Integer, Integer> changeAmount = ChangeService.initializeHoldingChange();
		showHoldingChanges(changeAmount);

		Items items = ItemsService.initializeItems();
		PaymentAmount paymentAmount = PaymentService.initializePaymentAmount();

		buyItem(items.getItems(), paymentAmount);
		OutputView.printChangeResult();
		getFinalChangeStatus(changeAmount);
	}





	private void buyItem(List<Item> items, PaymentAmount paymentAmount) {
		int leastCost = ItemsService.getLeastCostItem(items).getCost();
		while (paymentAmount.getPaymentAmount() >= leastCost && !ItemsService.isAllOutOfOrder(items)){
			try {
				OutputView.printRemainingPaymentAmount(paymentAmount.getPaymentAmount());
				String input = InputView.getItemNameToBuy();
				Item item = ItemsService.checkContainingItem(items, input);
				paymentAmount.payMoney(item.getCost());
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
			OutputView.printNewLine();
		}
		OutputView.printRemainingPaymentAmount(paymentAmount.getPaymentAmount());
	}



	private void getFinalChangeStatus(Map<Integer, Integer> changeAmount){
		List<Integer> keys = new ArrayList<>(changeAmount.keySet());
		keys.sort(Collections.reverseOrder());

		for (Integer coin: keys){
			if(changeAmount.get(coin) == 0){
				continue;
			}
			OutputView.printChangeStatus(coin, changeAmount.get(coin));
		}
		OutputView.printNewLine();
	}


}
