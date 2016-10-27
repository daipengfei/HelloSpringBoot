package com.nb.daipengfei.classloader;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nb.daipengfei.classloader.leak.LeakClassInterface;

/*********************************
 *                               *
 Created by daipengfei on 16/9/12.
 *                               *
 ********************************/

public class LeakTest {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        String s = objectMapper.writeValueAsString(list);
        System.out.println(s);
    }
}
