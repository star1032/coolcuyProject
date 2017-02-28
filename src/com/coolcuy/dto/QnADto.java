package com.coolcuy.dto;

public class QnADto {
	private int num;
	private String name;
	private String subject;
	private String textArea;
	private int pos;
	private int depth;
	private int ref;
	private String regdate;
	private String pass;
	private int cnt;
	
	public QnADto(){
		
	}
	public QnADto(String name,String subject,String textArea,int pos,int depth,int ref,String pass){
		this.name=name;
		this.subject=subject;
		this.textArea=textArea;
		this.pos=pos;
		this.depth=depth;
		this.ref=ref;
		this.pass=pass;
	}
	
	public QnADto(int num,String name,String subject,String textArea,int pos,int depth,int ref,String regdate,String pass,int cnt){
		this.num=num;
		this.name=name;
		this.subject=subject;
		this.textArea=textArea;
		this.pos=pos;
		this.depth=depth;
		this.ref=ref;
		this.regdate=regdate;
		this.pass=pass;
		this.cnt=cnt;
	}
	
	public QnADto(String name,String subject,String textArea,String pass){
		this.name=name;
		this.subject=subject;
		this.textArea=textArea;
		this.pass=pass;
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
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
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

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
