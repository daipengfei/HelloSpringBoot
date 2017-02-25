package com.nb.daipengfei.bean;

import org.springframework.stereotype.Component;

/*********************************
 *                               *
 Created by daipengfei on 16/12/9.
 *                               *
 ********************************/
@Component
public class TestBean {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
