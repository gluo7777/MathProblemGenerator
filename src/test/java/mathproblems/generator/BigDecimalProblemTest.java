package mathproblems.generator;

import java.math.BigDecimal;

import org.junit.Test;

import mathproblems.generator.problem.Problem;

public class BigDecimalProblemTest {
	@Test(expected=IllegalArgumentException.class)
	public void test_validateArguments_throwIllegalArgumentException_zeroLength() {
		new Problem<BigDecimal>(new Operation[] {}, new BigDecimal[] {BigDecimal.valueOf(1),BigDecimal.valueOf(2),BigDecimal.valueOf(3)});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_validateArguments_throwIllegalArgumentException_mismatch1() {
		new Problem<BigDecimal>(new Operation[] {Operation.ADD}, new BigDecimal[] {BigDecimal.valueOf(1),BigDecimal.valueOf(2),BigDecimal.valueOf(3)});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_validateArguments_throwIllegalArgumentException_mismatch2() {
		new Problem<BigDecimal>(new Operation[] {Operation.ADD}, new BigDecimal[] {BigDecimal.valueOf(1)});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_validateArguments_throwIllegalArgumentException_mismatch3() {
		new Problem<BigDecimal>(new Operation[] {Operation.ADD,Operation.SUBTRACT}, new BigDecimal[] {BigDecimal.valueOf(1)});
	}
}
