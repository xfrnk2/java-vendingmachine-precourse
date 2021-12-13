package vendingmachine.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Coins;
import vendingmachine.domain.Money;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class CoinController {
	private final InputView inputView;

	public CoinController(final InputView inputView) {
		this.inputView = inputView;
	}

	public Coins giveHoldingCoins() {
		Coins coins = initializeCoins();
		OutputView.printHoldingCoinStatus(coins);
		return coins;
	}

	public Coins initializeCoins() {
		try {
			OutputView.printHoldingCashRequest();
			int price = inputView.scanPrice();
			return new Coins(makeCoins(price));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return initializeCoins();
		}
	}

	private Map<Integer, Integer> makeCoins(int price) {
		Map<Integer, Integer> coinAmount = new LinkedHashMap<>();
		for (Coin coin : Coin.values()) {
			int amount = coin.getAmount();
			coinAmount.put(amount, price / amount);
			price %= amount;
		}
		return coinAmount;
	}

	public Map<Integer, Integer> getChanges(final Coins coins, final Money money) {
		Map<Integer, Integer> changes = money.makeChanges(coins);
		coins.update(changes);
		return changes;
	}
}
