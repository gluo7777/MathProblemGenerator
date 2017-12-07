package mathproblems.simplegenerator.randomizer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mathproblems.generator.Operation;
import mathproblems.generator.Randomizer;
import mathproblems.util.RandomGenerator;

public class SimpleRandomizer implements Randomizer<Integer> {

	private List<Bound<Operation>> bounds;
//	private Operation operation = null;
	private int total;
	private final RandomGenerator gen;

	public SimpleRandomizer() {
		this.gen = new RandomGenerator();
	}

	public SimpleRandomizer(RandomGenerator gen) {
		this.gen = gen;
	}

	@Override
	public void setAndMapFrequencies(Map<Operation, Integer> frequencies) {
		this.bounds = new LinkedList<>();
		if (frequencies.size() < 1)
			throw new IllegalArgumentException("Frequency map must contain at least one object.");
		int total = 0;
		for (Operation op : frequencies.keySet()) {
			int probability = frequencies.get(op);
			if (probability < 0)
				throw new IllegalArgumentException("Cannot have a negative probabilty.");
			this.bounds.add(new Bound<Operation>(op, total + 1, total += probability));
		}
		if (total == 0)
			throw new IllegalArgumentException("Frequency map must contain at least one non-zero probability.");
		this.total = total;
	}

	/**
	 * Used for setting a single operation when mixeMode is false.
	 * 
	 * @param operation
	 */
	/*public void setSingleOperation(Operation operation) {
		this.operation = operation;
	}*/

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
			/*
			 * imagine if freq = {0,0,0,1}
			 * bounds = {(1,1),(2,2),(3,3),(4,5)
			 */
			// returns first non-zero probability
			for (Bound<Operation> b : bounds)
				if(b.upper != 0)
					return b.value;
		}
		return null; // should never be returned
	}

	@Override
	public Integer generateNumber(int minDigit, int maxDigit) {
		if (minDigit <= 0 || minDigit <= 0)
			throw new IllegalArgumentException("Digits cannot be less than 1");
		if (maxDigit < minDigit)
			throw new IllegalArgumentException("A number cannot have fewer maximum digits than minimum digits.");
		int base = (int) Math.pow(10, gen.randInt(minDigit, maxDigit) - 1), digit = gen.randInt(1, 9) * base;
		while (base > 1) {
			base /= 10;
			digit += gen.randInt(0, 9) * base;
		}
		return digit;
	}

	@Override
	public int generateOperandCount(int minCount, int maxCount) {
		return gen.randInt(minCount, maxCount);
	}
}
