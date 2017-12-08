package mathproblems.simplegenerator;

import mathproblems.generator.Operation;
import mathproblems.generator.Problem;

public class IntegerProblem extends Problem<Integer> {

	private int remainder;

	public IntegerProblem(Operation[] operations, Integer[] operands) {
		super(operations, operands);
	}

	@Override
	protected void validateArguments(Operation[] operations, Integer[] operands) {
		if (operations.length == 0)
			throw new IllegalArgumentException("No operations being performed.");
		if (operations.length + 1 != operands.length)
			throw new IllegalArgumentException("The number of operands must be equal to operations plus one.");
	}

	protected final Integer getRemainder() {
		return remainder;
	}

	protected final void setRemainder(Integer remainder) {
		this.remainder = remainder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + remainder;
		return result;
	}

	/**
	 * For testing purposes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntegerProblem other = (IntegerProblem) obj;
		if (remainder != other.remainder)
			return false;
		return true;
	}

}
