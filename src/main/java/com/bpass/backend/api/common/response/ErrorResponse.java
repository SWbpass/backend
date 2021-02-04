package com.bpass.backend.api.common.response;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

  private final LocalDateTime timestamp;

  private final int status;

  private final String error;

  private final String message;

  public ErrorResponse(HttpStatus httpStatus, String errCode, String message) {
    this.timestamp = LocalDateTime.now();
    this.status = httpStatus.value();
    this.error = errCode;
    this.message = message;
  }


}
