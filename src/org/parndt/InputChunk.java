package org.parndt;

import java.util.ArrayList;
import java.util.List;

public class InputChunk {
    private List<String> values = new ArrayList<>();
    private int position;

    public InputChunk(int position) {
        this.position = position;
    }

    public void addValue(String value) {
        values.add(value);
    }

    public List<String> getValues() {
        return values;
    }

    public int getPosition() {
        return position;
    }

    public long length() {
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append(value);
        }

        return sb.length();
    }
}
