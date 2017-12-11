package mathproblems.generator.gui;

import java.math.BigDecimal;
import java.util.List;

import mathproblems.generator.builders.SimpleProblemBuilder;
import mathproblems.generator.problem.Problem;

public class ProblemWrapper {
	private SimpleProblemBuilder simpleProblemBuilder;
	private List<Problem<BigDecimal>> simpleProblemList;

	protected final SimpleProblemBuilder getSimpleProblemBuilder() {
		return simpleProblemBuilder;
	}

	protected final void setSimpleProblemBuilder(SimpleProblemBuilder simpleProblemBuilder) {
		this.simpleProblemBuilder = simpleProblemBuilder;
	}

	protected final List<Problem<BigDecimal>> getSimpleProblemList() {
		return simpleProblemList;
	}

	protected final void setSimpleProblemList(List<Problem<BigDecimal>> simpleProblemList) {
		this.simpleProblemList = simpleProblemList;
	}
}
