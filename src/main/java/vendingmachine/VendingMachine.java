package vendingmachine;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.base.Splitter;

import vendingmachine.change.Change;
import vendingmachine.change.ChangeAmount;
import vendingmachine.item.Item;
import vendingmachine.item.Items;
import vendingmachine.paymentAmount.PaymentAmount;
import vendingmachine.type.DelimiterType;
import vendingmachine.type.TextType;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {

	public void run() {

		Change change = initializeChange(); //refactor 필요 (input 순회 가능하도록)
		Map<Integer, Integer> changeAmount = initializeHoldingChange(change);
		Items items = initializeItems();
		PaymentAmount paymentAmount = initializePaymentAmount();

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
				String input = InputView.getHoldingTotalChanges();
				items = getItemsByInput(input);
				break;
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}
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
				System.out.println(item.getName());
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}
	}

	private Item getLeastCostItem(List<Item> items){
		return items.stream()
			.min(Comparator.comparing(Item::getCost))
			.orElseThrow(IllegalArgumentException::new);
	}


	private Item checkContainingItem(List<Item> items, String name){
		if (items.stream().noneMatch(item -> name.equals(item.getName()))){
			throw new IllegalArgumentException(TextType.ERROR_INVALID_NAME.getError());
		}
		return items.stream().filter(item -> name.equals(item.getName())).findFirst().get();
	}

	public boolean isAllOutOfOrder(List<Item> items) {
		return items.stream().allMatch(item -> item.getAmount() == 0);
	}


}
