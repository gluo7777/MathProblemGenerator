package mathproblems.generatorImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import mathproblems.generator.Operation;
import mathproblems.generator.ProblemSet;

public class SimpleProblemSet implements ProblemSet<SimpleProblem> {

	private final List<SimpleProblem> problems;

	public SimpleProblemSet() {
		this.problems = new LinkedList<>();
	}

	public Iterator<SimpleProblem> iterator() {
		return this.problems.iterator();
	}

	public void addProblem(Operation[] ops, Integer[] nums) {
		this.addProblem(new SimpleProblem(ops, nums));
	}

	@Override
	public void addProblem(SimpleProblem problem) {
		this.problems.add(problem);
	}

	@Override
	public void clearProblems() {
		this.problems.clear();
	}

//	@Override
//	public List<SimpleProblem> getProblems() {
//		return this.problems;
//	}

	@Override
	public void sortProblems(Comparator<SimpleProblem> comparator) {
		Collections.sort(this.problems, comparator);
	}

	@Override
	public int size() {
		return this.problems.size();
	}

}
