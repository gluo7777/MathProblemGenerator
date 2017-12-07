package mathproblems.simplegenerator;

import org.junit.Test;

import mathproblems.generator.Operation;
import mathproblems.simplegenerator.SimpleProblem;

public class SimpleProblemTest {
	@Test(expected=IllegalArgumentException.class)
	public void test_validateArguments_throwIllegalArgumentException_zeroLength() {
		new SimpleProblem(new Operation[] {}, new Integer[] {1,2,3});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_validateArguments_throwIllegalArgumentException_mismatch1() {
		new SimpleProblem(new Operation[] {Operation.ADD}, new Integer[] {1,2,3});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_validateArguments_throwIllegalArgumentException_mismatch2() {
		new SimpleProblem(new Operation[] {Operation.ADD}, new Integer[] {1});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_validateArguments_throwIllegalArgumentException_mismatch3() {
		new SimpleProblem(new Operation[] {Operation.ADD,Operation.SUBTRACT}, new Integer[] {1});
	}
}
