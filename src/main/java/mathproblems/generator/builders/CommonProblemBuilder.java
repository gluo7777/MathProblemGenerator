package mathproblems.generator.builders;

import java.util.Map;

import mathproblems.generator.Operation;
import mathproblems.generator.problem.Problem;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class CommonProblemBuilder<T extends Problem, B extends CommonProblemBuilder<T,B>> implements ProblemBuilder<T> {
	private int numberOfProblems = 1;
	private int minNumber = 2, maxNumber = 2;
	private boolean solveProblems = false, mixedMode = false;
	private Map<Operation, Integer> frequencies;

	protected void validate() {
		if (this.numberOfProblems <= 0)
			throw new IllegalArgumentException("Not building anything");
		if (this.minNumber < 2 || this.maxNumber < this.minNumber)
			throw new IllegalArgumentException(String
					.format("minimum number = %d and maximum number = %d are not valid values.", minNumber, maxNumber));
		if (frequencies == null || frequencies.size() < 1)
			throw new IllegalArgumentException("Frequency map must contain at least one object.");
		int total = 0;
		for (int probability : frequencies.values()) {
			if (probability < 0)
				throw new IllegalArgumentException("Cannot have a negative probabilty.");
			total += probability;
		}
		if (total <= 0)
			throw new IllegalArgumentException("Frequency map must contain at least one non-zero probability.");
	}

	public final B setNumberOfProblems(int count) {
		this.numberOfProblems = count;
		return (B) this;
	}

	public final B setFrequencies(Map<Operation, Integer> frequencies) {
		this.frequencies = frequencies;
		return (B) this;
	}

	public final B setSolveProblems(boolean solveProblems) {
		this.solveProblems = solveProblems;
		return (B) this;
	}

	public final B setMixedMode(boolean mixedMode) {
		this.mixedMode = mixedMode;
		return (B) this;
	}

	public final B setNumberOfOperands(int minNumber, int maxNumber) {
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		return (B) this;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public int getNumberOfProblems() {
		return numberOfProblems;
	}

	public boolean isSolveProblems() {
		return solveProblems;
	}

	public boolean isMixedMode() {
		return mixedMode;
	}

	public Map<Operation, Integer> getFrequencies() {
		return frequencies;
	}

}
