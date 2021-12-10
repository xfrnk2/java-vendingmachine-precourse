package vendingmachine;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import vendingmachine.change.Change;
import vendingmachine.change.ChangeAmount;
import vendingmachine.item.Item;
import vendingmachine.item.Items;

public class VendingMachineControllerTest {

	@Test
	public void initializeChange() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Change change = new Change("-1");
		});
	}

	@Test
	public void initializeHoldingChanges() {
		ChangeAmount changeAmount = new ChangeAmount(450);
		Map<Integer, Integer> changes = changeAmount.getChangeAmount();
		Assertions.assertEquals(changes.get(500), 0);
		Assertions.assertEquals(changes.get(100), 4);
		Assertions.assertEquals(changes.get(50), 1);
		Assertions.assertEquals(changes.get(10), 0);
	}

	@Test
	public void initializeItems() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Items items = new Items(Collections.singletonList(";"));
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Items items = new Items(Collections.singletonList(","));
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Items items = new Items(Collections.singletonList(""));
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Items items = new Items(Collections.singletonList("a,b"));
		});
	}

	@Test
	public void initializeItem() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Item item = new Item("[콜라,101,150]");
			Item item2 = new Item("[콜라,asd,]");
		});
	}
}
