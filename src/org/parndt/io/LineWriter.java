package org.parndt.io;

import java.io.*;
import java.util.Collection;

public class LineWriter {
    private String outputFile;
    private Collection<String> resultLines;

    public LineWriter(String outputFile, Collection<String> resultLines) {
        this.outputFile = outputFile;
        this.resultLines = resultLines;
    }

    public void write() throws IOException {
        File file = new File(outputFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String line : resultLines) {
            writer.append(line);
            writer.newLine();
        }

        writer.close();
    }
}
