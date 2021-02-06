package com.machinecoding.entities.bug;

public enum  BugSeverity {
  P0("P0"), P1("P1"), P2("P2");

  private String severity;

  BugSeverity(String severity) {
    this.severity = severity;
  }

  public String getBugSeverity() {
    return severity;
  }
}
