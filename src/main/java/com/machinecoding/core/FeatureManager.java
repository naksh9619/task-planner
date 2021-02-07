package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.common.TaskPlannerException;
import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("feature")
public class FeatureManager extends AbstractTaskManager {

  private Map<String, String> nextStateMap;

  public FeatureManager() {
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
    return taskState.equals(State.DEPLOYED.getState());
  }

  public void nextStateInitializer() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.TESTING.getState());
    stateMap.put(State.TESTING.getState(), State.DEPLOYED.getState());
    this.nextStateMap = stateMap;
  }
}
