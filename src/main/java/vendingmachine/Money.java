package vendingmachine;

import vendingmachine.type.ErrorType;

public interface Money {
	default int convertToNumber(String amount){
		try {
			return Integer.parseInt(amount);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format(ErrorType.ERROR_IS_NOT_NUMBER.getError(), "금액"));
		}
	};

	default void validate(int amount){
		if (amount < 0){
			throw new IllegalArgumentException(ErrorType.ERROR_IS_NEGATIVE.getError());
		}
	};
}
