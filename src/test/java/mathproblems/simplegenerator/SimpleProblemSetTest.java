package mathproblems.simplegenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mathproblems.generator.Operation;

public class SimpleProblemSetTest {
	private SimpleProblemSet set;
	private IntegerProblem p;

	@Before
	public void init() {
		set = new SimpleProblemSet();
		p = new IntegerProblem(new Operation[] { Operation.ADD, Operation.SUBTRACT }, new Integer[] { 1, 2, 3 });
		set.addProblem(p);
	}

	@Test
	public void test_iterator() {
		assertTrue(set.iterator().hasNext());
	}

	@Test
	public void test_addProblem1() {
		IntegerProblem s = set.iterator().next();
		assertEquals(p, s);
	}

	@Test
	public void test_addProblem2() {
		set.addProblem(new Operation[] { Operation.ADD, Operation.SUBTRACT }, new Integer[] { 1, 2, 3 });
		set.iterator().next();
		assertTrue(set.iterator().hasNext());
		IntegerProblem s = set.iterator().next();
		assertEquals(p, s);
	}
}
