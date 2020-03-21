package org.parndt;

import org.parndt.types.InputType;
import org.parndt.types.TypedInput;

import java.util.LinkedList;
import java.util.List;

public class TypeParser {
    private InputChunk rawData;
    private InputType inputType;

    public TypeParser(InputType inputType, InputChunk rawData) {
        this.rawData = rawData;
        this.inputType = inputType;
    }

    public List<TypedInput> parse() {
        List<TypedInput> result = new LinkedList<>();

        for (String element : rawData.getValues()) {
            result.add(inputType.convert(element));
        }

        return result;
    }
}
