package org.parndt.types;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringInputTest {

    @Test
    public void getStringValue() {
        StringInput input = new StringInput("Hello world");
        Assert.assertEquals("Hello world", input.getStringValue());
    }

    @Test
    public void capitalize() {
        StringInput input = new StringInput("Hello world");
        input.capitalize();
        Assert.assertEquals("HELLO WORLD", input.getStringValue());
    }

    @Test
    public void capitalizeNumber() {
        StringInput input = new StringInput("42");
        input.capitalize();
        Assert.assertEquals("42", input.getStringValue());
    }

    @Test
    public void reverse() {
        StringInput input = new StringInput("Hello world");
        input.reverse();
        Assert.assertEquals("dlrow olleH", input.getStringValue());
    }

    @Test
    public void capitalizeAndReverse() {
        StringInput input = new StringInput("Hello world");
        input.capitalize();
        input.reverse();
        Assert.assertEquals("DLROW OLLEH", input.getStringValue());
    }
}