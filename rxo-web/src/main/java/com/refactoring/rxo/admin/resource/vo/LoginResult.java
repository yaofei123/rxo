package com.refactoring.rxo.admin.resource.vo;

/**
 * 登陆结果
 * Created by fai.yao on 2017/1/5.
 */
public class LoginResult {

	private boolean success;

	private String id;

	private String msg;

	public LoginResult(Boolean success){
		this.success = success;
	}

	public LoginResult(Boolean success, String id,String msg){
		this.success = success;
		this.id = id;
		this.msg = msg;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "LoginResult{" +
				"success=" + success +
				", id='" + id + '\'' +
				", msg='" + msg + '\'' +
				'}';
	}
}
