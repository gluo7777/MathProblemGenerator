package generator;

public interface ProblemBuilder {

	/**
	 * Builds a {@link ProblemSet} with the properties configured by the setters
	 * 
	 * @return
	 */
	ProblemSet buildProblemSet();
}
