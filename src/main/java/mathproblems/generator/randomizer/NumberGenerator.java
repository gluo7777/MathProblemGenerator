package mathproblems.generator.randomizer;

public class NumberGenerator {
	public int randInt(int minDigit, int maxDigit) {
		return (int) (Math.random() * (maxDigit - minDigit + 1)) + minDigit;
	}
}
