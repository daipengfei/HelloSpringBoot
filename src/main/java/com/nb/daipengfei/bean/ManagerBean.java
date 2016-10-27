package com.nb.daipengfei.bean;

import javax.annotation.PostConstruct;

/*********************************
 *                               *
 Created by daipengfei on 16/9/10.
 *                               *
 ********************************/

public class ManagerBean {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ManagerBean{" +
                "id=" + id +
                '}';
    }

}
