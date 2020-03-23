package org.parndt.io;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LineWriter {

    private String outputFile;
    private List<Chunk> chunks;

    public LineWriter(String outputFile, List<Chunk> chunks) {
        this.outputFile = outputFile;
        this.chunks = chunks;
    }

    public void write() throws IOException {
        File file = new File(outputFile);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < chunks.size(); i++) {
                List<String> lines = chunks.get(i).getLines();

                for (int j = 0; j < lines.size(); j++) {
                    writer.append(lines.get(j));

                    // don't append a new new line if it is the last line in the last chunk
                    if (i < chunks.size() - 1 || j < lines.size() - 1)
                        writer.newLine();
                }
            }
        }
    }
}
