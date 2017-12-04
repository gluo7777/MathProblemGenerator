package mathproblems.generator;

public abstract class Problem {
	private final Operation[] operations;
	private final Number[] operands;
	private Number result;

	/**
	 * Constructor calls validateArguments. This method must be overrided to properly conduct argument validation.
	 * @param operations
	 * @param operands
	 */
	public Problem(Operation[] operations, Number[] operands) {
		validateArguments(operations, operands);
		this.operations = operations;
		this.operands = operands;
	}
	
	protected abstract void validateArguments(Operation[] operations, Number[] operands);

	public void setResult(Number result) {
		this.result = result;
	}

	public final Operation[] getOperations() {
		return operations;
	}

	public final Number[] getOperands() {
		return operands;
	}

	public final Number getResult() {
		return result;
	}

}
