package mathproblems.util;

public class RandomGenerator {
	public int randInt(int minDigit, int maxDigit) {
		int index = (int) (Math.random() * (maxDigit - minDigit + 1)) + minDigit;
		return index;
	}
}
