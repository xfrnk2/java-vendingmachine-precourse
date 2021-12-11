package vendingmachine.view;

import java.util.List;

import com.google.common.base.Splitter;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	private static final String SEMICOLON = ";";
	private static final String ERROR = "[ERROR] ";
	private static final String NON_DIGIT_ERROR = ERROR + "입력 금액은 숫자여야 합니다.";
	private static final String NEGATIVE_DIGIT_ERROR = ERROR + "입력 금액은 0 이상의 숫자여야 합니다.";
	private static final String EMPTY_ITEMS_ERROR = ERROR + "상품 목록은 비어 있을 수 없습니다.";

	public int scanPrice() {
		try {
			int price = Integer.parseInt(Console.readLine());
			if (price < 0) {
				throw new IllegalArgumentException(NEGATIVE_DIGIT_ERROR);
			}
			return price;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(NON_DIGIT_ERROR);
		}
	}

	public List<String> scanItemDetailList() {
		List<String> items = Splitter.on(SEMICOLON)
			.trimResults()
			.omitEmptyStrings()
			.splitToList(Console.readLine());
		if (items.size() <= 0) {
			throw new IllegalArgumentException(EMPTY_ITEMS_ERROR);
		}
		return items;
	}
}


