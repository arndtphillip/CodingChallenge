package org.parndt;

import org.parndt.arguments.Arguments;
import org.parndt.io.LineReader;
import org.parndt.io.LineWriter;
import org.parndt.types.TypedInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Main class.
 * 
 * @author KNIME GmbH
 */
public class Main {

	public static void main(String[] args) throws IOException {
		Arguments arguments = new Arguments(args);
		List<InputChunk> rawData = new LineReader(arguments.getInputFile(), arguments.getThreads()).readAsChunks();

		List<Worker> workers = new ArrayList<>();

		for (InputChunk chunk : rawData) {
			Worker worker = new Worker(arguments.getInputType(), arguments.getOperations(), chunk);
			worker.start();
			workers.add(worker);
		}

		List<String> processedData = new ArrayList<>();

		try {
			for (Worker worker : workers) {
				worker.join();
				processedData.addAll(worker.getProcessedData());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new LineWriter(arguments.getOutputFile(), processedData).write();
		
		// DO NOT CHANGE THE FOLLOWING LINES OF CODE
		System.out.println(String.format("Processed %d lines (%d of which were unique)", //
				Statistics.getInstance().getNoOfLinesRead(), //
				Statistics.getInstance().getNoOfUniqueLines()));
	}

}
