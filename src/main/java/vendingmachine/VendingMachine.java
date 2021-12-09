package vendingmachine;

import java.util.HashMap;
import java.util.Map;

import vendingmachine.change.Change;
import vendingmachine.change.ChangeAmount;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {
	private Map<Integer, Integer> changeAmount;

	public void run() {
		initialize();
	}

	private void initialize(){
		Change change = new Change(InputView.getHoldingTotalChanges());
		setMachineHoldingChange(change);

	}


	private void setMachineHoldingChange(Change change) {
		while (true) {
			try {
				ChangeAmount changeAmount = new ChangeAmount(change.getChange());
				this.changeAmount = changeAmount.getChangeAmount();
				break;
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}
	}
}
