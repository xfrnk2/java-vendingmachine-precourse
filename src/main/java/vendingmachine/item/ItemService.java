package vendingmachine.item;

import java.util.List;

import com.google.common.base.Splitter;

import vendingmachine.type.DelimiterType;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class ItemService {

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

}
