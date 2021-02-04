package com.bpass.backend.api.user.exception;

public class InvalidStoreException extends RuntimeException {
  public InvalidStoreException() {
    super("존재하지 않거나 제제당한 매장입니다.");
  }
}
