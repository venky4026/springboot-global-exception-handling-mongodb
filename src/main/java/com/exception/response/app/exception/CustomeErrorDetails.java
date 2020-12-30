package com.exception.response.app.exception;

import java.util.Date;

public class CustomeErrorDetails {
	private Date timestamp;
	private String message;
	private String errordetails;
	public CustomeErrorDetails() {
	}
	public CustomeErrorDetails(Date timestamp, String message, String errordetails) {
		this.timestamp = timestamp;
		this.message = message;
		this.errordetails = errordetails;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrordetails() {
		return errordetails;
	}
	public void setErrordetails(String errordetails) {
		this.errordetails = errordetails;
	}
	@Override
	public String toString() {
		return "CustomeErrorDetails [timestamp=" + timestamp + ", message=" + message + ", errordetails=" + errordetails
				+ "]";
	}
	

}
