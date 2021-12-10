package vendingmachine.change;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class ChangeService {
	public static ChangeAmount initializeChange() {
		try {
			String input = InputView.getHoldingTotalChanges();
			return initializeHoldingChange(new Change(input));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return initializeChange();
		}
	}

	public static ChangeAmount initializeHoldingChange(Change change) {
		ChangeAmount changeAmount = new ChangeAmount(change.getChange());
		showHoldingChanges(changeAmount.getChangeAmount());
		return changeAmount;
	}

	public static void showHoldingChanges(Map<Integer, Integer> changeAmount) {
		OutputView.printHoldingChanges();
		getChangeStatus(changeAmount);
	}

	private static void getChangeStatus(Map<Integer, Integer> changeAmount) {
		List<Integer> keys = new ArrayList<>(changeAmount.keySet());
		keys.sort(Collections.reverseOrder());

		for (Integer coin : keys) {
			OutputView.printChangeStatus(coin, changeAmount.get(coin));
		}
		OutputView.printNewLine();
	}

	public static void getFinalChangeStatus(Map<Integer, Integer> changeAmount) {
		List<Integer> keys = new ArrayList<>(changeAmount.keySet());
		keys.sort(Collections.reverseOrder());

		for (Integer coin : keys) {
			if (changeAmount.get(coin) == 0) {
				continue;
			}
			OutputView.printChangeStatus(coin, changeAmount.get(coin));
		}
		OutputView.printNewLine();
	}
}
