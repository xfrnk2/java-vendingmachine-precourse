package vendingmachine.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import vendingmachine.type.TextType;

public class Items {
	private final List<Item> items;


	public Items(List<String> itemList){
		validate(itemList);
		this.items = initialize(itemList);
	}

	private List<Item> initialize(List<String> itemList){
		List<Item> items = new ArrayList<Item>();
		for (String itemDetail : itemList){
			items.add(new Item(itemDetail));
		}
		return items;
	}

	private void validate(List<String> itemList){
		if (itemList == null || itemList.isEmpty()){
			throw new IllegalArgumentException(TextType.ERROR_IS_EMPTY.getError());
		}
	}

	public List<Item> getItems(){
		return Collections.unmodifiableList(items);
	}

}
