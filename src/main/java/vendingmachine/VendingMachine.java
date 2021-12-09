package vendingmachine;

import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;

import vendingmachine.change.Change;
import vendingmachine.change.ChangeAmount;
import vendingmachine.item.Items;
import vendingmachine.type.DelimiterType;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {

	public void run() {

		Change change = initializeChange(); //refactor 필요 (input 순회 가능하도록)
		Map<Integer, Integer> changeAmount = initializeHoldingChange(change);
		Items items = initializeItems();
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


}
