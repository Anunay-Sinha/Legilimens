package com.deeaae.legilimens.user.service.impl;

import com.deeaae.legilimens.user.model.User;
import com.deeaae.legilimens.user.repo.UserRepo;
import com.deeaae.legilimens.user.service.UserService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;


  @Override
  public User createUser(@NonNull User user) {
    if (user.getEmailId() == null || user.getUsername() == null) {
      throw new RuntimeException("Data not valid");
    }
    if (getUserByEmailId(user.getEmailId()) != null
        || getUserByUsername(user.getUsername()) != null) {
      throw new RuntimeException("User already exist with the details provided");
    }
    user.setId(UUID.randomUUID().toString());
    return userRepo.save(user);
  }

  @Override
  public User getUserById(String id) {
    return userRepo.findById(id).orElse(null);
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepo.findByUsername(username).orElse(null);
  }

  @Override
  public User getUserByEmailId(String emailId) {
    return userRepo.findByEmailId(emailId).orElse(null);
  }

  @Override
  public List<User> getAllUsers(int pageSize, int offset) {
    Pageable pageable = PageRequest.of(offset, pageSize);
    return userRepo.findAll(pageable).stream().collect(Collectors.toList());
  }
}
