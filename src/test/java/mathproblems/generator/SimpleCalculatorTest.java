package mathproblems.generator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mathproblems.generator.calculator.SimpleCalculator;
import mathproblems.generator.problem.Problem;

public class SimpleCalculatorTest {
	private static final double DIFF = 0.0001;

	private List<Problem<BigDecimal>> set;
	private Problem<BigDecimal> p1, p2;
	private SimpleCalculator calculator;

	@Before
	public void init() {
		set = new LinkedList<>();
		p1 = new Problem<BigDecimal>(new Operation[] { Operation.ADD, Operation.SUBTRACT },
				new BigDecimal[] { BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3) });
		p2 = new Problem<BigDecimal>(new Operation[] { Operation.ADD },
				new BigDecimal[] { BigDecimal.valueOf(1), BigDecimal.valueOf(2) });
		set.add(p1);
		set.add(p2);
		calculator = new SimpleCalculator();
	}

	@Test(expected = ArithmeticException.class)
	public void test_calculate_divideByZero_throwArithmeticException() {
		Problem<BigDecimal> zeroProb = new Problem<BigDecimal>(new Operation[] { Operation.DIVIDE },
				new BigDecimal[] { BigDecimal.valueOf(1), BigDecimal.valueOf(0) });
		calculator.calculate(zeroProb);
	}

	@Test
	public void test_calculate_singleOperations() {
		calculator.calculate(p2);
		assertEquals(BigDecimal.valueOf(3), p2.getResult());
	}

	@Test
	public void test_calculate_multipleOperations() {
		calculator.calculate(p1);
		assertEquals(BigDecimal.valueOf(0), p1.getResult());

		set.clear();
		set.add(new Problem<>(
				new Operation[] { Operation.ADD, Operation.SUBTRACT, Operation.MULTIPLY, Operation.ADD,
						Operation.DIVIDE, Operation.SUBTRACT, Operation.ADD, Operation.DIVIDE, Operation.ADD,
						Operation.DIVIDE },
				new BigDecimal[] { BigDecimal.valueOf(0), BigDecimal.valueOf(1), BigDecimal.valueOf(2),
						BigDecimal.valueOf(3), BigDecimal.valueOf(4), BigDecimal.valueOf(5), BigDecimal.valueOf(6),
						BigDecimal.valueOf(7), BigDecimal.valueOf(8), BigDecimal.valueOf(9),
						BigDecimal.valueOf(10), }));
		assertEquals(11, set.get(0).getOperands().length);
		assertEquals(10, set.get(0).getOperations().length);
		calculator.calculate(set);
		assertEquals(BigDecimal.valueOf(0.915).doubleValue(), set.get(0).getResult().doubleValue(), DIFF);
	}

	@Test
	public void test_calculate_multipleProblems() {
		calculator.calculate(set);
		assertEquals(BigDecimal.valueOf(0), p1.getResult());
		assertEquals(BigDecimal.valueOf(3), p2.getResult());
	}
}
