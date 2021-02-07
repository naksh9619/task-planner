package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.common.TaskPlannerException;
import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;
import com.machinecoding.entities.story.Story;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("story")
public class StoryManager extends AbstractTaskManager {

  private Map<String, String> nextStateMap;

  public StoryManager() {
    nextStateInitializer();
  }
  @Override
  public boolean createTask(Task task) {
    Story story = new Story();
    story.setSummary(task.getTitle());
    return true;
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
    return taskState.equals(State.COMPLETED.getState());
  }

  private void nextStateInitializer() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.COMPLETED.getState());
    this.nextStateMap = stateMap;
  }
}
