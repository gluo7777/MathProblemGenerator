package mathproblems.generator.randomizer;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mathproblems.generator.Operation;

public class SimpleRandomizer implements Randomizer<BigDecimal> {

	private List<Bound<Operation>> bounds;
	private int total;
	private final NumberGenerator gen;

	public SimpleRandomizer() {
		this.gen = new NumberGenerator();
	}

	public SimpleRandomizer(NumberGenerator gen) {
		this.gen = gen;
	}

	@Override
	public void setAndMapFrequencies(Map<Operation, Integer> frequencies) {
		this.bounds = new LinkedList<>();
		int total = 0;
		for (Operation op : frequencies.keySet()) {
			this.bounds.add(new Bound<Operation>(op, total + 1, total += frequencies.get(op)));
		}
		this.total = total;
	}

	@Override
	public Operation generateOperation(boolean mixedMode) {
		if (this.bounds.isEmpty())
			throw new UnsupportedOperationException("Frequencies have not been set.");
		if (mixedMode) {
			int index = gen.randInt(1, total);
			for (Bound<Operation> b : bounds) {
				if (index >= b.lower && index <= b.upper)
					return b.value;
			}
		} else {
			// returns first non-zero probability
			for (Bound<Operation> b : bounds)
				if(b.upper != 0)
					return b.value;
		}
		return null; // should never be returned
	}

	@Override
	public BigDecimal generateNumber(int minDigit, int maxDigit) {
		int base = (int) Math.pow(10, gen.randInt(minDigit, maxDigit) - 1);
		BigDecimal digit = BigDecimal.valueOf(gen.randInt(1, 9)).multiply(BigDecimal.valueOf(base));
		while (base > 1) {
			base /= 10;
			digit = digit.add(BigDecimal.valueOf(gen.randInt(0, 9) * base));
		}
		return digit;
	}

	@Override
	public int generateOperandCount(int minCount, int maxCount) {
		return gen.randInt(minCount, maxCount);
	}
}
