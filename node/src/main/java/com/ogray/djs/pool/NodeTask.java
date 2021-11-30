package com.ogray.djs.pool;

public interface NodeTask {
    // 0 .. 100
    int getCompleteLevel();
    boolean isDone();
}
