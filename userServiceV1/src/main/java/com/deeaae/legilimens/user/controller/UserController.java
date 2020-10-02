package com.deeaae.legilimens.user.controller;

import com.deeaae.legilimens.common.model.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController {

  @RequestMapping(value = "/default", method = RequestMethod.GET)
  public ResponseEntity<CommonResponse<?>> getDefaultUser() {
    log.info("requesting data for default user");
    String userId = "DefaultUser";
    CommonResponse<String> commonResponse = CommonResponse.success("userId");
    return ResponseEntity.ok(commonResponse);
  }

}
