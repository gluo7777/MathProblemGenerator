package mathproblems.simplegenerator;

import java.util.Iterator;

import mathproblems.generator.Calculator;
import mathproblems.generator.ProblemSet;

public class IntegerCalculator implements Calculator<IntegerProblem, ProblemSet<IntegerProblem>> {

	@Override
	public void calculate(IntegerProblem p) {
		int op = 0;
		Integer[] nums = p.getOperands();

		p.setResult(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			switch (p.getOperations()[op++]) {
			case ADD:
				p.setResult(p.getResult() + nums[i]);
				break;
			case SUBTRACT:
				p.setResult(p.getResult() - nums[i]);
				break;
			case MULTIPLY:
				p.setResult(p.getResult() * nums[i]);
				break;
			case DIVIDE:
				if(nums[i] == 0)
					throw new ArithmeticException(String.format("Element[%d] is 0. Cannot divide by 0.", i));
				p.setResult(p.getResult() / nums[i]);
				p.setRemainder(p.getResult() % nums[i]);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void calculate(ProblemSet<IntegerProblem> set) {
		Iterator<IntegerProblem> p = set.iterator();
		while (p.hasNext()) {
			this.calculate(p.next());
		}
	}

}
