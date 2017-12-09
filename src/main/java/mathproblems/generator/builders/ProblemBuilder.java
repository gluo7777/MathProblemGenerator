package mathproblems.generator.builders;

import java.util.List;

import mathproblems.generator.problem.Problem;

@SuppressWarnings("rawtypes")
public interface ProblemBuilder<T extends Problem> {

	/**
	 * Builds a {@link ProblemSet} with the properties configured by the setters
	 * 
	 * @return
	 */
	List<T> buildProblemSet();
}
