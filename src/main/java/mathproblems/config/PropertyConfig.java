package mathproblems.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import mathproblems.generator.Operation;

public class PropertyConfig {

	private static PropertyConfig propertyConfig = null;
	private Configurations configurationHelper = null;
	private PropertiesConfiguration configuration = null;
	private FileBasedConfigurationBuilder<PropertiesConfiguration> builder;

	private Map<Operation, Integer> map = null;

	public static final PropertyConfig instance() {
		if (propertyConfig == null)
			propertyConfig = new PropertyConfig();
		return propertyConfig;
	}

	private PropertyConfig() {
		this.configurationHelper = new Configurations();
	}

	public final PropertiesConfiguration getConfiguration() {
		return configuration;
	}

	public Map<Operation, Integer> getFrequencyMap() {
		if (map == null) {
			this.map = new HashMap<>();
			for (Operation op : Operation.getOperations())
				this.map.put(op, this.configuration
						.getInt(Property.FREQUENCY_ROOT.getName() + "." + op.name().toLowerCase(), 0));
		}
		return this.map;
	}
	
	public void save() throws ConfigurationException {
		this.builder.save();
	}
	
	public void setFrequency(Operation op, int value) {
		if(this.map == null)
			getFrequencyMap();
		this.map.put(op, value);
	}

	public void setProperty(Property property, Object value) {
		this.configuration.setProperty(property.getName(), value);
	}

	public void configure(String file) throws FileNotFoundException, ConfigurationException {
		File configFile = new File(file);
		if (!configFile.exists())
			throw new FileNotFoundException(String.format("%s does not exist.", file));
		try {
			this.builder = this.configurationHelper.propertiesBuilder(file);
			this.configuration = this.builder.getConfiguration();

		} catch (ConfigurationException e) {
			throw new ConfigurationException(String.format("Cannot configure properties from %s", file));
		}
	}
}
