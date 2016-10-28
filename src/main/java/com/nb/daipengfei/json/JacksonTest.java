package com.nb.daipengfei.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/*********************************
 *                               *
 Created by daipengfei on 16/10/13.
 *                               *
 ********************************/

public class JacksonTest {
    static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    static String json = "{\"key\":{\"key_test1\":\"value1\"},\"hello\":\"world\"}";

    public static void main(String[] args) throws IOException {
        Bean map = objectMapper.readValue(json, Bean.class);
        System.out.println(map);
    }

    static class Bean{
        private LocalBean key;

        public LocalBean getKey() {
            return key;
        }

        public void setKey(LocalBean key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "key=" + key +
                    '}';
        }
    }

    static class LocalBean{
        private String key_test1;

        private String key_test2;

        public String getKey_test2() {
            return key_test2;
        }

        public void setKey_test2(String key_test2) {
            this.key_test2 = key_test2;
        }

        public String getKey_test1() {
            return key_test1;
        }

        public void setKey_test1(String key_test1) {
            this.key_test1 = key_test1;
        }

        @Override
        public String toString() {
            return "LocalBean{" +
                    "key_test1='" + key_test1 + '\'' +
                    ", key_test2='" + key_test2 + '\'' +
                    '}';
        }
    }
}
