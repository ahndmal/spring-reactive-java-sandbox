package com.anma.springreactivejl;

import jdk.dynalink.StandardOperation;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyReader implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousFileChannel fileChannel;
    private Runnable finished;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private long position;

    @Override
    public void completed(Integer result, ByteBuffer attachment) {

        try {
            AsynchronousFileChannel.open(Path.of("/home/andrii/docs"), Collections.singleton(StandardOpenOption.READ), this.executorService);
            ByteBuffer byteBuffer = ByteBuffer.allocate(FileCopyUtils.BUFFER_SIZE);
            fileChannel.read(byteBuffer, position, byteBuffer, this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

    }

}
