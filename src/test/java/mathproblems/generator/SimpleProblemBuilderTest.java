package mathproblems.generator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mathproblems.generator.builders.SimpleProblemBuilder;
import mathproblems.generator.calculator.SimpleCalculator;
import mathproblems.generator.problem.Problem;
import mathproblems.generator.randomizer.SimpleRandomizer;

public class SimpleProblemBuilderTest {
	
	private SimpleProblemBuilder builder;
	
	@Mock
	private SimpleRandomizer randomizer;
	@Mock
	Map<Operation, Integer> frequencies;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		builder = new SimpleProblemBuilder();
		builder.setFrequencies(frequencies);
	}
	
	@Test
	public void testBuildProblemSet() {
		frequencies = new HashMap<>();
		frequencies.put(Operation.ADD, 25);
		frequencies.put(Operation.DIVIDE, 25);
		frequencies.put(Operation.MULTIPLY, 25);
		frequencies.put(Operation.SUBTRACT, 25);
		randomizer = new SimpleRandomizer();
		List<Problem<BigDecimal>> set = builder.setFrequencies(frequencies)
			.setRandomizer(randomizer)
			.setNumberOfDigits(1, 10)
			.setNumberOfProblems(20)
			.setNumberOfOperands(2, 10)
			.setMixedMode(true)
			.setSolveProblems(true)
			.buildProblemSet();
		
		SimpleCalculator calculator = new SimpleCalculator();
		
		for(Problem<BigDecimal> p : set) {
			BigDecimal currentResult = p.getResult();
			calculator.calculate(p);
			assertEquals(p.getResult(), currentResult);
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetnumberOfProblems_zero() {
		builder.setNumberOfProblems(0);
		builder.buildProblemSet();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetnumberOfProblems_negative() {
		builder.setNumberOfProblems(-1);
		builder.buildProblemSet();
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testSetFrequencies_empty() {
		Mockito.when(frequencies.size()).thenReturn(0);
		builder.setFrequencies(frequencies);
		builder.buildProblemSet();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetFrequencies_negative() {
		frequencies = new HashMap<>();
		frequencies.put(Operation.ADD, -1);
		builder.setFrequencies(frequencies);
		builder.buildProblemSet();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetFrequencies_allZero() {
		frequencies = new HashMap<>();
		frequencies.put(Operation.ADD, 0);
		frequencies.put(Operation.SUBTRACT, 0);
		builder.setFrequencies(frequencies);
		builder.buildProblemSet();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetFrequencies_notSet() {
		builder.setFrequencies(null);
		builder.buildProblemSet();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetNumberOfOperands_lessThan2() {
		builder.setNumberOfOperands(1, 2);
		builder.buildProblemSet();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetNumberOfOperands_maxLessMin() {
		builder.setNumberOfOperands(3, 2);
		builder.buildProblemSet();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNumberOfDigits_lessThan1() {
		builder.setNumberOfDigits(0, 1);
		builder.buildProblemSet();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNumberOfDigits_maxLessMin() {
		builder.setNumberOfDigits(2, 1);
		builder.buildProblemSet();
	}

}
