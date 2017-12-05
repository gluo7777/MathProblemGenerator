package mathproblems.simplegenerator;

import java.util.Map;

import mathproblems.generator.Operation;

public class SimpleProblemBuilder {
	private int count = 0;
	private int min = 0, max = 10;
	private Map<Operation, Integer> frequencies;
	private SimpleProblemSet set;
	private boolean solveProblems = false, mixedMode = false;

	SimpleProblemBuilder() {

	}
	
	private boolean validateFrequency() {
		return false;
	}

	public final SimpleProblemSet buildProblemSet() {
		if(!this.validateFrequency()) {
			throw new IllegalArgumentException(frequencies.toString()+" is not a valid frequency setting.");
		}
		set = new SimpleProblemSet();
		
		// build set
		
		return set;
	}
	
	public final SimpleProblemBuilder setCount(int count) {
		this.count = count;
		return this;
	}

	public final SimpleProblemBuilder setMin(int min) {
		this.min = min;
		return this;
	}

	public final SimpleProblemBuilder setMax(int max) {
		this.max = max;
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

}
