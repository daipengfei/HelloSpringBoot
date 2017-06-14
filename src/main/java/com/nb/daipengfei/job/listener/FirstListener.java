package com.nb.daipengfei.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

/**
 * Created by daipengfei
 * on 2017/6/4.
 */

public class FirstListener implements ElasticJobListener{
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("before : " + shardingContexts.getJobName());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("after : " + shardingContexts.getJobName());
    }
}
