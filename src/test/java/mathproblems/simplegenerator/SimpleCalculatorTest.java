package mathproblems.simplegenerator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mathproblems.generator.Operation;
import mathproblems.simplegenerator.IntegerCalculator;
import mathproblems.simplegenerator.IntegerProblem;
import mathproblems.simplegenerator.SimpleProblemSet;

public class SimpleCalculatorTest {
	private SimpleProblemSet set;
	private IntegerProblem p1,p2;
	private IntegerCalculator calculator;
	
	@Before
	public void init() {
		set = new SimpleProblemSet();
		p1 = new IntegerProblem(new Operation[] { Operation.ADD, Operation.SUBTRACT }, new Integer[] { 1, 2, 3 });
		p2 = new IntegerProblem(new Operation[] { Operation.ADD }, new Integer[] { 1, 2 });
		set.addProblem(p1);
		set.addProblem(p2);
		calculator = new IntegerCalculator();
	}
	
	@Test(expected=ArithmeticException.class)
	public void test_calculate_divideByZero_throwArithmeticException() {
		IntegerProblem zeroProb = new IntegerProblem(new Operation[] { Operation.DIVIDE }, new Integer[] { 1, 0 });
		calculator.calculate(zeroProb);
	}
	
	@Test
	public void test_calculate_singleOperations() {
		calculator.calculate(p2);
		assertEquals(new Integer(3), p2.getResult());
	}
	
	@Test
	public void test_calculate_multipleOperations() {
		calculator.calculate(p1);
		assertEquals(new Integer(0), p1.getResult());
	}
	
	@Test
	public void test_calculate_multipleProblems() {
		calculator.calculate(set);
		assertEquals(new Integer(0), p1.getResult());
		assertEquals(new Integer(3), p2.getResult());
	}
}
