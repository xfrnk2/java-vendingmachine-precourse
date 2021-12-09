package vendingmachine.type;

public enum DelimiterType {
	SEMICOLON(";"),
	COMMA(","),
	ITEM_PREFIX("["),
	ITEM_SUFFIX("]");

	private final String delimiter;

	DelimiterType (String delimiter){
		this.delimiter = delimiter;
	}

	public String getDelimiter(){
		return delimiter;
	}
}
