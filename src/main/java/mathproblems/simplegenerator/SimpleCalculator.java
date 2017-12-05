package mathproblems.simplegenerator;

import java.util.Iterator;

import mathproblems.generator.Calculator;

public class SimpleCalculator implements Calculator<SimpleProblem, SimpleProblemSet> {

	/**
	 * compute result between current result and next operand using operation
	 */
	@Override
	public void calculate(SimpleProblem p) {
		int op = 0;
		Integer[] nums = (Integer[]) p.getOperands();
		SimpleProblem problem = (SimpleProblem) p;

		problem.setResult(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			switch (problem.getOperations()[op++]) {
			case Add:
				problem.setResult((Integer) problem.getResult() + nums[i]);
				break;
			case Subtract:
				problem.setResult((Integer) problem.getResult() - nums[i]);
				break;
			case Multiple:
				problem.setResult((Integer) problem.getResult() * nums[i]);
				break;
			case Divide:
				if(nums[i] == 0)
					throw new ArithmeticException(String.format("Element[%d] is 0. Cannot divide by 0.", i));
				problem.setResult((Integer) problem.getResult() / nums[i]);
				problem.setRemainder((Integer) problem.getResult() % nums[i]);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void calculate(SimpleProblemSet set) {
		Iterator<SimpleProblem> p = set.iterator();
		while (p.hasNext()) {
			this.calculate(p.next());
		}
	}

}
