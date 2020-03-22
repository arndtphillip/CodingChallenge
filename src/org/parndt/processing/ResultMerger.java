package org.parndt.processing;

import org.parndt.io.Chunk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ResultMerger {

    private List<Future<Chunk>> futures;

    public ResultMerger(List<Future<Chunk>> futures) {
        this.futures = futures;
    }

    public List<Chunk> merge() throws InterruptedException, ExecutionException {
        List<Chunk> chunks = new ArrayList<>();

        for (Future<Chunk> future : futures) {
            chunks.add(future.get());
        }

        chunks.sort(Comparator.comparingInt(Chunk::getChunkId));

        return chunks;
    }
}
