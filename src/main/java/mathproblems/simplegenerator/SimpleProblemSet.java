package mathproblems.simplegenerator;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import mathproblems.generator.Operation;
import mathproblems.generator.ProblemSet;

public class SimpleProblemSet implements ProblemSet<IntegerProblem> {

	private final List<IntegerProblem> problems;

	public SimpleProblemSet() {
		this.problems = new LinkedList<>();
	}

	public Iterator<IntegerProblem> iterator() {
		return this.problems.iterator();
	}

	public void addProblem(Operation[] ops, Integer[] nums) {
		this.addProblem(new IntegerProblem(ops, nums));
	}

	@Override
	public void addProblem(IntegerProblem problem) {
		this.problems.add(problem);
	}

	@Override
	public void clearProblems() {
		this.problems.clear();
	}
	
	@Override
	public void sortProblems(Comparator<IntegerProblem> comparator) {
		Collections.sort(this.problems, comparator);
	}

	@Override
	public int size() {
		return this.problems.size();
	}

}
