package org.parndt.io;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LineWriter {

    private List<Chunk> chunks;

    public LineWriter(List<Chunk> chunks) {
        this.chunks = chunks;
    }

    public void writeToFile(String outputFile) throws IOException {
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

    public void writeToSystemOut() {
        System.out.println("Processed data: ");

        for (Chunk chunk : chunks) {
            for (String line : chunk.getLines()) {
                System.out.println(line);
            }
        }

        System.out.println();
    }
}
