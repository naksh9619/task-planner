package com.machinecoding.core;

import com.machinecoding.common.TaskPlannerException;
import com.machinecoding.entities.Task;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class AbstractTaskManager implements TaskManager {

  public void changeTaskStatus(Task task) {
    String currentTaskStatus = task.getStatus();
    if (taskInTerminalState(currentTaskStatus)) {
      log.error("Task already in terminal state.");
      throw new TaskPlannerException("Unable to update task state. Task already in terminal state");
    }
    Map<String, String> nextStateMap = createNextStateMap();
    task.setStatus(nextStateMap.get(currentTaskStatus));
  }

  protected abstract Map<String, String> createNextStateMap();
}
