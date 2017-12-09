package mathproblems.generator.calculator;

import java.util.List;

import mathproblems.generator.problem.Problem;

@SuppressWarnings("rawtypes")
public interface Calculator<T extends Problem> {
	void calculate(T problem);
	void calculate(List<T> set);
}
