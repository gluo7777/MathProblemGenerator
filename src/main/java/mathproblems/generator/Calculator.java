package mathproblems.generator;

public interface Calculator<T extends Problem<? extends Number>, U extends ProblemSet<T>> {
	void calculate(T problem);
	void calculate(U set);
}
