package com.coolcuy.dto;

public class BoardDto {

	private int num;
	private String name;
	private String subject;
	private String textArea;
	private int pos;
	private String regdate;
	private String pass;
	private String ip;
	private int cnt;
	
	public BoardDto(){
		
	}
	public BoardDto(int num,String name,String subject,String textArea,int pos,String regdate,String pass,String ip,int cnt){
		this.num=num;
		this.name=name;
		this.subject=subject;
		this.textArea=textArea;
		this.pos=pos;
		this.regdate=regdate;
		this.pass=pass;
		this.ip=ip;
		this.cnt=cnt;
	}
	public BoardDto(String name,String subject,String textArea,String pass,String ip){
		this.name=name;
		this.subject=subject;
		this.textArea=textArea;
		this.pass=pass;
		this.ip=ip;
	}
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTextArea() {
		return textArea;
	}
	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
	
}
