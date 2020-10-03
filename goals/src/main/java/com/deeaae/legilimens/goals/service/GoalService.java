package com.deeaae.legilimens.goals.service;

import com.deeaae.legilimens.goals.model.GoalType;
import com.deeaae.legilimens.goals.model.dao.GoalDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoalService {

  GoalDao createGoal(GoalDao goalDao);

  GoalDao addTracker(String goalId, String trackerId);

  GoalDao removeTracker(String goalId, String trackerId);

  GoalDao addAlert(String goalId, String alertId);

  GoalDao removeAlert(String goalId, String alertId);

  GoalDao saveGoal(GoalDao goalDao);

  Page<GoalDao> getAllGoals(Pageable pageable);

  Page<GoalDao> getAllGoalsForUser(String userId, Pageable pageable);

  Page<GoalDao> getAllGoalsForUserAndType(String userId, GoalType goalType, Pageable pageable);

  GoalDao getGoalById(String goalId);


}
