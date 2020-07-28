package com.shepe.admin.biz.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CounselingVO {
	
	private String h_userid;
	private String h_adminid;
	private String h_subject;
	private String h_content;
	private String h_ok;
	private int consultsq;
	private int historysq;
	private Date h_date;
	
	
	public String getH_userid() {
		return h_userid;
	}
	public void setH_userid(String h_userid) {
		this.h_userid = h_userid;
	}
	public String getH_adminid() {
		return h_adminid;
	}
	public void setH_adminid(String h_adminid) {
		this.h_adminid = h_adminid;
	}
	public String getH_subject() {
		return h_subject;
	}
	public void setH_subject(String h_subject) {
		this.h_subject = h_subject;
	}
	public String getH_content() {
		return h_content;
	}
	public void setH_content(String h_content) {
		this.h_content = h_content;
	}
	public String getH_ok() {
		return h_ok;
	}
	public void setH_ok(String h_ok) {
		this.h_ok = h_ok;
	}
	
	public int getConsultsq() {
		return consultsq;
	}
	public void setConsultsq(int consultsq) {
		this.consultsq = consultsq;
	}
	public int getHistorysq() {
		return historysq;
	}
	public void setHistorysq(int historysq) {
		this.historysq = historysq;
	}
	public String getH_date() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return date.format(h_date);
	}
	public void setH_date(Date h_date) {
		this.h_date = h_date;
	}
	

}
