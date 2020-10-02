package com.deeaae.legilimens.user.service;

import com.deeaae.legilimens.user.model.User;
import java.util.List;

public interface UserService {

  User createUser(User user);

  User getUserById(String id);

  User getUserByUsername(String username);

  User getUserByEmailId(String emailId);

  List<User> getAllUsers(int pageSize, int offset);
}
