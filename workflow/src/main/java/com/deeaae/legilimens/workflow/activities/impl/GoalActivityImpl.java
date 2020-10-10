package com.deeaae.legilimens.workflow.activities.impl;

import com.deeaae.legilimens.workflow.activities.GoalActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class GoalActivityImpl implements GoalActivity {

  private static ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  private RestTemplate restTemplate;
  @Value("${services.goal.url:http://localhost:9091}")
  private String goalServiceURL;
  @Value("${services.goal.contextPath:/legilimens/goal_service}")
  private String goalServiceContextPath;
  @Value("${services.goal.getGoalRequest:/v1/goals/}")
  private String getGoalRequest;
  @Value("${services.goal.checkGoalCreation:/v1/goals/check/isCreationComplete}")
  private String checkGoalCreation;
  @Value("${services.goal.checkGoalCompletion:/v1/goals/check/isGoalComplete}")
  private String checkGoalCompletion;

  @Override
  public boolean isGoalAvailable(String goalId) {
    String url = goalServiceURL + goalServiceContextPath + getGoalRequest + goalId;
    try {
      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
      log.debug("response body {}", response.getBody());
    } catch (HttpStatusCodeException ex) {
      int statusCode = ex.getStatusCode().value();
      log.warn("status code {}", ex.getStatusCode());
      log.debug("failure response {}", ex.getResponseBodyAsString());
      if (statusCode == 400) {
        return false;
      }
    } catch (Exception ex) {
      log.error("error in fetching goal {} | {}", goalId, ex.getMessage());
    }
    return true;
  }

  @Override
  public boolean isGoalComplete(String goalId) {
    String url = goalServiceURL + goalServiceContextPath + checkGoalCompletion + "?id=" + goalId;
    try {
      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
      log.debug("response body {}", response.getBody());
      Boolean data = objectMapper.readTree(response.getBody()).get("response").asBoolean();
      return data;
    } catch (HttpStatusCodeException ex) {
      int statusCode = ex.getStatusCode().value();
      log.warn("status code {}", ex.getStatusCode());
      log.debug("failure response {}", ex.getResponseBodyAsString());
      if (statusCode == 400) {
        return false;
      }
    } catch (Exception ex) {
      log.error("error in fetching goal {} | {}", goalId, ex.getMessage());
    }
    return false;
  }

  @Override
  public boolean isGoalCreationComplete(String goalId) {
    String url = goalServiceURL + goalServiceContextPath + checkGoalCreation + "?id=" + goalId;
    try {
      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
      log.debug("response body {}", response.getBody());
      Boolean data = objectMapper.readTree(response.getBody()).get("response").asBoolean();
      return data;
    } catch (HttpStatusCodeException ex) {
      int statusCode = ex.getStatusCode().value();
      log.warn("status code {}", ex.getStatusCode());
      log.debug("failure response {}", ex.getResponseBodyAsString());
      if (statusCode == 400) {
        return false;
      }
    } catch (Exception ex) {
      log.error("error in fetching goal {} | {}", goalId, ex.getMessage());
    }
    return false;
  }

  @Override
  public boolean onTaskCompletion(String goalId, String taskId) {
    return false;
  }

  @Override
  public boolean updateRAGStatus(String goalId) {
    return false;
  }
}
