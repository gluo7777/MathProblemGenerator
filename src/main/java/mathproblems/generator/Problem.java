package mathproblems.generator;

public abstract class Problem<T extends Number> {
	private final Operation[] operations;
	private final T[] operands;
	private T result;

	/**
	 * Constructor calls validateArguments. This method must be overrided to properly conduct argument validation.
	 * @param operations
	 * @param operands
	 */
	public Problem(Operation[] operations, T[] operands) {
		validateArguments(operations, operands);
		this.operations = operations;
		this.operands = operands;
	}
	
	protected abstract void validateArguments(Operation[] operations, T[] operands);

	public void setResult(T result) {
		this.result = result;
	}

	public final Operation[] getOperations() {
		return operations;
	}

	public final T[] getOperands() {
		return operands;
	}

	public final T getResult() {
		return result;
	}

	public void setRemainder(int i) {
		// TODO Auto-generated method stub
		
	}

}
