package mathproblems.generator.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

import mathproblems.generator.problem.Problem;

public class SimpleCalculator implements Calculator<Problem<BigDecimal>> {

	private int scale = 10;
	private RoundingMode roundingMode = RoundingMode.HALF_UP;

	/**
	 * @return the sCALE
	 */
	public final int getScale() {
		return scale;
	}

	/**
	 * @param scale
	 *            the sCALE to set
	 */
	public final void setScale(int scale) {
		this.scale = scale;
	}

	/**
	 * @return the roundingMode
	 */
	public final RoundingMode getRoundingMode() {
		return roundingMode;
	}

	/**
	 * @param roundingMode
	 *            the roundingMode to set
	 */
	public final void setRoundingMode(RoundingMode roundingMode) {
		this.roundingMode = roundingMode;
	}

	@Override
	public void calculate(Problem<BigDecimal> p) {
		int op = 0;
		BigDecimal[] nums = p.getOperands();

		p.setResult(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			switch (p.getOperations()[op++]) {
			case ADD:
				p.setResult(p.getResult().add(nums[i]));
				break;
			case SUBTRACT:
				p.setResult(p.getResult().subtract(nums[i]));
				break;
			case MULTIPLY:
				p.setResult(p.getResult().multiply(nums[i]));
				break;
			case DIVIDE:
				if (nums[i].doubleValue() == 0.0)
					throw new ArithmeticException(String.format("Element[%d] is 0. Cannot divide by 0.", i));
				p.setResult(p.getResult().divide(nums[i], this.scale, this.roundingMode));
				break;
			default:
				throw new UnsupportedOperationException();
			}
		}
	}

	@Override
	public void calculate(List<Problem<BigDecimal>> set) {
		Iterator<Problem<BigDecimal>> p = set.iterator();
		while (p.hasNext()) {
			this.calculate(p.next());
		}
	}

}
