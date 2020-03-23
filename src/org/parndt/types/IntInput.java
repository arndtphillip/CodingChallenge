package org.parndt.types;

import org.parndt.operations.Negatable;
import org.parndt.operations.Reversable;

/**
 * Data class int input values.
 *
 * @author Philipp Arndt
 */
public class IntInput implements TypedInput, Negatable, Reversable {

    private int value;

    public IntInput(String value) throws IllegalArgumentException {
        this.value = Integer.parseInt(value);
    }

    /** {@inheritDoc} */
    @Override
    public String getStringValue() {
        return Integer.toString(value);
    }

    /** {@inheritDoc} */
    @Override
    public void negate() {
        value = -value;
    }

    /** {@inheritDoc} */
    @Override
    public void reverse() {
        int absValue = Math.abs(value);
        StringBuilder sb = new StringBuilder(Integer.toString(absValue));
        absValue = Integer.parseInt(sb.reverse().toString());

        // handle negative values
        boolean negative = value < 0;

        if (negative) {
            value = -absValue;
        } else {
            value = absValue;
        }
    }
}
