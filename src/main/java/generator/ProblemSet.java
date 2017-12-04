package generator;

import java.util.Collection;
import java.util.Comparator;

public interface ProblemSet extends Iterable<Problem>{
	/**
	 * Add a problem to this collection.
	 * @param problem to be added
	 */
	void addProblem(Problem problem);
	
	/**
	 * Removes all problems from this collection.
	 */
	void clearProblems();
	
	/**
	 * Returns this collection.
	 * @return
	 */
	Collection<Problem> getProblems();
	
	/**
	 * Sorts this collection.
	 * @param comparator to be used for sorting
	 */
	void sortProblems(Comparator<Problem> comparator);
}
