package com.bpass.backend.api.visit.exception;

public class VisitsNotExistsException extends RuntimeException {
  public VisitsNotExistsException() {
    super("방문정보가 존재하지 않습니다.");
  }
}
