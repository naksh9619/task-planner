package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.common.TaskPlannerException;
import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("bug")
public class BugManager extends AbstractTaskManager {

  private Map<String, String> nextStateMap;

  public BugManager() {
    nextStateInitializer();
  }

  @Override
  public boolean createTask(Task task) {
    return false;
  }

  @Override
  public void changeTaskStatus(Task task) {
    String currentTaskStatus = task.getStatus();
    if (taskInTerminalState(currentTaskStatus)) {
      log.error("Task already in terminal state.");
      throw new TaskPlannerException("Unable to update task state. Task already in terminal state");
    }
    task.setStatus(nextStateMap.get(currentTaskStatus));
  }

  @Override
  public boolean taskInTerminalState(String taskState) {
    return taskState.equals(State.FIXED.getState());
  }

  private void nextStateInitializer() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.TO_DO.getState());
    stateMap.put(State.TO_DO.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.FIXED.getState());
    this.nextStateMap = stateMap;
  }
}
