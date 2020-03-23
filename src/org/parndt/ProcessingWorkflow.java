package org.parndt;

import org.parndt.arguments.Arguments;
import org.parndt.io.Chunk;
import org.parndt.io.LineReader;
import org.parndt.io.LineWriter;
import org.parndt.processing.ChunkProcessor;
import org.parndt.processing.ProcessingException;
import org.parndt.processing.ResultMerger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Class for the workflow of the application
 *
 * @author Philipp Arndt
 */
public class ProcessingWorkflow {

    /** */
    private Arguments arguments;

    public ProcessingWorkflow(Arguments arguments) {
        this.arguments = arguments;
    }

    public void process() throws IOException, ProcessingException {
        // read from file
        List<Chunk> chunks = new LineReader(arguments.getInputFile(), arguments.getThreads()).readAsChunks();

        // parse, process, merge back
        List<Chunk> processedChunks = processChunks(chunks);

        // write to file
        new LineWriter(arguments.getOutputFile(), processedChunks).write();
    }

    public List<Chunk> processChunks(List<Chunk> chunks) throws ProcessingException {
        ExecutorService executor = Executors.newFixedThreadPool(arguments.getThreads());
        List<Callable<Chunk>> callables = new ArrayList<>();

        for (Chunk chunk : chunks) {
            callables.add(new ChunkProcessor(arguments.getInputType(), arguments.getOperations(), chunk));
        }

        List<Future<Chunk>> futures = new ArrayList<>();
        List<Chunk> processedChunks;

        try {
            futures = executor.invokeAll(callables);
            processedChunks = new ResultMerger(futures).merge();
        } catch (InterruptedException | ExecutionException e) {
            for (Future<Chunk> future : futures) {
                future.cancel(true);
            }

            throw new ProcessingException("Error while processing! ", e);
        } finally {
            executor.shutdown();
        }

        return processedChunks;
    }
}
