package vendingmachine.item;

import java.util.List;

import com.google.common.base.Splitter;

import vendingmachine.type.DelimiterType;
import vendingmachine.type.TextType;


public class Item {

	private final String name;
	private final int cost;
	private final int amount;


	Item(String itemDetail){

		List<String> itemInfoList = initialize(itemDetail);
		this.name = itemInfoList.get(0);
		this.cost = convertToNumber(itemInfoList.get(1), "가격");
		this.amount = convertToNumber(itemInfoList.get(2), "수량");
	}

	public List<String> initialize(String itemDetail) throws IllegalArgumentException{
			List<String> itemInfoList = Splitter.on(DelimiterType.SEMICOLON.getDelimiter())
				.omitEmptyStrings()
				.trimResults()
				.splitToList(extractPrefixAndSuffix(itemDetail));
			validate(itemInfoList);
			return itemInfoList;
	}

	private String extractPrefixAndSuffix(String itemDetail){
		if (!(itemDetail.startsWith(DelimiterType.ITEM_PREFIX.getDelimiter())
			&& itemDetail.endsWith(DelimiterType.ITEM_SUFFIX.getDelimiter()))){
			throw new IllegalArgumentException("상품 상세는 여는 대괄호([)로 시작하고, 닫는 대괄호(])로 끝나야 합니다.");
		}
		return itemDetail.substring(1, itemDetail.length()-1);
	}

	private void validate(List<String> itemInfo){
		if (itemInfo.size() != 3){
			throw new IllegalArgumentException("입력이 '상품명, 가격 ,수량' 세 항목 단위가 아닙니다.");
		}
	}

	private int convertToNumber(String money, String unit) {
		try {
			return Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format(TextType.ERROR_IS_NOT_NUMBER.getError(), unit));
		}
	}

	public String getName(){
		return this.name;
	}

	public int getCost(){
		return this.cost;
	}

	public int getAmount(){
		return this.amount;
	}
}
