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
	private final OutputView outputView;
	private Coins coins;

	public CoinController(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void setupHoldingCoins() {
		this.coins = initializeCoins();
		outputView.printHoldingCoinStatus(coins.findAll());
	}

	public Coins initializeCoins() {
		try {
			outputView.printHoldingCashRequest();
			int price = inputView.scanPrice();
			return new Coins(makeCoins(price));
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
			return initializeCoins();
		}
	}

	private Map<Integer, Integer> makeCoins(int holdingAmount) {
		Map<Integer, Integer> coinAmount = new LinkedHashMap<>();
		for (Coin coin : Coin.values()) {
			int amount = coin.getAmount();
			coinAmount.put(amount, holdingAmount / amount);
			holdingAmount %= amount;
		}
		return coinAmount;
	}

	public Map<Integer, Integer> getChanges(final Coins coins, final Money money) {
		Map<Integer, Integer> changes = money.makeChanges(coins);
		coins.update(changes);
		return changes;
	}
}
