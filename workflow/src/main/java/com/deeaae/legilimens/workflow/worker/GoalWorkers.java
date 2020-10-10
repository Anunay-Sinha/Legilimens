package com.deeaae.legilimens.workflow.worker;

import com.deeaae.legilimens.workflow.activities.GoalActivity;
import com.deeaae.legilimens.workflow.models.Constants;
import com.deeaae.legilimens.workflow.temporalworkflows.impl.GoalWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GoalWorkers {

  @Autowired
  private GoalActivity goalActivity;

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationEvent() {
    log.info("Registering workers");
    WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newInstance();
    WorkflowClient client = WorkflowClient.newInstance(serviceStub);
    WorkflowOptions options = WorkflowOptions.newBuilder()
        .setTaskQueue(Constants.GOAL_TASK_QUEUE)
        .build();

    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker(Constants.GOAL_TASK_QUEUE);
    worker.registerWorkflowImplementationTypes(GoalWorkflowImpl.class);
    worker.registerActivitiesImplementations(goalActivity);
    factory.start();

  }

}
