package com.java.board.board.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private int articleNo;
	private int parentNo;
	private int layer;
	private int count;
	private Date writeDate;
	private String title;
	private String contents;
	private String writer;
	private String imageFileName;
	private int hits;
}
