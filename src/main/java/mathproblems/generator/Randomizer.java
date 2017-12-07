package mathproblems.generator;

import java.util.Map;

/**
 * classes that implement this interface must define methods that allow for
 * generating random values
 * 
 * @author wums
 *
 * @param <T>
 */
public interface Randomizer<T extends Number> {

	/**
	 * Parses the map and builds a random generator based on the supplied
	 * probabilities for each Operation. If all-zero probabilites, then each
	 * operation will have equal likelihood.
	 * 
	 * @param frequencies
	 * 
	 * @throws IllegalArgumentException
	 *             - if map is empty or has any negative probabilities
	 */
	void setAndMapFrequencies(Map<Operation, Integer> frequencies);

	/**
	 * 
	 * @param mixedMode
	 *            - whether or not operations can be mixed
	 * @return a randomly generated operation if mixedMode = true, otherwise,
	 *         returns the first operation in frequency with a non-zero value
	 */
	Operation generateOperation(boolean mixedMode);

	/**
	 * generates a number with a digit between min and max digit (inclusive)
	 * 
	 * @param minDigit
	 *            - lower bound
	 * @param maxDigit
	 *            - upper bound
	 * @return
	 */
	T generateNumber(int minDigit, int maxDigit);

	/**
	 * generates a number with a digit between min and max count (inclusive)
	 * 
	 * @param minCount
	 *            - lower bound
	 * @param maxCount
	 *            - upper bound
	 * @return
	 */
	int generateOperandCount(int minCount, int maxCount);
}
