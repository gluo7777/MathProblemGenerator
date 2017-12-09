package mathproblems.generator;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
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
		assertEquals(6, Integer.parseInt((String)config.getProperty(Property.MAX_OPERAND)));
	}

	@Test
	public void test_setProperty() {
		config.setProperty(Property.MAX_DIGIT, 25);
		assertEquals(25, (int) config.getProperty(Property.MAX_DIGIT));
	}

}
