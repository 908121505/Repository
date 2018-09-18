package com.honglu.quickcall.common.core.generator;

/**
 * ID生成器，能够适应在分布式环境下的高并发
 * @author SteveGuo
 * @version 1.0
 * @created 12/04/2017 15:39:23 PM
 */
public class IDWorker {

    private final long workerId;
    private final static long twepoch = 1288834974657L;
    private long sequence = 0L;
    private final static long workerIdBits = 4L;
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;

    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private long lastTimestamp = -1L;

    private IDWorker(final long workerId) {
        super();
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    public static IDWorker getInstance() {
        return IDWorkerHolder.INSTANCE;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGenerate();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(
                        String.format(
                                "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << workerIdShift) | (this.sequence);
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGenerate();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGenerate();
        }
        return timestamp;
    }

    private long timeGenerate() {
        return System.currentTimeMillis();
    }

    private static class IDWorkerHolder {
        private static IDWorker INSTANCE = new IDWorker(8);
    }

}

