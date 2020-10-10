package com.deeaae.legilimens.goals.controller;

import com.deeaae.legilimens.common.model.response.CommonResponse;
import com.deeaae.legilimens.common.model.response.CommonResponse.FailureResponse;
import com.deeaae.legilimens.goals.model.GoalStatus;
import com.deeaae.legilimens.goals.model.dao.GoalDao;
import com.deeaae.legilimens.goals.service.GoalService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/goals")
public class GoalController {

  @Autowired
  private GoalService goalService;



  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<CommonResponse<?>> createGoal(@RequestBody GoalDao goalDao) {
    try {
      goalDao = goalService.createGoal(goalDao);
      CommonResponse<GoalDao> response = CommonResponse.success(goalDao);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/check/isCreationComplete", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> isGoalCreated(@RequestParam String id) {
    try {
      GoalDao goalDao = goalService.getGoalById(id);
      if (goalDao == null) {
        throw new RuntimeException("Goal not exist with the given id");
      }
      boolean responseFlag = goalDao.getStatus().equals(GoalStatus.INITIALIZED)?false:true;
      CommonResponse<Boolean> response = CommonResponse.success(responseFlag);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/check/isGoalComplete", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> isGoalCompleted(@RequestParam String id) {
    try {
      GoalDao goalDao = goalService.getGoalById(id);
      if (goalDao == null) {
        throw new RuntimeException("Goal not exist with the given id");
      }
      boolean responseFlag = goalDao.getStatus().equals(GoalStatus.CLOSED_ON_SUCCESS)?true:false;
      CommonResponse<Boolean> response = CommonResponse.success(responseFlag);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/mark/creationComplete", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> markGoalCreated(@RequestParam String id) {
    try {
      GoalDao goalDao = goalService.markCreationComplete(id);
      if (goalDao == null) {
        throw new RuntimeException("Goal not exist with the given id");
      }
      CommonResponse<GoalDao> response = CommonResponse.success(goalDao);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/mark/complete", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> markGoalCompleted(@RequestParam String id) {
    try {
      GoalDao goalDao = goalService.markComplete(id);
      if (goalDao == null) {
        throw new RuntimeException("Goal not exist with the given id");
      }
      CommonResponse<GoalDao> response = CommonResponse.success(goalDao);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> getGoal(@PathVariable String id) {
    try {
      GoalDao goalDao = goalService.getGoalById(id);
      if (goalDao == null) {
        throw new RuntimeException("Goal not exist with the given id");
      }
      CommonResponse<GoalDao> response = CommonResponse.success(goalDao);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> getGoals(Pageable pageable) {
    try {
      Page<GoalDao> goalDaos = goalService.getAllGoals(pageable);

      CommonResponse<Page<GoalDao>> response = CommonResponse.success(goalDaos);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> getGoalsByUser(@PathVariable String userId,
      Pageable pageable) {
    try {
      Page<GoalDao> goalDaos = goalService.getAllGoalsForUser(userId, pageable);
      CommonResponse<Page<GoalDao>> response = CommonResponse.success(goalDaos);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/{id}/task/{taskId}", method = RequestMethod.PUT)
  public ResponseEntity<CommonResponse<?>> addTracker(@PathVariable String id,
      @PathVariable String taskId) {
    try {
      GoalDao goalDao = goalService.addTask(id, taskId);
      CommonResponse<GoalDao> response = CommonResponse.success(goalDao);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<CommonResponse<?>> addTracker(@PathVariable String id,
      @RequestBody GoalDao goalDao) {
    try {
      goalDao = goalService.saveGoal(goalDao);
      CommonResponse<GoalDao> response = CommonResponse.success(goalDao);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }

  @RequestMapping(value = "/{id}/alert/{alertId}", method = RequestMethod.PUT)
  public ResponseEntity<CommonResponse<?>> addAlert(@PathVariable String id,
      @PathVariable String alertId) {
    try {
      GoalDao goalDao = goalService.addAlert(id, alertId);
      CommonResponse<GoalDao> response = CommonResponse.success(goalDao);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);
    }
  }


}
