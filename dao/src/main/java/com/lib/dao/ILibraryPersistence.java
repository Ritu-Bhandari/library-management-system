package com.lib.dao;

import java.util.Set;

import com.lib.data.Book;
import com.lib.data.User;

public interface ILibraryPersistence {

	void addBooks(Set<Book> books);

	void addUsers(Set<User> users);

	Book loadBook(String bookId);

	User loadUser(String userId);

	Book updateBook(Book book);

	User updateUser(User user);

	Set<Book> searchBooksByTitle(String title);

	Set<Book> searchBooksByAuthor(String author);

	Set<User> searchUsersByName(String name);
}
