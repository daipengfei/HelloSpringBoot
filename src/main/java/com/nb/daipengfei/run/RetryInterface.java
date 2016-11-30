package com.nb.daipengfei.run;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

/*********************************
 *                               *
 Created by daipengfei on 16/11/14.
 *                               *
 ********************************/

public interface RetryInterface {

    @Retryable(value = RuntimeException.class,backoff = @Backoff(1000))
    void testRetry(String name);

    @Recover
    public void recover(RuntimeException e);
}
