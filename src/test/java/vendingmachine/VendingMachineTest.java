package vendingmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static camp.nextstep.edu.missionutils.test.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import vendingmachine.change.Change;
import vendingmachine.change.ChangeAmount;
import vendingmachine.item.Item;
import vendingmachine.item.Items;

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

	@Test
	public void initializeItems(){
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Items items = new Items(Arrays.asList(";"));
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Items items = new Items(Arrays.asList(","));
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Items items = new Items(Arrays.asList(""));
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Items items = new Items(Arrays.asList("a,b"));
		});
	}

	@Test
	public void initializeItem(){
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Item item = new Item("[콜라,-1,101]");
			Item item2 = new Item("[콜라,asd,]");
		});
	}
}
