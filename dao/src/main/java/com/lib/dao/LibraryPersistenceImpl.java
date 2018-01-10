package com.lib.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lib.data.Book;
import com.lib.data.User;

public class LibraryPersistenceImpl implements ILibraryPersistence {

	private volatile Map<String, Book> booksMap = Maps.newHashMap();

	private volatile Map<String, User> usersMap = Maps.newHashMap();

	@Override
	public void addBooks(List<Book> books) {
		for (Book book : books) {
			booksMap.put(book.getId(), book);
		}
	}

	@Override
	public void addUsers(List<User> users) {
		for (User user : users) {
			usersMap.put(user.getId(), user);
		}
	}

	@Override
	public Book loadBook(String bookId) {
		return booksMap.get(bookId);
	}

	@Override
	public User loadUser(String userId) {
		return usersMap.get(userId);
	}

	@Override
	public Book updateBook(Book book) {
		return booksMap.put(book.getId(), book);
	}

	@Override
	public User updateUser(User user) {
		return usersMap.put(user.getId(), user);
	}

	@Override
	public Set<Book> searchBooksByTitle(String title) {

		Set<Book> bookSet = Sets.newHashSet();
		booksMap.entrySet().stream()
		.forEach(
				book -> {
					if (book.getValue().getTitle() != null && book.getValue().getTitle().trim().equalsIgnoreCase(title.trim())) {
						bookSet.add(book.getValue());                    } 
				}
				);

		return bookSet;
	}

	@Override
	public Set<Book> searchBooksByAuthor(String author) {

		Set<Book> bookSet = Sets.newHashSet();
		booksMap.entrySet().stream()
		.forEach(
				book -> {
					if (book.getValue().getTitle() != null && book.getValue().getAuthor().trim().equalsIgnoreCase(author.trim())) {
						bookSet.add(book.getValue());                    } 
				}
				);

		return bookSet;

	}

	@Override
	public Set<User> searchUsersByName(String name) {

		Set<User> users = Sets.newHashSet();
		usersMap.entrySet().stream()
		.forEach(
				user -> {
					if (user.getValue().getName() != null && user.getValue().getName().trim().equalsIgnoreCase(name.trim())) {
						users.add(user.getValue());                    } 
				}
				);

		return users;
	}

}
