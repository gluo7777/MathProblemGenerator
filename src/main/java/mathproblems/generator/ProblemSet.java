package mathproblems.generator;

import java.util.Comparator;

public interface ProblemSet<T extends Problem> extends Iterable<T>{
	/**
	 * Add a problem to this collection.
	 * @param problem to be added
	 */
	void addProblem(T problem);
	
	/**
	 * 
	 * @return number of problems
	 */
	int size();
	
	/**
	 * Removes all problems from this collection.
	 */
	void clearProblems();
	
//	/**
//	 * Returns this collection.
//	 * @return
//	 */
//	Collection<T> getProblems();
	
	/**
	 * Sorts this collection.
	 * @param comparator to be used for sorting
	 */
	void sortProblems(Comparator<T> comparator);
}
