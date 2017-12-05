package mathproblems.generator;

public enum Operation {
	Add("+"), Subtract("-"), Multiple("x"), Divide("/");

	private final String value;

	private Operation(String value) {
		this.value = value;
	}

	public final String getValue() {
		return value;
	}

}
