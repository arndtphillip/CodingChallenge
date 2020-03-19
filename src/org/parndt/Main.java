package org.parndt;

import org.parndt.arguments.Arguments;
import org.parndt.io.LineReader;
import org.parndt.io.LineWriter;
import org.parndt.types.TypedInput;

import java.io.IOException;
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
		List<String> rawData = new LineReader(arguments.getInputFile()).readAsChunks(1);
		List<TypedInput> typedData = new TypeParser(arguments.getInputType(), rawData).parse();
		List<String> processedData = new OperationsPipeline(arguments.getOperations(), typedData).process();
		new LineWriter(arguments.getOutputFile(), processedData).write();
		
		// DO NOT CHANGE THE FOLLOWING LINES OF CODE
		System.out.println(String.format("Processed %d lines (%d of which were unique)", //
				Statistics.getInstance().getNoOfLinesRead(), //
				Statistics.getInstance().getNoOfUniqueLines()));
	}

}
