package com.deeaae.legilimens.goals.service;

import com.deeaae.legilimens.goals.model.GoalStatus;
import com.deeaae.legilimens.goals.model.GoalType;
import com.deeaae.legilimens.goals.model.RAGStatus;
import com.deeaae.legilimens.goals.model.dao.GoalDao;
import com.deeaae.legilimens.goals.repo.GoalRepo;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoalServiceImpl implements GoalService {

  @Autowired
  private GoalRepo goalRepo;

  @Override
  public GoalDao createGoal(GoalDao goalDao) {
    log.info("creating a new goal.");
    log.debug("creating a new goal {}", goalDao);
    goalDao.setCreatedDate(LocalDateTime.now());
    goalDao.setStatus(GoalStatus.INITIALIZED);
    goalDao.setRagStatus(RAGStatus.NA);
    goalDao.setId(UUID.randomUUID().toString());
    log.debug("saving goal with id {}", goalDao.getId());
    return saveGoal(goalDao);
  }

  @Override
  public GoalDao addTask(String goalId, String taskId) {
    GoalDao goalDao = getGoalById(goalId);
    if (goalDao == null) {
      throw new RuntimeException("Goal not found");
    }
    Set<String> taskIds = goalDao.getTaskIds();
    if (taskIds == null) {
      taskIds = new HashSet<>();
    }
    if (taskIds.contains(taskId)) {
      return goalDao;
    } else {
      taskIds.add(taskId);
      goalDao.setAlertIds(taskIds);
      return saveGoal(goalDao);
    }
  }

  @Override
  public GoalDao removeTask(String goalId, String taskId) {
    GoalDao goalDao = getGoalById(goalId);
    if (goalDao == null) {
      throw new RuntimeException("Goal not found");
    }
    Set<String> taskIds = goalDao.getTaskIds();
    if (taskIds == null) {
      throw new RuntimeException("Task id not found for the given goal");
    }
    if (taskIds.contains(taskId)) {
      taskIds.remove(taskId);
      goalDao.setAlertIds(taskIds);
      return saveGoal(goalDao);

    } else {
      throw new RuntimeException("Task id not found for the given goal");
    }

  }

  @Override
  public GoalDao addAlert(String goalId, String alertId) {
    GoalDao goalDao = getGoalById(goalId);
    if (goalDao == null) {
      throw new RuntimeException("Goal not found");
    }
    Set<String> alertIds = goalDao.getAlertIds();
    if (alertIds == null) {
      alertIds = new HashSet<>();
    }
    if (alertIds.contains(alertId)) {
      return goalDao;
    } else {
      alertIds.add(alertId);
      goalDao.setAlertIds(alertIds);
      return saveGoal(goalDao);
    }

  }

  @Override
  public GoalDao removeAlert(String goalId, String alertId) {
    GoalDao goalDao = getGoalById(goalId);
    if (goalDao == null) {
      throw new RuntimeException("Goal not found");
    }
    Set<String> alertIds = goalDao.getAlertIds();
    if (alertIds == null) {
      throw new RuntimeException("Alert id not found for the given goal");
    }
    if (alertIds.contains(alertId)) {
      alertIds.remove(alertId);
      goalDao.setAlertIds(alertIds);
      return saveGoal(goalDao);

    } else {
      throw new RuntimeException("Alert id not found for the given goal");
    }
  }

  @Override
  public GoalDao saveGoal(GoalDao goalDao) {
    log.info("request to save goal with id {}", goalDao.getId());
    log.debug("saving goal {}", goalDao);
    String errorString = "";
    boolean isGoalGood = true;
    if (goalDao.getSubject() == null || goalDao.getSubject().isEmpty()) {
      isGoalGood = false;
      errorString = errorString + "Subject cannot be null or empty. \n";
    }
    if (goalDao.getId() == null || goalDao.getId().isEmpty()) {
      isGoalGood = false;
      errorString = errorString + "Id cannot be null or empty. \n";
    }
    if (goalDao.getUserId() == null || goalDao.getUserId().isEmpty()) {
      isGoalGood = false;
      errorString = errorString + "UserId cannot be null or empty. \n";
    }
    if (isGoalGood) {
      log.debug("persisting the goal in db");
      return goalRepo.save(goalDao);
    } else {
      log.error("error while saving the goal {}", errorString);
      throw new RuntimeException("Error saving the goal. Err : " + errorString);
    }
  }

  @Override
  public Page<GoalDao> getAllGoals(Pageable pageable) {
    return goalRepo.findAll(pageable);
  }

  @Override
  public Page<GoalDao> getAllGoalsForUser(String userId, Pageable pageable) {
    return goalRepo.findAllByUserId(userId, pageable);
  }

  @Override
  public Page<GoalDao> getAllGoalsForUserAndType(String userId, GoalType goalType,
      Pageable pageable) {
    return goalRepo.findAllByUserIdAndGoalType(userId, goalType, pageable);
  }

  @Override
  public GoalDao getGoalById(String goalId) {
    GoalDao goalDao = goalRepo.findById(goalId).orElse(null);
    if (goalDao == null) {
      log.warn("could not find the goal by id {}", goalId);
    }
    log.debug("goal fetched against id {} : {}", goalId, goalDao);
    return goalDao;
  }
}
