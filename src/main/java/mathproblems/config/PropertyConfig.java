package mathproblems.config;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class PropertyConfig {

	private static PropertyConfig propertyConfig = null;
	private Configurations configurationHelper = null;
	private PropertiesConfiguration configuration = null;

	public static final PropertyConfig instance() {
		if (propertyConfig == null)
			propertyConfig = new PropertyConfig();
		return propertyConfig;
	}

	private PropertyConfig() {
	}
	
	public Object getProperty(Property p){
		if(this.configuration == null) {
			throw new UnsupportedOperationException("Properties have not been configured yet.");
		}
		return this.configuration.getProperty(p.getName());	
	}
	
	public void setProperty(Property property, Object value) {
		this.configuration.setProperty(property.getName(), value);
	}

	public void configure(String file) throws FileNotFoundException, ConfigurationException {
		File configFile = new File(file);
		if (!configFile.exists())
			throw new FileNotFoundException(String.format("%s does not exist.", file));
		try {
			this.configurationHelper = new Configurations();
			this.configuration = configurationHelper.properties(configFile);
			this.configuration.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
			
		} catch (ConfigurationException e) {
			throw new ConfigurationException(String.format("Cannot configure properties from %s", file));
		}
	}
}
