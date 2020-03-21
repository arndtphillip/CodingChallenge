package org.parndt.types;

import org.junit.Assert;
import org.junit.Test;

public class IntInputTest {

    @Test
    public void toResultString() {
        IntInput input = new IntInput("42");
        Assert.assertEquals("42", input.getStringValue());
    }

    @Test
    public void negate() {
        IntInput input = new IntInput("42");
        input.negate();
        Assert.assertEquals("-42", input.getStringValue());
    }

    @Test
    public void reverse() {
        IntInput input = new IntInput("1010");
        input.reverse();
        Assert.assertEquals("101", input.getStringValue());
    }

    @Test
    public void reverseNegative() {
        IntInput input = new IntInput("-42");
        input.reverse();
        Assert.assertEquals("-24", input.getStringValue());
    }

    @Test
    public void negateAndReverse() {
        IntInput input = new IntInput("1010");
        input.negate();
        input.reverse();
        Assert.assertEquals("-101", input.getStringValue());
    }

    @Test
    public void reverseAndNegate() {
        IntInput input = new IntInput("1010");
        input.reverse();
        input.negate();
        Assert.assertEquals("-101", input.getStringValue());
    }
}