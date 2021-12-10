package vendingmachine;

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
import vendingmachine.type.DelimiterType;
import vendingmachine.type.ErrorType;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {

	public void run() {

		Change change = initializeChange(); //refactor 필요 (input 순회 가능하도록)
		Map<Integer, Integer> changeAmount = initializeHoldingChange(change);
		OutputView.printHoldingChanges();
		getChangeStatus(changeAmount);
		Items items = initializeItems();

		PaymentAmount paymentAmount = initializePaymentAmount();
		buyItem(items.getItems(), paymentAmount);
		OutputView.printChangeResult();
		getFinalChangeStatus(changeAmount);
	}

	private Change initializeChange() {
			try {
				String input = InputView.getHoldingTotalChanges();
				return new Change(input);
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
				return initializeChange();
			}
	}

	private Map<Integer, Integer> initializeHoldingChange(Change change) {
			try {
				ChangeAmount changeAmount = new ChangeAmount(change.getChange());
				return changeAmount.getChangeAmount();
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
				return initializeHoldingChange(change);
			}
		}

	private Items initializeItems() {
			try {
				String input = InputView.getItemList();
				Items items = getItemsByInput(input);
				OutputView.printNewLine();
				return items;
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
				return initializeItems();
			}
		}

	private Items getItemsByInput(String input) {
		List<String> itemList = Splitter.on(DelimiterType.SEMICOLON.getDelimiter())
			.omitEmptyStrings().trimResults().splitToList(input);
		return new Items(itemList);
	}

	private PaymentAmount initializePaymentAmount() {
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

	private void buyItem(List<Item> items, PaymentAmount paymentAmount) {
		int leastCost = getLeastCostItem(items).getCost();
		while (paymentAmount.getPaymentAmount() >= leastCost && !isAllOutOfOrder(items)){
			try {
				OutputView.printRemainingPaymentAmount(paymentAmount.getPaymentAmount());
				String input = InputView.getItemNameToBuy();
				Item item = checkContainingItem(items, input);
				paymentAmount.payMoney(item.getCost());
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
			OutputView.printNewLine();
		}
		OutputView.printRemainingPaymentAmount(paymentAmount.getPaymentAmount());
	}

	private Item getLeastCostItem(List<Item> items){
		return items.stream()
			.min(Comparator.comparing(Item::getCost))
			.orElseThrow(IllegalArgumentException::new);
	}


	private Item checkContainingItem(List<Item> items, String name){
		if (items.stream().noneMatch(item -> name.equals(item.getName()))){
			throw new IllegalArgumentException(ErrorType.ERROR_INVALID_NAME.getError());
		}
		return items
			.stream()
			.filter(item -> name.equals(item.getName()))
			.findFirst()
			.get();
	}

	public boolean isAllOutOfOrder(List<Item> items) {
		return items.stream().allMatch(item -> item.getAmount() == 0);
	}

	private void getChangeStatus(Map<Integer, Integer> changeAmount){
		List<Integer> keys = new ArrayList<>(changeAmount.keySet());
		keys.sort(Collections.reverseOrder());

		for (Integer coin: keys){
			OutputView.printChangeStatus(coin, changeAmount.get(coin));
		}
		OutputView.printNewLine();
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
