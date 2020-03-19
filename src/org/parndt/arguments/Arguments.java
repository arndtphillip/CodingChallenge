package org.parndt.arguments;

import org.parndt.operations.Operation;
import org.parndt.types.InputType;

import java.util.HashSet;
import java.util.Set;

public class Arguments {
    private String inputFile;
    private InputType inputType;
    private Set<Operation> operations = new HashSet<>();
    private int threads = 1;
    private String outputFile;

    public String getInputFile() {
        return inputFile;
    }

    public InputType getInputType() {
        return inputType;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public int getThreads() {
        return threads;
    }

    public String getOutputFile() {
        return outputFile;
    }

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
        } else {
            outputFile = "test1.txt";
            System.out.println("Using output file " + outputFile);
        }
    }
}
