package com.bachi.service;

import java.util.List;

import com.bachi.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> findAll();
	List<User> findById(int id);
	void editRole(int idEmployee, int idRole);
	List<User> findByIdAllfields(int id);
	void updateUser(User user);
}
