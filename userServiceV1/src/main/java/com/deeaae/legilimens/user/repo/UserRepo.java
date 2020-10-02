package com.deeaae.legilimens.user.repo;

import com.deeaae.legilimens.user.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

  Optional<User> findByUsername(String username);

  Optional<User> findByEmailId(String emailId);


}
