package mathproblems.simplegenerator.randomizer;

public class Bound<T> {
	public final T value;
	public final int upper, lower;

	Bound(T value, int lower, int upper) {
		this.value = value;
		this.upper = upper;
		this.lower = lower;
	}

}
