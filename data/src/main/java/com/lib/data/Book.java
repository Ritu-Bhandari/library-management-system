package com.lib.data;

import com.lib.enums.BOOK_STATUS;

import lombok.Data;

@Data
public class Book {

	private String id;
	private String name;
	private BOOK_STATUS status;
	private String assignedTo;
	private String title;
	private String author;

}
