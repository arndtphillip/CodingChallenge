package org.parndt.types;

import org.parndt.operations.Negatable;

/**
 * Data class for double input values
 */
public class DoubleInput implements TypedInput, Negatable {

    private double value;

    public DoubleInput(String value) throws IllegalArgumentException {
        this.value = Double.parseDouble(value);
    }

    /** {@inheritDoc} */
    @Override
    public String getStringValue() {
        return Double.toString(value);
    }

    @Override
    public void negate() {
        value = -value;
    }
}
