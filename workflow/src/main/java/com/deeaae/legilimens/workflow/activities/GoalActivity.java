package com.deeaae.legilimens.workflow.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface GoalActivity {

  boolean isGoalAvailable(String goalId);

  boolean isGoalCreationComplete(String goalId);

  boolean onTaskCompletion(String goalId, String taskId);

  boolean updateRAGStatus(String goalId);

  boolean isGoalComplete(String goalId);
}
