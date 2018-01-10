package com.lib.data;

import java.util.Set;

import lombok.Data;

@Data
public class LibraryRequest{
	private String userId;
	private Set<String> booksId;
	private Integer upperLimit;
}
