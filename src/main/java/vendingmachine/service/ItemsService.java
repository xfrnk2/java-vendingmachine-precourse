package vendingmachine.service;

import java.util.Comparator;
import java.util.List;

import com.google.common.base.Splitter;

import vendingmachine.item.Item;
import vendingmachine.item.Items;
import vendingmachine.type.DelimiterType;
import vendingmachine.type.ErrorType;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class ItemsService {

	public static Items initializeItems() {
		try {
			String input = InputView.getItemList();
			Items items = getItemsByInput(input);
			OutputView.printNewLine();
			return items;
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return initializeItems();
		}
	}

	public static Items getItemsByInput(String input) {
		List<String> itemList = Splitter.on(DelimiterType.SEMICOLON.getDelimiter())
			.omitEmptyStrings().trimResults().splitToList(input);
		return new Items(itemList);
	}


	public static Item getLeastCostItem(List<Item> items){
		return items.stream()
			.min(Comparator.comparing(Item::getCost))
			.orElseThrow(IllegalArgumentException::new);
	}


	public static Item checkContainingItem(List<Item> items, String name){
		if (items.stream().noneMatch(item -> name.equals(item.getName()))){
			throw new IllegalArgumentException(ErrorType.ERROR_INVALID_NAME.getError());
		}
		return items
			.stream()
			.filter(item -> name.equals(item.getName()))
			.findFirst()
			.get();
	}

	public static boolean isAllOutOfOrder(List<Item> items) {
		return items.stream().allMatch(item -> item.getAmount() == 0);
	}
}
