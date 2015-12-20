package com.x.agile.plm.rest.bean;

import java.io.InputStream;

public class AttachmentBean {
	public InputStream getAttStream() {
		return attStream;
	}
	public void setAttStream(InputStream attStream) {
		this.attStream = attStream;
	}
	public String getAttName() {
		return attName;
	}
	public void setAttName(String attName) {
		this.attName = attName;
	}
	private InputStream attStream;
	private String attName;

}
