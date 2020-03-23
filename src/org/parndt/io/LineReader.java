package org.parndt.io;

import org.parndt.Statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader {

    private String inputFile;
    private int threads;
    private Statistics statistics;

    public LineReader(String inputFile, int threads) {
        this.inputFile = inputFile;
        this.threads = threads;
        this.statistics = Statistics.getInstance();
    }

    public List<Chunk> readAsChunks() throws IOException {
        List<Chunk> chunks = new ArrayList<>();

        File file = new File(inputFile);
        long chunkSize = getChunkSize(file);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int chunkId = 0;
            Chunk chunk = new Chunk(chunkId);
            String line;

            while ((line = reader.readLine()) != null) {
                chunk.addLine(line);

                statistics.updateStatisticsWithLine(line);

                if (chunk.length() > chunkSize) {
                    chunks.add(chunk);
                    chunk = new Chunk(++chunkId);
                }
            }

            // add the last one
            chunks.add(chunk);
        }

        return chunks;
    }

    private long getChunkSize(File file) {
        return file.length() / threads;
    }
}
