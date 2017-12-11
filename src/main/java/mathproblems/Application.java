package mathproblems;

import mathproblems.generator.builders.SimpleProblemBuilder;
import mathproblems.generator.gui.CommandLineInterface;

public class Application {
	public static void main(String[] args) {
		CommandLineInterface cmd = new CommandLineInterface();
		cmd.setSimpleProblemBuilder(new SimpleProblemBuilder());
		cmd.parseArgs(args);
		cmd.run();
	}
}
