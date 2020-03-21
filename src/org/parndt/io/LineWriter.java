package org.parndt.io;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LineWriter {

    private String outputFile;
    private List<Future<Chunk>> futures;

    public LineWriter(String outputFile, List<Future<Chunk>> futures) {
        this.outputFile = outputFile;
        this.futures = futures;
    }

    public void write() throws IOException, ExecutionException, InterruptedException {
        File file = new File(outputFile);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Future<Chunk> future : futures) {
                // TODO: order of chunks
                Chunk chunk = future.get();

                for (String line : chunk.getLines()) {
                    writer.append(line);
                    writer.newLine();
                    // TODO: remove last new line
                }
            }
        }
    }
}
