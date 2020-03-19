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

    public LineReader(String inputFile) {
        this.inputFile = inputFile;
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

    public List<String> readAsChunks(int threads) throws IOException {
        List<String> chunks = new ArrayList<>();

        File file = new File(inputFile);
        long chunkSize = getChunkSize(file, threads);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                statistics.updateStatisticsWithLine(line);

                if (sb.length() > chunkSize) {
                    chunks.add(sb.toString());
                    sb = new StringBuilder();
                }
            }

            chunks.add(sb.toString());
        }

        return chunks;
    }

    public long getChunkSize(File file, int threads) {
        return file.length() / threads;
    }
}
