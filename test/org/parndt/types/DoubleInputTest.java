package org.parndt.types;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleInputTest {

    @Test
    public void getStringValue() {
        DoubleInput input = new DoubleInput("42.3");
        Assert.assertEquals("42.3", input.getStringValue());
    }

    @Test
    public void negate() {
        DoubleInput input = new DoubleInput("42.3");
        input.negate();
        Assert.assertEquals("-42.3", input.getStringValue());
    }
}