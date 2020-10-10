package com.deeaae.legilimens.workflow.temporalworkflows.impl;

import com.deeaae.legilimens.workflow.activities.GoalActivity;
import com.deeaae.legilimens.workflow.models.Constants;
import com.deeaae.legilimens.workflow.models.GoalWorkflowArguments;
import com.deeaae.legilimens.workflow.temporalworkflows.GoalWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoalWorkflowImpl implements GoalWorkflow {


  private final GoalActivity goalActivity;
  private String goalId;
  private String goalWorkflowStatus;

  public GoalWorkflowImpl() {
    this.goalActivity = Workflow
        .newActivityStub(GoalActivity.class, ActivityOptions.newBuilder().setStartToCloseTimeout(
            Duration.ofMinutes(Constants.DEFAULT_ACTIVITY_START_TO_CLOSE_TIMEOUT_IN_MINUTES))
            .build());
  }

  @Override
  public String manageGoal(GoalWorkflowArguments goalWorkflowArguments) {
    log.info("Starting a goal with id {}", goalWorkflowArguments.getGoalId());
    goalId = goalWorkflowArguments.getGoalId();

    if (goalActivity.isGoalAvailable(goalId)) {
      goalWorkflowStatus = "Initiated";
    } else {
      goalWorkflowStatus = "Failed";
      return goalWorkflowStatus;
    }

    while (!goalActivity.isGoalCreationComplete(goalId)) {
      log.debug("Goal is not created {}", goalId);
      Workflow.sleep(Constants.GOAL_ACTIVITY_SLEEP_FOR_CREATION_IN_MS);
    }
    log.info("Goal creation is  complete {}", goalId);
    goalWorkflowStatus = "Created";

    while (!goalActivity.isGoalComplete(goalId)) {
      log.debug("Goal is not complete {}", goalId);
      goalWorkflowStatus = "Open";
      goalActivity.updateRAGStatus(goalId);
      Workflow.sleep(Constants.GOAL_ACTIVITY_SLEEP_FOR_CREATION_IN_MS);
    }
    log.info("Goal is  complete {}", goalId);
    goalWorkflowStatus = "Completed";

    return "ok " + goalWorkflowStatus;
  }

  @Override
  public void onUpdate(String goalId) {

  }

  @Override
  public void onTaskCompletion(String goalId, String taskId) {

  }
}
