package mathproblems.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mathproblems.generator.randomizer.NumberGenerator;
import mathproblems.generator.randomizer.SimpleRandomizer;

public class SimpleRandomizerTest {
	private Map<Operation, Integer> frequencies;
	private SimpleRandomizer randomizer;

	@Mock
	private NumberGenerator gen;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		frequencies = new LinkedHashMap<>();
		randomizer = new SimpleRandomizer(gen);
	}

	@Test
	public void test_generateOperation() {
		frequencies.put(Operation.ADD, 50);
		frequencies.put(Operation.SUBTRACT, 50);
		frequencies.put(Operation.MULTIPLY, 50);
		frequencies.put(Operation.DIVIDE, 50);

		randomizer.setAndMapFrequencies(frequencies);

		returnAndExpect(1, Operation.ADD);
		returnAndExpect(25, Operation.ADD);
		returnAndExpect(50, Operation.ADD);

		returnAndExpect(51, Operation.SUBTRACT);
		returnAndExpect(75, Operation.SUBTRACT);
		returnAndExpect(100, Operation.SUBTRACT);

		returnAndExpect(101, Operation.MULTIPLY);
		returnAndExpect(125, Operation.MULTIPLY);
		returnAndExpect(150, Operation.MULTIPLY);

		returnAndExpect(151, Operation.DIVIDE);
		returnAndExpect(175, Operation.DIVIDE);
		returnAndExpect(200, Operation.DIVIDE);

		verify(gen, times(12)).randInt(anyInt(), anyInt());
	}
	
	@Test
	public void test_generateOperation_noMixed() {
		frequencies.put(Operation.ADD, 0);
		frequencies.put(Operation.SUBTRACT, 0);
		frequencies.put(Operation.MULTIPLY, 50);
		frequencies.put(Operation.DIVIDE, 0);
		frequencies.put(Operation.DIVIDE, 50);
		
		randomizer.setAndMapFrequencies(frequencies);
		
		assertEquals(Operation.MULTIPLY, randomizer.generateOperation(false));
	}

	protected <E> void returnAndExpect(int genInt, E expected) {
		Mockito.when(gen.randInt(anyInt(), anyInt())).thenReturn(genInt);
		assertEquals(expected, randomizer.generateOperation(true));
	}

	protected void genReturn(int returnValue) {
		Mockito.when(gen.randInt(anyInt(), anyInt())).thenReturn(returnValue);
	}

	// Use this method for below test
	@Test
	public void test_generateNumber() {
		returnAndExpectNumber(1, 1, 1, 9);
		returnAndExpectNumber(1, 2, 1, 99);
		returnAndExpectNumber(2, 2, 10, 99);
		returnAndExpectNumber(1, 3, 1, 999);
		returnAndExpectNumber(2, 3, 10, 999);
		returnAndExpectNumber(3, 3, 100, 999);
		/// ...
		returnAndExpectNumber(1, 9, 1, 999999999);
		returnAndExpectNumber(9, 9, 100000000, 999999999);
	}

	// TODO investigate number of invocations
	protected void returnAndExpectNumber(int minDigits, int maxDigits, int minBound, int maxBound) {
		// generateNumber(1,1):[1,9] -> (1,0) & (9,9)
		// generateNumber(1,2):[1,99] -> (1,0) & (9,9)
		// generateNumber(2,2):[10,99] -> (1,0) & (9,9)
		// generateNumber(3,3):[100,999] -> (1,0) & (9,9)

		BigDecimal left, mid, right;

		// test left boundary
		Mockito.when(gen.randInt(minDigits, maxDigits)).thenReturn(minDigits);
		Mockito.when(gen.randInt(1, 9)).thenReturn(1);
		Mockito.when(gen.randInt(0, 9)).thenReturn(0);
		left = randomizer.generateNumber(minDigits, maxDigits);
		assertEquals(minBound, left.intValue());

		// test right boundary
		Mockito.when(gen.randInt(minDigits, maxDigits)).thenReturn(maxDigits);
		Mockito.when(gen.randInt(1, 9)).thenReturn(9);
		Mockito.when(gen.randInt(0, 9)).thenReturn(9);
		right = randomizer.generateNumber(minDigits, maxDigits);
		assertEquals(maxBound, right.intValue());

		// test mid boundary
		Mockito.when(gen.randInt(minDigits, maxDigits)).thenReturn(minDigits);
		Mockito.when(gen.randInt(1, 9)).thenReturn(5);
		Mockito.when(gen.randInt(0, 9)).thenReturn(5);
		mid = randomizer.generateNumber(minDigits, maxDigits);
		assertTrue(String.format("Failed middle boundary test for min=%d and max=%d", minDigits, maxDigits),
				mid.doubleValue() > left.doubleValue() && mid.doubleValue() < right.doubleValue());

		Mockito.verify(gen, atLeast(6)).randInt(anyInt(), anyInt());
//		Mockito.verify(gen, atMost(maxDigits*3+7)).randInt(anyInt(), anyInt());
	}
	
	@Test
	public void test_generateOperandCount() {
		Mockito.when(gen.randInt(4, 10)).thenReturn(7);
		assertEquals(7, randomizer.generateOperandCount(4, 10));
	}

}
