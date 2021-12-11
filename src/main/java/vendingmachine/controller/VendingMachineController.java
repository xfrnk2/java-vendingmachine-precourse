package vendingmachine.controller;

import vendingmachine.domain.Coins;
import vendingmachine.domain.Item;
import vendingmachine.domain.Items;
import vendingmachine.domain.Money;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
	private static final InputView inputView = new InputView();
	private static final CoinController coinController = new CoinController(inputView);
	private static final ItemController itemcontroller = new ItemController(inputView);

	public void run() {

		Coins coins = coinController.giveHoldingCoins();
		OutputView.printHoldingCoinStatus(coins.toString());

		Items items = itemcontroller.giveItems();
		Money money = giveMoney();
		perChaseItem(items, money);

	}

	private Money giveMoney() {
		try {
			OutputView.printInsertingMoneyRequest();
			return new Money(inputView.scanPrice());
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return giveMoney();
		}
	}

	private void perChaseItem(Items items, Money money) {
		int leastItemCost = itemcontroller.getLeastItemCost(items);
		while (leastItemCost <= money.getRemainingMoney() && !items.checkAllOutOfOrder()) {
			try {
				System.out.println(money.toString());
				OutputView.printItemPerChaseRequest();
				Item item = items.findItemByName(inputView.scanItemName());
				money.pay(item.getCost());
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}
	}
}