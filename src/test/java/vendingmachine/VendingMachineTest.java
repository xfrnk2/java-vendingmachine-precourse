package vendingmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static camp.nextstep.edu.missionutils.test.Assertions.*;

import java.util.Map;

import vendingmachine.change.Change;
import vendingmachine.change.ChangeAmount;

public class VendingMachineTest {

	@Test
	public void initializeChange(){
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Change change = new Change("-1");
		});
	}

	@Test
	public void initializeHoldingChanges(){
		ChangeAmount changeAmount = new ChangeAmount(450);
		Map<Integer, Integer> changes = changeAmount.getChangeAmount();
		Assertions.assertEquals(changes.get(500), 0);
		Assertions.assertEquals(changes.get(100), 4);
		Assertions.assertEquals(changes.get(50), 1);
		Assertions.assertEquals(changes.get(10), 0);


	}

}
