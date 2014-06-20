package org.kona.js.exceptions;

public class KonaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9090450786149913212L;
	public String msg;

	public KonaException(String msg) {

		super(msg);
		this.setMsg(msg);

	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
