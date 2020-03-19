package org.parndt.types;

import org.parndt.operations.Negatable;
import org.parndt.operations.Reversable;

public class IntInput implements TypedInput, Negatable, Reversable {

    private Integer value;

    public IntInput(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public String getStringValue() {
        return value.toString();
    }

    @Override
    public void negate() {
        value = -value;
    }

    @Override
    public void reverse() {
        StringBuilder sb = new StringBuilder(value.toString());
        value = Integer.parseInt(sb.reverse().toString());
    }
}
