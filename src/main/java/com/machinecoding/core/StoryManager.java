package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;
import com.machinecoding.entities.story.Story;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("story")
public class StoryManager extends AbstractTaskManager {

  public StoryManager() {
  }

  @Override
  public boolean createTask(Task task) {
    Story story = new Story();
    story.setSummary(task.getTitle());
    return true;
  }

  @Override
  public boolean taskInTerminalState(String taskState) {
    return taskState.equals(State.COMPLETED.getState());
  }

  @Override
  protected Map<String, String> getNextStatesMap() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.COMPLETED.getState());
    return stateMap;
  }
}
