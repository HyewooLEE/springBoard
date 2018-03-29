package spring.controller;

import org.springframework.stereotype.Component;

@Component
public class ListDTO {
	private int r;
	private int no;
	private String subject;
	private String writer;
	private String contents;
	private String writeDate;
	
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
}
