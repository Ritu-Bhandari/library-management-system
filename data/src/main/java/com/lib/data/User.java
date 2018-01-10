package com.lib.data;

import java.util.Set;

import lombok.Data;


@Data
public class User{

	private String id;
	private String name;
	private Set<String> borrowedBooks;
	private Integer upperLimit;

}


