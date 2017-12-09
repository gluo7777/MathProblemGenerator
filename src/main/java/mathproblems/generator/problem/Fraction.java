package mathproblems.generator.problem;

import java.math.BigDecimal;

public class Fraction {
	private long numerator;
	private long denominator;

	public Fraction(long numerator, long denominator) {
		super();
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public final long getNumerator() {
		return numerator;
	}

	public final void setNumerator(long numerator) {
		this.numerator = numerator;
	}

	public final long getDenominator() {
		return denominator;
	}

	public final void setDenominator(long denominator) {
		this.denominator = denominator;
	}

	public final double getValue() {
		return BigDecimal.valueOf(this.numerator / this.denominator).doubleValue();
	}

	public void simplify() {
		long gcd = this.findGreatestCommonDivisor();
		if (gcd != 1) {
			this.numerator /= gcd;
			this.denominator /= gcd;
		}
	}

	private long findGreatestCommonDivisor() {
		long gcd = 1;
		for (int i = 2; i < this.numerator && i < this.denominator; i++)
			if (this.numerator % i == 0 && this.denominator % i == 0)
				gcd = i;
		return gcd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (denominator ^ (denominator >>> 32));
		result = prime * result + (int) (numerator ^ (numerator >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		if (denominator != other.denominator)
			return false;
		if (numerator != other.numerator)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fraction [numerator=" + numerator + ", denominator=" + denominator + "]";
	}

}
