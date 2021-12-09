package vendingmachine.change;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChangeAmount {
	private final Map<Integer, Integer> changeAmount;

	public ChangeAmount(int money) {
		this.changeAmount = initialize(money);
	}

	private Map<Integer, Integer> initialize(int money) {
		HashMap<Integer, Integer> coinMap = new HashMap<>();
		for (Coin coin : Coin.values()) {
			coinMap.put(coin.getAmount(), money / coin.getAmount());
			money = money % coin.getAmount();
		}
		return coinMap;
	}

	public Map<Integer, Integer> getChangeAmount() {
		return Collections.unmodifiableMap(changeAmount);
	}

}
