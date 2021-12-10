package vendingmachine.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import vendingmachine.change.Change;
import vendingmachine.change.ChangeAmount;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class ChangeService {
	public static Change initializeChange() {
		try {
			String input = InputView.getHoldingTotalChanges();
			return new Change(input);
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return initializeChange();
		}
	}

	public static Map<Integer, Integer> initializeHoldingChange(Change change) {
		try {
			ChangeAmount changeAmount = new ChangeAmount(change.getChange());
			return changeAmount.getChangeAmount();
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return initializeHoldingChange(change);
		}
	}

	public static void showHoldingChanges(Map<Integer, Integer> changeAmount){
		OutputView.printHoldingChanges();
		getChangeStatus(changeAmount);
	}

	private static void getChangeStatus(Map<Integer, Integer> changeAmount){
		List<Integer> keys = new ArrayList<>(changeAmount.keySet());
		keys.sort(Collections.reverseOrder());

		for (Integer coin: keys){
			OutputView.printChangeStatus(coin, changeAmount.get(coin));
		}
		OutputView.printNewLine();
	}

}
