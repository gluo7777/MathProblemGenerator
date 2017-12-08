package mathproblems.simplegenerator;

import java.util.Map;

import mathproblems.generator.Operation;
import mathproblems.generator.Randomizer;
import mathproblems.simplegenerator.randomizer.SimpleRandomizer;

public class SimpleProblemBuilder {
	private int count = 1;
	private int minNumber = 2, maxNumber = 2;
	private int minDigit = 1, maxDigit = 1;
	private boolean solveProblems = false, mixedMode = false;
	private Map<Operation, Integer> frequencies;
	private SimpleProblemSet<IntegerProblem> set;
	private Randomizer<Integer> randomizer;

	public SimpleProblemBuilder() {
	}

	private void validate() {
		if (this.count <= 0)
			throw new IllegalArgumentException("Not building anything");
		if (this.minNumber < 2 || this.maxNumber < this.minNumber)
			throw new IllegalArgumentException(String
					.format("minimum number = %d and maximum number = %d are not valid values.", minNumber, maxNumber));
		if (this.minDigit < 1 || this.maxDigit < this.minDigit)
			throw new IllegalArgumentException(String
					.format("minimum digit = %d and maximum digit = %d are not valid values.", minDigit, maxDigit));
		if (frequencies.size() < 1)
			throw new IllegalArgumentException("Frequency map must contain at least one object.");
		int total = 0;
		for (int probability : frequencies.values()) {
			if (probability < 0)
				throw new IllegalArgumentException("Cannot have a negative probabilty.");
			total += probability;
		}
		if (total == 0)
			throw new IllegalArgumentException("Frequency map must contain at least one non-zero probability.");
	}

	public final SimpleProblemSet<IntegerProblem> buildProblemSet() {
		this.validate();
		if (this.randomizer == null)
			this.randomizer = new SimpleRandomizer();
		set = new SimpleProblemSet<>();

		SimpleCalculator calculator = new SimpleCalculator();

		// build set
		for (int i = 0; i < count; i++) {
			Integer[] operands = new Integer[this.randomizer.generateOperandCount(this.minNumber, this.maxNumber)];
			Operation[] operations = new Operation[operands.length - 1];
			for (int j = 0; j < count - 1; j++) {
				operands[j] = this.randomizer.generateNumber(this.minDigit, this.maxDigit);
				operations[j] = this.randomizer.generateOperation(this.mixedMode);
			}
			operands[operands.length - 1] = this.randomizer.generateNumber(this.minDigit, this.maxDigit);
			IntegerProblem p = new IntegerProblem(operations, operands);
			set.addProblem(p);
		}

		// find solutions
		if (this.solveProblems)
			calculator.calculate(this.set);

		return set;
	}

	public final SimpleProblemBuilder setRandomizer(SimpleRandomizer randomizer) {
		this.randomizer = randomizer;
		return this;
	}

	public final SimpleProblemBuilder setCount(int count) {
		this.count = count;
		return this;
	}

	public final SimpleProblemBuilder setFrequencies(Map<Operation, Integer> frequencies) {
		this.frequencies = frequencies;
		return this;
	}

	public final SimpleProblemBuilder setSolveProblems(boolean solveProblems) {
		this.solveProblems = solveProblems;
		return this;
	}

	public final SimpleProblemBuilder setMixedMode(boolean mixedMode) {
		this.mixedMode = mixedMode;
		return this;
	}

	public final SimpleProblemBuilder setNumberOfOperands(int minNumber, int maxNumber) {
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		return this;
	}

	public final SimpleProblemBuilder setNumberOfDigits(int minDigit, int maxDigit) {
		this.minDigit = minDigit;
		this.maxDigit = maxDigit;
		return this;
	}

}
