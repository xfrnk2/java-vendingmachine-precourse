package vendingmachine.type;

public enum TextType {
	ERROR_PREFIX("[ERROR] "),
	ERROR_IS_NOT_NUMBER("%s은(는) 숫자여야 합니다.%n"),
	ERROR_IS_EMPTY("빈 값을 입력할 수 없습니다. %n"),
	ERROR_IS_NEGATIVE("보유 금액은 0보다 작을 수 없습니다.");

	private final String error;

	TextType(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
}
