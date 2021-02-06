package com.machinecoding.entities;

public enum State {
  OPEN("open"), IN_PROGRESS("open-progress"), TO_DO("to-do"), FIXED("fixed"),
  COMPLETED("completed"), TESTING("testing"), DEPLOYED("deployed");

  private String state;

  State(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }
}
