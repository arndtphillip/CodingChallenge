package org.parndt;

import org.parndt.arguments.Arguments;
import org.parndt.io.Chunk;
import org.parndt.io.LineReader;
import org.parndt.io.LineWriter;
import org.parndt.processing.OperationsProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ProcessingWorkflow {

    private Arguments arguments;

    public ProcessingWorkflow(Arguments arguments) {
        this.arguments = arguments;
    }

    public void process() throws IOException, InterruptedException, ExecutionException {
        // read
        LineReader reader = new LineReader(arguments.getInputFile(), arguments.getThreads());
        List<Chunk> chunks = reader.readAsChunks();

        // dispatch to threads
        ExecutorService executor = Executors.newFixedThreadPool(arguments.getThreads());

        List<Callable<Chunk>> callables = new ArrayList<>();
        for (Chunk chunk : chunks) {
            OperationsProcessor processor = new OperationsProcessor(arguments.getInputType(), arguments.getOperations(), chunk);
            callables.add(processor);
        }

        List<Future<Chunk>> futures = executor.invokeAll(callables);

        // collect results and write
        LineWriter writer = new LineWriter(arguments.getOutputFile(), futures);
        writer.write();

        executor.shutdown();
    }
}
