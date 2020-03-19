package org.parndt.types;

import org.parndt.operations.Capitalizable;
import org.parndt.operations.Reversable;

public class StringInput implements TypedInput, Capitalizable, Reversable {

    private String value;

    public StringInput(String value) {
        this.value = value;
    }

    @Override
    public String getStringValue() {
        return value;
    }

    @Override
    public void capitalize() {
        value = value.toUpperCase();
    }

    @Override
    public void reverse() {
        value = new StringBuilder(value).reverse().toString();
    }
}
