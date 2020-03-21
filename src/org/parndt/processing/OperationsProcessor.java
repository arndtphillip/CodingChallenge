package org.parndt.processing;

import org.parndt.io.Chunk;
import org.parndt.operations.Operation;
import org.parndt.types.InputType;
import org.parndt.types.TypedInput;

import java.util.List;
import java.util.concurrent.Callable;

public class OperationsProcessor implements Callable<Chunk> {

    private InputType inputType;
    private List<Operation> operations;
    private Chunk chunk;

    public OperationsProcessor(InputType inputType, List<Operation> operations, Chunk chunk) {
        this.inputType = inputType;
        this.operations = operations;
        this.chunk = chunk;
    }

    @Override
    public Chunk call() throws IllegalArgumentException {
        Chunk resultChunk = new Chunk(chunk.getChunkId());

        for (String line : chunk.getLines()) {
            // parse input
            TypedInput typedInput = inputType.convert(line);

            // apply operations
            for (Operation operation : operations) {
                operation.apply(typedInput);
            }

            // add to result
            resultChunk.addLine(typedInput.getStringValue());
        }

        return resultChunk;
    }
}
