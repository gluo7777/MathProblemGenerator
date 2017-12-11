package mathproblems.generator.gui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mathproblems.config.Property;
import mathproblems.config.PropertyConfig;
import mathproblems.generator.Operation;
import mathproblems.generator.builders.SimpleProblemBuilder;
import mathproblems.generator.calculator.SimpleCalculator;
import mathproblems.generator.problem.Problem;
import mathproblems.generator.randomizer.NumberGenerator;
import mathproblems.generator.randomizer.SimpleRandomizer;

public class CommandLineInterface implements UserInterface {

	private static final Logger log = LogManager.getFormatterLogger(CommandLineInterface.class);
	private static final String DEFAULT_CONFIG = "generator.properties";
	private PropertyConfig config;
	private Scanner scan;
	private final DecimalFormat decimalFormat = new DecimalFormat("###.##");
	private ProblemWrapper wrapper;

	public final void setConfig(PropertyConfig config) {
		this.config = config;
	}

	public final void parseArgs(String[] args) {
		// TODO parse arguments
	}

	public final void setSimpleProblemBuilder(SimpleProblemBuilder simpleProblemBuilder) {
		this.wrapper.setSimpleProblemBuilder(simpleProblemBuilder);
	}

	public CommandLineInterface() {
		this.wrapper = new ProblemWrapper();
		this.config = PropertyConfig.instance();
	}

	@Override
	public void configure() {
		// Ask if user wants to override default settings

		String propertyFile = Thread.currentThread().getContextClassLoader().getResource(DEFAULT_CONFIG).getFile();
		try {
			this.config.configure(propertyFile);
			log.info("Property file location: %s", propertyFile);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}

	@Override
	public void init() {
		// Ask if user wants to override default settings
		// Parse arguments to see which builder to use
		try {
			int scale = 2;
			RoundingMode mode = RoundingMode.HALF_UP;
			SimpleCalculator calculator = new SimpleCalculator();
			calculator.setScale(2);
			calculator.setRoundingMode(mode);
			// generate a list of problems
			List<Problem<BigDecimal>> list = build(calculator);

			// display problems one at a time to user
			// get user input
			this.scan = new Scanner(System.in);
			while (true) {
				display(this.scan, scale, calculator, list);
				System.out.print("Play again? (y/n)");
				if(this.scan.next().equals("n"))
					break;
				list = build(calculator);
			}
			System.out.println("Thanks for playing!");

		} catch (Exception e) {
			log.error(e.getMessage());
			System.err.println(e.getMessage());
		}
	}

	private List<Problem<BigDecimal>> build(SimpleCalculator calculator) {
		PropertiesConfiguration configuration = this.config.getConfiguration();
		List<Problem<BigDecimal>> list = this.wrapper.getSimpleProblemBuilder().setCalculator(calculator)
				.setRandomizer(new SimpleRandomizer(new NumberGenerator()))
				.setNumberOfProblems(configuration.getInt(Property.PROBLEM_COUNT.getName()))
				.setMixedMode(configuration.getBoolean(Property.MIXED_MODE.getName()))
				.setNumberOfDigits(configuration.getInt(Property.MIN_DIGIT.getName()),
						configuration.getInt(Property.MAX_DIGIT.getName()))
				.setNumberOfOperands(configuration.getInt(Property.MIN_OPERAND.getName()),
						configuration.getInt(Property.MAX_OPERAND.getName()))
				.setSolveProblems(true).setFrequencies(this.config.getFrequencyMap()).buildProblemSet();
		return list;
	}

	private void display(Scanner scan, int scale, SimpleCalculator calculator, List<Problem<BigDecimal>> list) {
		BigDecimal answer;
		Problem<BigDecimal> current;
		int correct = 0;
		for (int i = 1; i <= list.size(); i++) {
			answer = null;
			while (answer == null) {
				try {
					// user enters answer
					current = list.get(i - 1);
					System.out.printf("Problem %d:\t%s = ", i, getProblemDisplay(current));
					answer = scan.nextBigDecimal();
					current.setAnswer(answer);

					// checks answer
					System.out.printf("Correct answer was %s.\t\tYou entered %s.%n", formatDecimal(current.getResult()),
							formatDecimal(current.getAnswer()));
					correct = calculator.isAnsweredCorrect(current, Math.pow(10, -1 * scale)) ? correct + 1 : correct;
				} catch (InputMismatchException e) {
					System.out.println("Try again. Please enter a number.");
					scan.nextLine(); // advances pass erroneous input
				}
			}
		}
		System.out.printf("That was all of them.\nYour score is %d out of %d%n", correct, list.size());
	}

	private String formatDecimal(BigDecimal d) {
		return decimalFormat.format(d.doubleValue());
	}

	private String getProblemDisplay(Problem<?> p) {
		StringBuilder display = new StringBuilder();
		Object[] operands = p.getOperands();
		Operation[] operations = p.getOperations();
		for (int i = 0; i < operands.length - 1; i++) {
			display.append(operands[i]).append(" ").append(operations[i].getValue()).append(" ");
		}
		display.append(operands[operands.length - 1]);
		return display.toString();
	}

	@Override
	public void close() {
		if (this.scan != null)
			this.scan.close();
	}

}
