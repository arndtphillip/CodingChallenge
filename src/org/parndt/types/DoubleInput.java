package org.parndt.types;

import org.parndt.operations.Negatable;

public class DoubleInput implements TypedInput, Negatable {

    private double value;

    public DoubleInput(String value) throws IllegalArgumentException {
        this.value = Double.parseDouble(value);
    }

    @Override
    public String getStringValue() {
        return Double.toString(value);
    }

    @Override
    public void negate() {
        value = -value;
    }
}
