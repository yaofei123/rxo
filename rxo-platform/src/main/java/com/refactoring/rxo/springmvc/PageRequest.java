package com.refactoring.rxo.springmvc;

/**
 * Created by fai.yao on 16/10/25.
 */
public class PageRequest<T>  {

	private Integer page;
	private Integer limit;
	private T condition;

	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}


	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
