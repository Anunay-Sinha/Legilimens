package com.deeaae.legilimens.workflow.temporalworkflows;

import com.deeaae.legilimens.workflow.models.GoalWorkflowArguments;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface GoalWorkflow {

  @WorkflowMethod
  String manageGoal(GoalWorkflowArguments goalWorkflowArguments);

  @SignalMethod
  void onUpdate(String goalId);

  @SignalMethod
  void onTaskCompletion(String goalId, String taskId);


}
