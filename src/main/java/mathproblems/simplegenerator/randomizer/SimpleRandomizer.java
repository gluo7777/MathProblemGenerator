package mathproblems.simplegenerator.randomizer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mathproblems.generator.Operation;
import mathproblems.generator.Randomizer;

public class SimpleRandomizer implements Randomizer<Integer> {

	private final List<Bound<Operation>> bounds;
	private Operation operation = null;
	private int total;

	public SimpleRandomizer() {
		this.bounds = new LinkedList<>();
	}

	/**
	 * Parses the map and builds a random generator based on the supplied
	 * probabilities for each Operation. If all-zero probabilites, then each
	 * operation will have equal likelihood.
	 * 
	 * @param frequencies
	 * 
	 * @throws IllegalArgumentException
	 *             - if map is empty or has any negative probabilities
	 */
	public void setAndMapFrequencies(Map<Operation, Integer> frequencies) {
		if (frequencies.size() < 1)
			throw new IllegalArgumentException("Frequency map must contain at least one object.");
		int total = 0;
		for (Operation op : frequencies.keySet()) {
			int probability = frequencies.get(op);
			if (probability < 0)
				throw new IllegalArgumentException("Cannot have a negative probabilty.");
			this.bounds.add(new Bound<Operation>(op, ++total, total += probability));
		}
		this.total = total;
	}

	/**
	 * Used for setting a single operation when mixeMode is false.
	 * @param operation
	 */
	public void setSingleOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public Operation generateOperation(boolean mixedMode) {
		if (mixedMode) {
			if (this.bounds.isEmpty())
				throw new UnsupportedOperationException("Frequencies have not been set.");
			int index = new Random().nextInt(total) + 1;
			for (Bound<Operation> b : bounds) {
				if (index >= b.lower || index <= b.upper)
					return b.value;
			}
		} else {
			if (this.operation == null)
				throw new NullPointerException("Operation has not been set.");
			return this.operation;
		}
		return null; // should never be returned
	}

	@Override
	public Integer generateNumber(int minDigit, int maxDigit) {
		if (minDigit <= 0 || minDigit <= 0)
			throw new IllegalArgumentException("Digits cannot be less than 1");
		int base = (int) Math.pow(10, maxDigit - 1), digit = getInt(1, 9) * base;
		while (base > 1) {
			base /= 10;
			digit += getInt(0, 9) * base;
		}
		return digit;
	}


	@Override
	public int generateOperandCount(int minCount, int maxCount) {
		return getInt(minCount, maxCount);
	}

	protected int getInt(int minDigit, int maxDigit) {
		int index = (int) (Math.random() * (maxDigit - minDigit + 1)) + minDigit;
		return index;
	}
}
