package com.deeaae.legilimens.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CommonResponse<T> {

  /*
   * status of the response
   * 0 : Success
   * non zero positive response implies failure
   */
  private int status;
  /*
   * response object
   * might be populated when status is 0
   */
  private T response;
  /*
   * failure response object
   * might be populated when status is non zero
   */
  private FailureResponse failureResponse;

  /*
   * gets the CommonResponse instance for success response. satus will be 0.
   * @param response Response instance to be added to commonResponse
   * @return CommonResponse instance
   */
  public static <T> CommonResponse<T> success(T response) {
    CommonResponse<T> commonResponse = new CommonResponse<>();
    commonResponse.setStatus(0);
    commonResponse.setResponse(response);
    return commonResponse;
  }
}

@Data
class FailureResponse {

  /*
   * error code for the failure
   */
  private int errorCode;
  /*
   * Short desc about the failure
   */
  private String message;
}
