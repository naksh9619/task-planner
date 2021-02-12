package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;
import com.machinecoding.entities.bug.Bug;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import static com.machinecoding.common.TaskPlannerConstants.SEVERITY;
import static com.machinecoding.entities.bug.BugSeverity.getBugSeverityType;

@Slf4j
@Component("bug")
public class BugManager extends AbstractTaskManager {

  public BugManager() {
  }

  @Override
  public Task createTask(String title, String creator, String assignee,
                         String type, String dueDate, Map<String, String> metadata) {
    return createBugTask(title, creator, assignee, type, dueDate, metadata);
  }

  @Override
  public boolean taskInTerminalState(String taskState) {
    return taskState.equals(State.FIXED.getState());
  }

  @Override
  protected Map<String, String> createNextStateMap() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.TO_DO.getState());
    stateMap.put(State.TO_DO.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.FIXED.getState());
    return stateMap;
  }

  private Bug createBugTask(String title, String creator, String assignee,
                            String type, String dueDate, Map<String, String> metadata) {
    Bug bug = new Bug();
    bug.setType(type);
    bug.setTitle(title);
    bug.setCreator(creator);
    bug.setDueDate(dueDate);
    bug.setAssignee(assignee);
    bug.setStatus(State.OPEN.getState());
    bug.setSeverity(getBugSeverityType(metadata.get(SEVERITY)));
    return bug;
  }
}
