package org.parndt;

import org.parndt.arguments.Arguments;
import org.parndt.processing.ProcessingException;

import java.io.IOException;

/**
 * Main class.
 * 
 * @author KNIME GmbH
 */
public class Main {

	public static void main(String[] args) throws IOException, ProcessingException {
		Arguments arguments = new Arguments(args);
		new ProcessingWorkflow(arguments).process();
		
		// DO NOT CHANGE THE FOLLOWING LINES OF CODE
		System.out.println(String.format("Processed %d lines (%d of which were unique)", //
				Statistics.getInstance().getNoOfLinesRead(), //
				Statistics.getInstance().getNoOfUniqueLines()));
	}

}
