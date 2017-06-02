package com.nb.daipengfei.job;

/**
 * Created by daipengfei
 * on 2017/6/2.
 */
public class TransportData {

    private String name;

    private long id;

    public TransportData(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TransportData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
