package com.lib.data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class User{

	private String id;
	private String name;
	private Set<String> borrowedBooks;
	private Integer upperLimit;

}


