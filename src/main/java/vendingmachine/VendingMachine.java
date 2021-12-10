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
import vendingmachine.paymentAmount.PaymentAmount;
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
		Change change;
		while (true) {
			try {
				String input = InputView.getHoldingTotalChanges();
				change = new Change(input);
				break;
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}

		return change;

	}

	private Map<Integer, Integer> initializeHoldingChange(Change change) {
		Map<Integer, Integer> machineHoldingChange;
		while (true) {
			try {
				ChangeAmount changeAmount = new ChangeAmount(change.getChange());
				machineHoldingChange = changeAmount.getChangeAmount();
				break;
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}
		return machineHoldingChange;
	}

	private Items initializeItems() {
		Items items;
		while (true) {
			try {
				String input = InputView.getItemList();
				items = getItemsByInput(input);
				break;
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}
		OutputView.printNewLine();
		return items;
	}

	private Items getItemsByInput(String input) {
		List<String> itemList = Splitter.on(DelimiterType.SEMICOLON.getDelimiter())
			.omitEmptyStrings().trimResults().splitToList(input);
		return new Items(itemList);
	}

	private PaymentAmount initializePaymentAmount() {
		PaymentAmount paymentAmount;
		while (true) {
			try {
				String input = InputView.getPaymentAmount();
				paymentAmount = new PaymentAmount(input);
				break;
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}
		OutputView.printNewLine();
		return paymentAmount;
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
		return items.stream().filter(item -> name.equals(item.getName())).findFirst().get();
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
