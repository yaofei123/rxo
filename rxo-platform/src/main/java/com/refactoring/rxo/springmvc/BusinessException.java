package com.refactoring.rxo.springmvc;

/**
 * 暂时先定义为运行时异常
 * TODO 等controller代码结构适配好后，再修改为普通异常
 * Created by fai.yao on 2016/12/13.
 */
public class BusinessException extends RuntimeException{

	private String module;
	private String business;
	private String errorCode;


	public BusinessException(String module, String business, String errorCode, String errorMessage){
		super(errorMessage);

		this.module = module;
		this.business = business;
		this.errorCode = errorCode;

	}

	@Override
	public String toString(){
		return module+"-"+business+"-"+errorCode+":"+this.getMessage();
	}
}
