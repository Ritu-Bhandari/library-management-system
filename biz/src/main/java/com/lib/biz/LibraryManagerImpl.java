package com.lib.biz;

import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Sets;
import com.lib.dao.ILibraryPersistence;
import com.lib.data.Book;
import com.lib.data.User;
import com.lib.enums.BOOK_STATUS;
import com.lib.enums.ERROR_CODE;

public class LibraryManagerImpl implements ILibraryManager {

	ILibraryPersistence librarayPersistence;

	@Override
	public void addBooks(Set<Book> books) {
		for(Book book : books) {
			book.setId(UUID.randomUUID().toString());
		}
		librarayPersistence.addBooks(books);
	}

	@Override
	public void addUsers(Set<User> users) {
		for(User user : users) {
			user.setId(UUID.randomUUID().toString());
		}
		librarayPersistence.addUsers(users);
	}

	@Override
	public void lendBooks(Set<String> booksId, String userId) {

		User user = librarayPersistence.loadUser(userId);

		if (user == null) {
			throw new IllegalArgumentException(ERROR_CODE.INVALID_USER.name());
		}

		if (user.getBorrowedBooks() != null && user.getUpperLimit() != null
				&& user.getBorrowedBooks().size() == user.getUpperLimit()) {
			throw new IllegalArgumentException(ERROR_CODE.USER_IS_ALREADY_ASSIGNED_MAX_ALLOWED_BOOKS.name());
		}

		if (user.getUpperLimit() != null) {
			int numberOfBorrowedBooks = user.getBorrowedBooks() != null ? user.getBorrowedBooks().size() : 0;
			int remainingAllowedAllocation = user.getUpperLimit() - numberOfBorrowedBooks;
			if (booksId.size() > remainingAllowedAllocation) {
				throw new IllegalArgumentException(ERROR_CODE.USER_IS_EXCEEDING_UPPER_LIMIT.name());
			}
		}

		Set<String> borrowedBooks = user.getBorrowedBooks();
		if (borrowedBooks == null) {
			borrowedBooks = Sets.newHashSet();
		}

		for (String bookId : booksId) {
			Book book = librarayPersistence.loadBook(bookId);

			if (book == null) {
				System.out.println("invalid book : " + bookId);
				continue;
			}
			if (book.getStatus() == BOOK_STATUS.ASSIGNED) {
				System.out.println("book is already assigned");
				continue;
			}

			book.setStatus(BOOK_STATUS.ASSIGNED);
			book.setAssignedTo(userId);
			librarayPersistence.updateBook(book);
			borrowedBooks.add(bookId);
		}

		user.setBorrowedBooks(borrowedBooks);
		librarayPersistence.updateUser(user);

	}

	@Override
	public void returnBooks(Set<String> booksId) {

		for (String bookId : booksId) {
			Book book = librarayPersistence.loadBook(bookId);
			if (book == null) {
				System.out.println("invalid book : " + bookId);
			}
			book.setStatus(BOOK_STATUS.UN_ASSIGNED);
			book.setStatus(null);

			User user = librarayPersistence.loadUser(book.getAssignedTo());
			Set<String> borrowedBooks = user.getBorrowedBooks();
			borrowedBooks.remove(bookId);
			user.setBorrowedBooks(borrowedBooks);

			librarayPersistence.updateBook(book);
			librarayPersistence.updateUser(user);
		}
	}

	@Override
	public void upperLimit(String userId, Integer upperLimit) {

		User user = librarayPersistence.loadUser(userId);
		user.setUpperLimit(upperLimit);
		librarayPersistence.updateUser(user);
	}

	@Override
	public Set<Book> searchBooksByTitle(String title) {

		return librarayPersistence.searchBooksByTitle(title);
	}

	@Override
	public Set<Book> searchBooksByAuthor(String author) {

		return librarayPersistence.searchBooksByAuthor(author);
	}

	@Override
	public Set<User> searchUsersByName(String name) {

		return librarayPersistence.searchUsersByName(name);
	}

}
