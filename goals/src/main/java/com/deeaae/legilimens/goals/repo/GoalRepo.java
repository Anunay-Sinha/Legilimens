package com.deeaae.legilimens.goals.repo;

import com.deeaae.legilimens.goals.model.GoalType;
import com.deeaae.legilimens.goals.model.dao.GoalDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoalRepo extends MongoRepository<GoalDao, String> {

  Page<GoalDao> findAllByUserId(String userId, Pageable page);

  Page<GoalDao> findAllByUserIdAndGoalType(String userId, GoalType goalType, Pageable page);


}
