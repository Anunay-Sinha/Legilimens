package com.deeaae.legilimens.user.controller;

import com.deeaae.legilimens.common.model.response.CommonResponse;
import com.deeaae.legilimens.common.model.response.CommonResponse.FailureResponse;
import com.deeaae.legilimens.user.model.User;
import com.deeaae.legilimens.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/default", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> getDefaultUser() {
    log.info("requesting data for default user");
    String userId = "DefaultUser";
    CommonResponse<String> commonResponse = CommonResponse.success("userId");
    return ResponseEntity.ok(commonResponse);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<CommonResponse<?>> createUser(@RequestBody User user) {
    try {
      user = userService.createUser(user);
      CommonResponse<User> commonResponse = CommonResponse.success(user);
      return ResponseEntity.ok(commonResponse);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);

    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> getUserById(@PathVariable String id) {
    try {
      User user = userService.getUserById(id);
      if (user == null) {
        throw new RuntimeException("User not available");
      }
      CommonResponse<User> commonResponse = CommonResponse.success(user);
      return ResponseEntity.ok(commonResponse);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);

    }

  }

  @RequestMapping(value = "/email/{emailId}", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> getUserByEmailId(@PathVariable String emailId) {
    try {
      User user = userService.getUserByEmailId(emailId);
      if (user == null) {
        throw new RuntimeException("User not available");
      }
      CommonResponse<User> commonResponse = CommonResponse.success(user);
      return ResponseEntity.ok(commonResponse);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);

    }

  }

  @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> getUserByUsername(@PathVariable String username) {
    try {
      User user = userService.getUserByUsername(username);
      if (user == null) {
        throw new RuntimeException("User not available");
      }
      CommonResponse<User> commonResponse = CommonResponse.success(user);
      return ResponseEntity.ok(commonResponse);
    } catch (Exception ex) {
      CommonResponse<FailureResponse> failedResponse = CommonResponse
          .failure(ex.getMessage(), 400);
      return ResponseEntity.badRequest().body(failedResponse);

    }

  }

}
