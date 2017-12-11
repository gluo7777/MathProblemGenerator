package mathproblems.config;

public enum Property {
	MIN_DIGIT("digits.minimum"),
	MAX_DIGIT("digits.maximum"), 
	MIN_OPERAND("operands.minimum"),
	MAX_OPERAND("operands.maximum"),
	MIXED_MODE("mixedMode"),
	PROBLEM_COUNT("problem.count"),
	FREQUENCY_ROOT("frequency")
	;

	private final String name;

	private Property(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	
}
