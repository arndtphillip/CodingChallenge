package org.parndt.types;

import org.parndt.operations.Negatable;

public class DoubleInput implements TypedInput, Negatable {

    private Double value;

    public DoubleInput(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public String getStringValue() {
        return value.toString();
    }

    @Override
    public void negate() {
        value = -value;
    }
}
