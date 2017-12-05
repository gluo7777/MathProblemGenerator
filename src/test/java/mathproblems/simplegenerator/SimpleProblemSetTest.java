package mathproblems.simplegenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mathproblems.generator.Operation;
import mathproblems.simplegenerator.SimpleProblem;
import mathproblems.simplegenerator.SimpleProblemSet;

public class SimpleProblemSetTest {
	private SimpleProblemSet set;
	private SimpleProblem p;

	@Before
	public void init() {
		set = new SimpleProblemSet();
		p = new SimpleProblem(new Operation[] { Operation.Add, Operation.Subtract }, new Integer[] { 1, 2, 3 });
		set.addProblem(p);
	}

	@Test
	public void test_iterator() {
		assertTrue(set.iterator().hasNext());
	}

	@Test
	public void test_addProblem1() {
		SimpleProblem s = set.iterator().next();
		assertEquals(p, s);
	}

	@Test
	public void test_addProblem2() {
		set.addProblem(new Operation[] { Operation.Add, Operation.Subtract }, new Integer[] { 1, 2, 3 });
		set.iterator().next();
		assertTrue(set.iterator().hasNext());
		SimpleProblem s = set.iterator().next();
		assertEquals(p, s);
	}
}
