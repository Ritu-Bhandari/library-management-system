package com.lib.biz;

import java.util.Set;

import com.lib.data.Book;
import com.lib.data.User;


public interface ILibraryManager {

	void addBooks(Set<Book> books);

	void addUsers(Set<User> users);

	void lendBooks(Set<String> booksId, String userId);

	void returnBooks(Set<String> bookId);

	void upperLimit(String userId, Integer upperLimit);

	Set<Book> searchBooksByTitle(String title);

	Set<Book> searchBooksByAuthor(String author);

	Set<User> searchUsersByName(String name);
}
