package org.parndt;

import org.parndt.operations.Operation;
import org.parndt.types.InputType;
import org.parndt.types.TypedInput;

import java.util.List;

public class Worker extends Thread {
    private InputType type;
    private List<Operation> operations;
    private InputChunk data;

    private List<String> processedData;

    public Worker(InputType type, List<Operation> operations, InputChunk data) {
        this.type = type;
        this.operations = operations;
        this.data = data;
    }

    @Override
    public void run() {
        List<TypedInput> parsedData = new TypeParser(type, data).parse();
        processedData = new OperationsPipeline(operations, parsedData).process();
    }

    public List<String> getProcessedData() {
        return processedData;
    }
}
