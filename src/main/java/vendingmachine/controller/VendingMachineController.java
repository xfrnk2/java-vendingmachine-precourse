package vendingmachine.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
	private static final InputView inputView = new InputView();
	private static final OutputView outputView = new OutputView();
	private static final CoinController coinController = new CoinController(inputView, outputView);
	private static final ItemController itemcontroller = new ItemController(inputView, outputView);
	private static final MoneyController moneyController = new MoneyController(inputView, outputView);

	public void run() {

		coinController.setupHoldingCoins();
		itemcontroller.setupItems();
		moneyController.setupMoney();
		sell();

		outputView.printMoney(moneyController.getCurrentMoney());
		Map<Coin, Integer> changes = getChanges();
		coinController.reduceCoins(changes);
		outputView.printChanges(changes);

	}

	private void sell() {
		while (moneyController.getCurrentMoney() >= itemcontroller.getLeastItemCost()
			&& !itemcontroller.checkAllOutOfOrder()) {
			try {
				outputView.printMoney(moneyController.getCurrentMoney());
				outputView.printBuyItemRequest();
				int targetItemCost = itemcontroller.sellItem(moneyController.getCurrentMoney());
				moneyController.reduceMoney(targetItemCost);

			} catch (IllegalArgumentException e) {
				outputView.printError(e.getMessage());
			}
		}
	}

	public Map<Coin, Integer> getChanges() {
		Map<Coin, Integer> changes = new LinkedHashMap<>();
		Map<Coin, Integer> restCoins = coinController.getRestCoins();
		for (Map.Entry<Coin, Integer> coin : restCoins.entrySet()) {
			final int number = coinController.getAvailableChangeNumber(coin.getKey().getAmount(), coin.getValue(),
				moneyController.getCurrentMoney());
			final boolean isUpperThanZero = 0 < number;
			if (isUpperThanZero) {
				changes.put(coin.getKey(), number);
				moneyController.reduceMoney(coin.getKey().getAmount() * number);
			}
		}
		return changes;
	}
}
