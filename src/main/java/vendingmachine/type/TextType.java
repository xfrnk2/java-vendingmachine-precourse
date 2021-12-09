package vendingmachine.type;

import javax.xml.soap.Text;

public enum TextType {
	ERROR_PREFIX("[ERROR] "),
	ERROR_IS_NOT_NUMBER("[ERROR] 금액은 숫자여야 합니다.");

	private final String error;

	TextType (String error){
		this.error = error;
	}

	public String getError(){
		return error;
	}
}
