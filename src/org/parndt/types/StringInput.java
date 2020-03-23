package org.parndt.types;

import org.parndt.operations.Capitalizable;
import org.parndt.operations.Reversable;

/**
 * Data class for string input values.
 *
 * @author Philipp Arndt
 */
public class StringInput implements TypedInput, Capitalizable, Reversable {

    private String value;

    public StringInput(String value) {
        this.value = value;
    }


    /** {@inheritDoc} */
    @Override
    public String getStringValue() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public void capitalize() {
        value = value.toUpperCase();
    }

    /** {@inheritDoc} */
    @Override
    public void reverse() {
        value = new StringBuilder(value).reverse().toString();
    }
}
