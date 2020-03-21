package org.parndt;

import org.parndt.arguments.Arguments;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Main class.
 * 
 * @author KNIME GmbH
 */
public class Main {

	public static void main(String[] args) throws IOException {
		Arguments arguments = new Arguments(args);
		new ProcessingWorkflow(arguments).process();
		
		// DO NOT CHANGE THE FOLLOWING LINES OF CODE
		System.out.println(String.format("Processed %d lines (%d of which were unique)", //
				Statistics.getInstance().getNoOfLinesRead(), //
				Statistics.getInstance().getNoOfUniqueLines()));
	}

}
