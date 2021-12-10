package vendingmachine.type;

public enum ErrorType {
	ERROR_PREFIX("[ERROR] "),
	ERROR_IS_NOT_NUMBER("%s은(는) 숫자여야 합니다.%n"),
	ERROR_IS_EMPTY("빈 값을 입력할 수 없습니다. %n"),
	ERROR_IS_NEGATIVE("보유 금액은 0보다 작을 수 없습니다."),
	ERROR_COST_INVALID_CONDITION("상품 가격은 100 이상이거나 10으로 나누어 떨어져야 합니다."),
	ERROR_INVALID_NAME("존재하는 상품의 이름만 입력할 수 있습니다.");

	private final String error;

	ErrorType(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
}
