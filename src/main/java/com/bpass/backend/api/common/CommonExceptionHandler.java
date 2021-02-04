package com.bpass.backend.api.common;

import com.bpass.backend.api.common.exception.ThisIsNotYoursException;
import com.bpass.backend.api.common.response.ErrorResponse;
import java.util.Objects;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonExceptionHandler {

  @ExceptionHandler(ThisIsNotYoursException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleNotYours(ThisIsNotYoursException exception) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST, "1001", exception.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleNotYours(HttpMessageNotReadableException exception) {
    String message = Objects.requireNonNull(exception.getRootCause()).getMessage();
    return new ErrorResponse(HttpStatus.BAD_REQUEST, "2001", message.split("\\(class")[0]);
  }
}
