package mathproblems.generatorImpl;

import mathproblems.generator.Operation;
import mathproblems.generator.Problem;

public class SimpleProblem extends Problem {

	private int remainder;

	public SimpleProblem(Operation[] operations, Integer[] operands) {
		super(operations, operands);
	}

	@Override
	protected void validateArguments(Operation[] operations, Number[] operands) {
		if (operations.length == 0)
			throw new IllegalArgumentException("No operations being performed.");
		if (operations.length + 1 != operands.length)
			throw new IllegalArgumentException("The number of operands must be equal to operations plus one.");
	}

	protected final int getRemainder() {
		return remainder;
	}

	protected final void setRemainder(int remainder) {
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
		SimpleProblem other = (SimpleProblem) obj;
		if (remainder != other.remainder)
			return false;
		return true;
	}

}
