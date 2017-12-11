package mathproblems.generator;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Before;
import org.junit.Test;

import mathproblems.config.Property;
import mathproblems.config.PropertyConfig;

public class PropertyConfigTest {

	private static final String RESOURCES = "src/test/resources/";
	private static final String PROPERTIES = "generator.properties";
	private PropertyConfig config;
	
	@Before
	public void setUp() {
		try {
			config = PropertyConfig.instance();
			config.configure(RESOURCES + PROPERTIES);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_getProperty() {
		assertEquals(6, config.getConfiguration().getInt(Property.MAX_OPERAND.getName()));
	}

	@Test
	public void test_setProperty() {
		config.getConfiguration().setProperty(Property.MAX_DIGIT.getName(), 25);
		assertEquals(25, config.getConfiguration().getInt(Property.MAX_DIGIT.getName()));
	}

	@Test
	public void test_listOfFrequencies() throws ConfigurationException, FileNotFoundException {
		assertEquals(4, config.getFrequencyMap().size());
		assertEquals(20, (int) config.getFrequencyMap().get(Operation.ADD));
		assertEquals(30, (int) config.getFrequencyMap().get(Operation.SUBTRACT));
		assertEquals(15, (int) config.getFrequencyMap().get(Operation.MULTIPLY));
		assertEquals(0, (int) config.getFrequencyMap().get(Operation.DIVIDE));
		
		config.setFrequency(Operation.ADD, 35);
		config.setFrequency(Operation.DIVIDE, 20);
		assertEquals(4, config.getFrequencyMap().size());
		assertEquals(35, (int) config.getFrequencyMap().get(Operation.ADD));
		assertEquals(30, (int) config.getFrequencyMap().get(Operation.SUBTRACT));
		assertEquals(15, (int) config.getFrequencyMap().get(Operation.MULTIPLY));
		assertEquals(20, (int) config.getFrequencyMap().get(Operation.DIVIDE));
		
		config.setFrequency(Operation.DIVIDE, 35);
		config.save();
		config.configure(RESOURCES+PROPERTIES);
		assertEquals(35, (int) config.getFrequencyMap().get(Operation.DIVIDE));
		config.setFrequency(Operation.DIVIDE, 0);
		config.save();
	}

}
