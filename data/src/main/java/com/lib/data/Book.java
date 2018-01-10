package com.lib.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lib.enums.BOOK_STATUS;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Book {

	private String id;
	private String name;
	private BOOK_STATUS status;
	private String assignedTo;
	private String title;
	private String author;

}
