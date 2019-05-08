package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	// get(), put and keyset() are synchronized and can be safely used concurrently. 
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getUserList() {
		List<User> list = new ArrayList<User>(users.values());
		User user = new User();
		user.setId((long) 1);
		user.setAge(23);
		user.setName("Ragnar");
		list.add(user);
		return list;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postUser(@ModelAttribute User user) {
		users.put(user.getId(), user);
		return "success";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		return users.get(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		User newUser = users.get(id);
		newUser.setName(user.getName());
		newUser.setAge(user.getAge());
		users.put(id, newUser);
		return "success";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}
}
