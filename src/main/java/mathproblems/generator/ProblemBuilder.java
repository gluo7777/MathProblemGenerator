package mathproblems.generator;

public interface ProblemBuilder<U extends ProblemSet<? extends Problem>> {

	/**
	 * Builds a {@link ProblemSet} with the properties configured by the setters
	 * 
	 * @return
	 */
	U buildProblemSet();
}
