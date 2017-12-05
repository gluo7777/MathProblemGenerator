package mathproblems.simplegenerator.randomizer;

public class Bound<T> {
	public final T value;
	public final int upper, lower;

	Bound(T value, int upper, int lower) {
		this.value = value;
		this.upper = upper;
		this.lower = lower;
	}

}
