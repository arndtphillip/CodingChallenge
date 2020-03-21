package org.parndt.arguments;

import org.parndt.operations.Operation;
import org.parndt.types.InputType;

import java.util.ArrayList;
import java.util.List;

public class Arguments {
    private String inputFile;
    private InputType inputType;
    private List<Operation> operations = new ArrayList<>();
    private int threads = 1;
    private String outputFile;

    public String getInputFile() {
        return inputFile;
    }

    public InputType getInputType() {
        return inputType;
    }

    public List<Operation> getOperations() {
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
