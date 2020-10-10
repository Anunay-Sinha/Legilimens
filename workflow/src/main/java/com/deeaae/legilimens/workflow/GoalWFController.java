package com.deeaae.legilimens.workflow;

import com.deeaae.legilimens.workflow.models.Constants;
import com.deeaae.legilimens.workflow.models.GoalWorkflowArguments;
import com.deeaae.legilimens.workflow.temporalworkflows.GoalWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoalWFController {

  @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
  public String start(@PathVariable String id) {
    WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    WorkflowClient client = WorkflowClient.newInstance(service);
    GoalWorkflow goalWorkflow = client
        .newWorkflowStub(GoalWorkflow.class, WorkflowOptions.newBuilder().setTaskQueue(
            Constants.GOAL_TASK_QUEUE).build());
    GoalWorkflowArguments goalWorkflowArguments = new GoalWorkflowArguments();
    goalWorkflowArguments.setGoalId(id);
    return goalWorkflow.manageGoal(goalWorkflowArguments);

  }


}
