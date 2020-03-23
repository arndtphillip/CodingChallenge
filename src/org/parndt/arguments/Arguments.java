package org.parndt.arguments;

import org.parndt.operations.Operation;
import org.parndt.types.InputType;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class for the command line arguments of the application.
 *
 * @author Philipp Arndt
 */
public class Arguments {

    private String inputFile;
    private InputType inputType;
    private List<Operation> operations = new ArrayList<>();
    private int threads;
    private String outputFile;

    /**
     *
     * @return The input file to read from.
     */
    public String getInputFile() {
        return inputFile;
    }

    /**
     *
     * @return The {@link InputType}, which should be used when parsing the input lines.
     */
    public InputType getInputType() {
        return inputType;
    }

    /**
     *
     * @return A list of {@link Operation} that should be applied on the input lines.
     */
    public List<Operation> getOperations() {
        return operations;
    }

    /**
     *
     * @return The number of threads to use for parsing and processing.
     */
    public int getThreads() {
        return threads;
    }

    /**
     *
     * @return The output file, to which the result should be written.
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     * Creates a new {@link Arguments} object from command line arguments. The parsing is achieved through array indices,
     * so the command line arguments should always be in the same order.
     *
     * @param args The command line arguments.
     */
    public Arguments(String[] args) {
        inputFile = args[1];
        inputType = InputType.valueOf(args[3].toUpperCase());

        String[] operationsString = args[5].split(",");
        for (String operation : operationsString) {
            operations.add(Operation.valueOf(operation.toUpperCase()));
        }

        threads = Integer.parseInt(args[7]);

        if (args.length > 9) {
            outputFile = args[9];
        }
    }

    /**
     *
     * @return True if the output should be written to a file, false if it should be written to System.out
     */
    public boolean writeToFile() {
        return outputFile != null;
    }
}
