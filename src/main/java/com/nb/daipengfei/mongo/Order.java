package com.nb.daipengfei.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by daipengfei
 * on 2017/3/17.
 */


public class Order {
    @Id
    private String oderCode;

    private Date createTime;

    private GeoJsonPoint point;

    public Order(String oderCode, Date createTime, GeoJsonPoint point) {
        this.oderCode = oderCode;
        this.createTime = createTime;
        this.point = point;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oderCode='" + oderCode + '\'' +
                ", createTime=" + createTime +
                ", point=" + point +
                '}';
    }
}
