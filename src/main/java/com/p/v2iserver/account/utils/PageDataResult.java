package com.p.v2iserver.account.utils;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class PageDataResult {

	//总记录数量
	private Integer totals;
	//当前页数据列表
	private List<?> list;

	public PageDataResult() {
	}

	public PageDataResult(Integer totals,
                          List<?> list) {
		this.totals = totals;
		this.list = list;
	}
}
