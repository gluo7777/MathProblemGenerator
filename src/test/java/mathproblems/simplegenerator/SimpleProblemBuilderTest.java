package mathproblems.simplegenerator;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mathproblems.generator.Operation;
import mathproblems.simplegenerator.randomizer.SimpleRandomizer;

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
		frequencies.put(Operation.ADD, 1);
		SimpleProblemSet set = builder.buildProblemSet();
		
	}

	@Test
	public void testNotSetRandomizer() {
		fail("Not yet implemented");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetCount_zero() {
		builder.setCount(0);
		builder.buildProblemSet();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCount_negative() {
		builder.setCount(-1);
		builder.buildProblemSet();
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testSetFrequencies_empty() {
		Mockito.when(frequencies.size()).thenReturn(0);
		builder.setFrequencies(frequencies);
		
	}

	@Test
	public void testSetNumberOfOperands() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNumberOfDigits() {
		fail("Not yet implemented");
	}

}
