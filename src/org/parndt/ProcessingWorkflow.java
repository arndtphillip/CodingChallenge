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

    private Arguments arguments;

    public ProcessingWorkflow(Arguments arguments) {
        this.arguments = arguments;
    }

    /**
     * Main process method. This implements the workflow of the application. The input file is read as a list of
     * {@link Chunk}, which are processed by {@link ProcessingWorkflow#processChunks(List)}. Afterwards the result is
     * written to the output file or to System.out, depending on {@link Arguments#writeToFile()}.
     *
     * @throws IOException Throws an IOException if an error occurs while reading the input or writing the output file.
     * @throws ProcessingException Throws an {@link ProcessingException} if an error occurs while parsing or processing
     * the chunks in {@link ProcessingWorkflow#processChunks(List)}.
     */
    public void process() throws IOException, ProcessingException {
        // read from file
        List<Chunk> chunks = new LineReader(arguments.getInputFile(), arguments.getThreads()).readAsChunks();

        // parse, process, merge back
        List<Chunk> processedChunks = processChunks(chunks);

        LineWriter writer = new LineWriter(processedChunks);
        if (arguments.writeToFile()) {
            // write to file
            writer.writeToFile(arguments.getOutputFile());
        } else {
            // write to system out
            writer.writeToSystemOut();
        }
    }

    /**
     * This method parses and processes a list of {@link Chunk} and returns the resulting {@link Chunk} list.
     *
     * At first, a thread pool with {@link Arguments#getThreads()} threads is created and the parsing and
     * processing work is dispatched to the threads. Afterwards the results are collected, merged and returned.
     *
     * @param chunks The chunks to process
     * @return The processed chunks
     * @throws ProcessingException Throws a {@link ProcessingException} if an error occurs while parsing or processing
     * the chunks. All threads get cancelled.
     */
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
