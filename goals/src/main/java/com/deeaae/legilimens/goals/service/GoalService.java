package com.deeaae.legilimens.goals.service;

import com.deeaae.legilimens.goals.model.GoalType;
import com.deeaae.legilimens.goals.model.dao.GoalDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoalService {

  GoalDao createGoal(GoalDao goalDao);

  GoalDao addTask(String goalId, String taskId);

  GoalDao markCreationComplete(String goalId);

  GoalDao markComplete(String goalId);

  GoalDao removeTask(String goalId, String taskId);

  GoalDao addAlert(String goalId, String alertId);

  GoalDao removeAlert(String goalId, String alertId);

  GoalDao saveGoal(GoalDao goalDao);

  Page<GoalDao> getAllGoals(Pageable pageable);

  Page<GoalDao> getAllGoalsForUser(String userId, Pageable pageable);

  Page<GoalDao> getAllGoalsForUserAndType(String userId, GoalType goalType, Pageable pageable);

  GoalDao getGoalById(String goalId);


}
