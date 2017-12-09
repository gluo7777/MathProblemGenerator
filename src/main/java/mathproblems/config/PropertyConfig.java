package mathproblems.config;

public class PropertyConfig {
	
	private static PropertyConfig config = null;
	
	// Implement getters and setters for properties
	public static final PropertyConfig getConfig() {
		if(config == null)
			config = new PropertyConfig();
		return config;
	}
	
	private PropertyConfig() {
	}

	public void configure() {

	}

	public void configure(String file) {

	}
}
