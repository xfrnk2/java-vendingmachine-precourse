package vendingmachine.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vendingmachine.type.ErrorType;

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
			throw new IllegalArgumentException(ErrorType.ERROR_IS_EMPTY.getError());
		}
	}

	public List<Item> findAll(){
		return Collections.unmodifiableList(items);
	}


	public Item findLeastCostItem(){
		return items.stream()
			.min(Comparator.comparing(Item::getCost))
			.orElseThrow(IllegalArgumentException::new);
	}


	public Item findItem(String name){
		if (items.stream().noneMatch(item -> name.equals(item.getName()))){
			throw new IllegalArgumentException(ErrorType.ERROR_INVALID_NAME.getError());
		}
		return items
			.stream()
			.filter(item -> name.equals(item.getName()))
			.findFirst()
			.get();
	}

	public boolean isAllOutOfOrder() {
		return items.stream().allMatch(item -> item.getAmount() == 0);
	}

}
