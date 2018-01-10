package com.lib.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.biz.ILibraryManager;
import com.lib.data.Book;
import com.lib.data.LibraryRequest;
import com.lib.data.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class LibarayControllerV1{

	@Autowired
	private ILibraryManager librarayManagerImpl;

	@RequestMapping(value = "/v1/healthcheck", method = RequestMethod.GET)
	public @ResponseBody String healthcheck(HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		return "Server is running";
	}


	@RequestMapping(value = "/v1/add/books", method = RequestMethod.POST)
	public @ResponseBody String addBooks(@RequestBody ArrayList<Book> books, HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		librarayManagerImpl.addBooks(books);
		return "added successfully";
	}


	@RequestMapping(value = "/v1/add/users", method = RequestMethod.POST)
	public @ResponseBody String addUsers(@RequestBody ArrayList<User> users , HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		librarayManagerImpl.addUsers(users);
		return "added successfully";
	}


	@RequestMapping(value = "/v1/lend", method = RequestMethod.POST)
	public @ResponseBody String lendBooks(@RequestBody LibraryRequest request, HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		if(request.getBooksId() == null || request.getUserId() == null || request.getBooksId().isEmpty()) {
			return "books ids and user id is mandatory";
		}
		librarayManagerImpl.lendBooks(request.getBooksId(), request.getUserId());
		return "done";
	}


	@RequestMapping(value = "/v1/return", method = RequestMethod.POST)
	public @ResponseBody String returnBooks(@RequestBody LibraryRequest request, HttpServletResponse response)
			throws Exception {

		if(request.getBooksId() == null || request.getBooksId().isEmpty()) {
			return "books ids is mandatory";
		}

		librarayManagerImpl.returnBooks(request.getBooksId());
		return "done";
	}


	@RequestMapping(value = "/v1/limit", method = RequestMethod.POST)
	public @ResponseBody String limitTheNumberOfBooks(@RequestBody LibraryRequest request, HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {

		if(request.getUserId() == null || request.getUpperLimit() == null) {
			return "user id and upper limit is mandatory";
		}

		librarayManagerImpl.upperLimit(request.getUserId(), request.getUpperLimit());
		return "done";
	}


	@RequestMapping(value = "/v1/search/book", method = RequestMethod.GET)
	public @ResponseBody Set<Book> searchBooksByTitle(@RequestParam Map<String, Object> paramSet , HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		if((paramSet.get("title") == null || paramSet.get("title").toString().isEmpty()) && (paramSet.get("author") == null || paramSet.get("author").toString().isEmpty())) {
			throw new IllegalArgumentException("either author or title is mandatory");
		}
		if(paramSet.get("title") != null && !paramSet.get("title").toString().isEmpty()){
			return librarayManagerImpl.searchBooksByTitle(paramSet.get("title").toString());
		}else{
			return librarayManagerImpl.searchBooksByAuthor(paramSet.get("author").toString());
		}
	}

	@RequestMapping(value = "/v1/search/user", method = RequestMethod.GET)
	public @ResponseBody Set<User> searchUsersByName(@RequestParam Map<String, Object> paramSet, HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		if(paramSet.get("name") == null || paramSet.get("name").toString().isEmpty()) {
			throw new IllegalArgumentException("name is mandatory");
		}
		return librarayManagerImpl.searchUsersByName(paramSet.get("name").toString());
	}


	@Data
	@AllArgsConstructor
	private class SaveAcknowledgement {
		private boolean acknowledged;
	}

}
