package mathproblems.generator;

public interface UserInterface {
	
	/**
	 * Starts the application
	 * Must override configure, init, and close
	 */
	public default void run() {
		this.configure();
		this.init();
		this.close();
	}
	
	/**
	 * Configure properties from a properties file
	 */
	void configure();
	
	/**
	 * Set up the interface
	 */
	void init();
	
	/**
	 * Perform tasks necessary to safely terminate application
	 */
	void close();
}
