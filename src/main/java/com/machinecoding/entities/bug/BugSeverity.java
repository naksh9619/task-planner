package com.machinecoding.entities.bug;

import com.machinecoding.common.TaskPlannerException;

public enum  BugSeverity {
  P0("P0"), P1("P1"), P2("P2");

  private String severity;

  BugSeverity(String severity) {
    this.severity = severity;
  }

  public String getBugSeverity() {
    return severity;
  }

  public static BugSeverity getBugSeverityType(String severityStr) {
    for (BugSeverity bugSeverity: BugSeverity.values()) {
      if (bugSeverity.severity.equals(severityStr)) {
        return bugSeverity;
      }
    }
    throw new TaskPlannerException("Bug severity not supported.");
  }
}
