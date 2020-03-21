package org.parndt.arguments;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.parndt.operations.Operation;
import org.parndt.types.InputType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArgumentsTest {

    private Arguments arguments;

    @Before
    public void setUp() throws Exception {
        String[] args = new String[] {
                "--input", "test.txt",
                "--inputtype", "string",
                "--operations", "neg,reverse",
                "--threads", "4",
                "--output", "result.txt"
        };

        this.arguments = new Arguments(args);
    }

    @Test
    public void getInputFile() {
        Assert.assertEquals("test.txt", arguments.getInputFile());
    }

    @Test
    public void getInputType() {
        Assert.assertEquals(InputType.STRING, arguments.getInputType());
    }

    @Test
    public void getOperations() {
        List<Operation> expected = Arrays.asList(Operation.NEG, Operation.REVERSE);
        assertThat(expected, is(arguments.getOperations()));
    }

    @Test
    public void getThreads() {
        Assert.assertEquals(4, arguments.getThreads());
    }

    @Test
    public void getOutputFile() {
        Assert.assertEquals("result.txt", arguments.getOutputFile());
    }
}