package mathproblems.generator;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum Operation {
	ADD("+"), SUBTRACT("-"), MULTIPLY("x"), DIVIDE("/");

	private final String value;

	private static final Set<Operation> operations = Collections.unmodifiableSet(EnumSet.allOf(Operation.class));

	private Operation(String value) {
		this.value = value;
	}

	public final String getValue() {
		return value;
	}

	public static final Set<Operation> getOperations() {
		return operations;
	}

}
