package mathproblems.generator.problem;

import mathproblems.generator.Operation;

public class Problem<T> {
	private final Operation[] operations;
	private final T[] operands;
	private T answer;
	private T result;

	/**
	 * Constructor calls validateArguments. This method must be overrided to
	 * properly conduct argument validation.
	 * 
	 * @param operations
	 * @param operands
	 */
	public Problem(Operation[] operations, T[] operands) {
		// validateArguments(operations, operands);
		this.operations = operations;
		this.operands = operands;
	}

	// implementation of validation should implementor's responsibility and should
	// be done outside the scope of this class' constructor
	// protected void validateArguments(Operation[] operations, T[] operands) {
	// if (operations.length == 0)
	// throw new IllegalArgumentException("No operations being performed.");
	// if (operations.length + 1 != operands.length)
	// throw new IllegalArgumentException("The number of operands must be equal to
	// operations plus one.");
	// }

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

	public final T getAnswer() {
		return answer;
	}

	public final void setAnswer(T answer) {
		this.answer = answer;
	}

}
