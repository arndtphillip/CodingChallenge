package org.parndt.io;

import org.parndt.InputChunk;
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

    public List<String> read() throws IOException {
        List<String> resultLines = new ArrayList<>();

        File file = new File(inputFile);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            resultLines.add(line);
            statistics.updateStatisticsWithLine(line);
        }

        reader.close();

        return resultLines;
    }

    public List<InputChunk> readAsChunks() throws IOException {
        List<InputChunk> chunks = new ArrayList<>();

        File file = new File(inputFile);
        long chunkSize = getChunkSize(file, threads);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int chunkIndex = 0;
            InputChunk chunk = new InputChunk(chunkIndex);
            String line;
            while ((line = reader.readLine()) != null) {
                chunk.addValue(line);
                statistics.updateStatisticsWithLine(line);

                if (chunk.length() > chunkSize) {
                    chunks.add(chunk);
                    chunk = new InputChunk(chunkIndex++);
                }
            }

            // add the last one
            chunks.add(chunk);
        }

        return chunks;
    }

    public long getChunkSize(File file, int threads) {
        return file.length() / threads;
    }
}
