package mathproblems.generator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mathproblems.generator.problem.Problem;

public class ProblemTest {
	// @Test(expected=IllegalArgumentException.class)
	// public void test_validateArguments_throwIllegalArgumentException_zeroLength()
	// {
	// new Problem<BigDecimal>(new Operation[] {}, new BigDecimal[]
	// {BigDecimal.valueOf(1),BigDecimal.valueOf(2),BigDecimal.valueOf(3)});
	// }
	//
	// @Test(expected=IllegalArgumentException.class)
	// public void test_validateArguments_throwIllegalArgumentException_mismatch1()
	// {
	// new Problem<BigDecimal>(new Operation[] {Operation.ADD}, new BigDecimal[]
	// {BigDecimal.valueOf(1),BigDecimal.valueOf(2),BigDecimal.valueOf(3)});
	// }
	//
	// @Test(expected=IllegalArgumentException.class)
	// public void test_validateArguments_throwIllegalArgumentException_mismatch2()
	// {
	// new Problem<BigDecimal>(new Operation[] {Operation.ADD}, new BigDecimal[]
	// {BigDecimal.valueOf(1)});
	// }
	//
	// @Test(expected=IllegalArgumentException.class)
	// public void test_validateArguments_throwIllegalArgumentException_mismatch3()
	// {
	// new Problem<BigDecimal>(new Operation[] {Operation.ADD,Operation.SUBTRACT},
	// new BigDecimal[] {BigDecimal.valueOf(1)});
	// }
	private Problem<Integer> p;
	private Operation[] o = { Operation.ADD };
	private Integer[] i = { 1 };

	@Before
	public void setUp() {
		p = new Problem<>(o, i);
	}

	@Test
	public void test_settersAndGetters() {
		assertEquals(o[0], p.getOperations()[0]);
		assertEquals(i[0], p.getOperands()[0]);
		p.setResult(5);
		assertEquals(5, (int) p.getResult());
	}
	
	// TODO implement test for faction problems
}
