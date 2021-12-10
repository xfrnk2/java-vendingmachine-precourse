package vendingmachine.item;

import java.util.List;

import com.google.common.base.Splitter;

import vendingmachine.type.DelimiterType;
import vendingmachine.type.ErrorType;

public class Item {

	private final String name;
	private final int cost;
	private final int amount;

	public Item(String itemDetail) {

		List<String> itemInfoList = initialize(itemDetail);
		this.name = itemInfoList.get(0);
		this.cost = convertToNumber(itemInfoList.get(1), "가격");
		this.amount = convertToNumber(itemInfoList.get(2), "수량");
		isInvalidCostCondition(this.cost);
	}

	public List<String> initialize(String itemDetail) {
		List<String> itemInfoList = Splitter.on(DelimiterType.COMMA.getDelimiter())
			.omitEmptyStrings()
			.trimResults()
			.splitToList(extractPrefixAndSuffix(itemDetail));
		validateSize(itemInfoList);

		return itemInfoList;
	}

	private String extractPrefixAndSuffix(String itemDetail) {
		if (!(itemDetail.startsWith(DelimiterType.ITEM_PREFIX.getDelimiter())
			&& itemDetail.endsWith(DelimiterType.ITEM_SUFFIX.getDelimiter()))) {
			throw new IllegalArgumentException("상품 상세는 여는 대괄호([)로 시작하고, 닫는 대괄호(])로 끝나야 합니다.");
		}
		return itemDetail.substring(1, itemDetail.length() - 1);
	}

	private void validateSize(List<String> itemInfo) {
		if (itemInfo.size() != 3) {
			throw new IllegalArgumentException("입력이 '상품명, 가격 ,수량' 세 항목 단위가 아닙니다.");
		}
	}

	private int convertToNumber(String value, String unit) {
		try {
			int converted = Integer.parseInt(value);
			if (converted < 0) {
				throw new IllegalArgumentException(ErrorType.ERROR_IS_NOT_NUMBER.getError());
			}
			return converted;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format(ErrorType.ERROR_IS_NOT_NUMBER.getError(), unit));
		}
	}

	private void isInvalidCostCondition(int cost) {
		if (!(cost % 10 == 0 && 100 <= cost)) {
			throw new IllegalArgumentException(ErrorType.ERROR_COST_INVALID_CONDITION.getError());
		}
	}

	public String getName() {
		return this.name;
	}

	public int getCost() {
		return this.cost;
	}

	public int getAmount() {
		return this.amount;
	}

}
