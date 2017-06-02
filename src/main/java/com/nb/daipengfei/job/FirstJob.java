package com.nb.daipengfei.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by daipengfei
 * on 2017/5/19.
 */

public class FirstJob implements DataflowJob<TransportData> {

    @Override
    public List<TransportData> fetchData(ShardingContext shardingContext) {
        int shard = shardingContext.getShardingItem();
        int total = shardingContext.getShardingTotalCount();
        List<TransportData> list = new ArrayList<>();
        List<Integer> cityIds = Arrays.asList(1,2,3,4,5,6,7);
        for(Integer cityId : cityIds){
            if(cityId % total == shard){
                list.add(new TransportData("a",cityId));
            }
        }
        return list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<TransportData> data) {
        System.out.println(shardingContext.getShardingItem() + " : " + data);
    }
}
