package com.machinecoding.common;

public class TaskPlannerException extends RuntimeException {

  public TaskPlannerException(String msg) {
    super(msg);
  }

  public TaskPlannerException(Throwable throwable) {
    super(throwable);
  }

  public TaskPlannerException(String msg, Throwable throwable) {
    super(msg, throwable);
  }
}
