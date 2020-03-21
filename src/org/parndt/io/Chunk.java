package org.parndt.io;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    private List<String> lines = new ArrayList<>();
    private int chunkId;

    public Chunk(int chunkId) {
        this.chunkId = chunkId;
    }

    public List<String> getLines() {
        return lines;
    }

    public int getChunkId() {
        return chunkId;
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public long length() {
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            sb.append(line);
        }

        return sb.length();
    }
}
