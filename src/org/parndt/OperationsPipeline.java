package org.parndt;

import org.parndt.operations.Operation;
import org.parndt.types.TypedInput;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;

public class OperationsPipeline {
    public Set<Operation> operations;
    public List<TypedInput> data;

    public OperationsPipeline(Set<Operation> operations, List<TypedInput> data) {
        this.operations = operations;
        this.data = data;
    }

    public List<String> process() {
        List<String> result = new LinkedList<>();

        for (TypedInput typedInput : data) {

            for (Operation operation : operations) {
                operation.apply(typedInput);
            }

            result.add(typedInput.getStringValue());
        }

        return result;
    }
}
