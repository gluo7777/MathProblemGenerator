package mathproblems.generator.builders;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import mathproblems.generator.Operation;
import mathproblems.generator.calculator.SimpleCalculator;
import mathproblems.generator.problem.Problem;
import mathproblems.generator.randomizer.Randomizer;
import mathproblems.generator.randomizer.SimpleRandomizer;

public class SimpleProblemBuilder extends CommonProblemBuilder<Problem<BigDecimal>, SimpleProblemBuilder> {
	private int minDigit = 1, maxDigit = 1;
	private List<Problem<BigDecimal>> set;
	private Randomizer<BigDecimal> randomizer;

	public SimpleProblemBuilder() {
	}

	@Override
	protected void validate() {
		super.validate();
		if (this.minDigit < 1 || this.maxDigit < this.minDigit)
			throw new IllegalArgumentException(String
					.format("minimum digit = %d and maximum digit = %d are not valid values.", minDigit, maxDigit));
	}

	public final List<Problem<BigDecimal>> buildProblemSet() {
		this.validate();
		
		if (this.randomizer == null)
			this.randomizer = new SimpleRandomizer();
		
		this.randomizer.setAndMapFrequencies(super.getFrequencies());
		
		set = new LinkedList<>();

		SimpleCalculator calculator = new SimpleCalculator();

		// build set
		for (int i = 0; i < super.getNumberOfProblems(); i++) {
			BigDecimal[] operands = new BigDecimal[this.randomizer.generateOperandCount(super.getMinNumber(), super.getMaxNumber())];
			Operation[] operations = new Operation[operands.length - 1];
			for (int j = 0; j < operations.length; j++) {
				operands[j] = this.randomizer.generateNumber(this.minDigit, this.maxDigit);
				operations[j] = this.randomizer.generateOperation(super.isMixedMode());
			}
			operands[operands.length - 1] = this.randomizer.generateNumber(this.minDigit, this.maxDigit);
			Problem<BigDecimal> p = new Problem<BigDecimal>(operations, operands);
			set.add(p);
		}

		// find solutions
		if (super.isSolveProblems())
			calculator.calculate(this.set);

		return set;
	}
	
	public final SimpleProblemBuilder setRandomizer(SimpleRandomizer randomizer) {
		this.randomizer = randomizer;
		return this;
	}

	public final SimpleProblemBuilder setNumberOfDigits(int minDigit, int maxDigit) {
		this.minDigit = minDigit;
		this.maxDigit = maxDigit;
		return this;
	}

}
