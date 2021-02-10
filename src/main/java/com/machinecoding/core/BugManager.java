package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("bug")
public class BugManager extends AbstractTaskManager {
  public BugManager() {
  }

  @Override
  public boolean createTask(Task task) {
    return false;
  }

  @Override
  public boolean taskInTerminalState(String taskState) {
    return taskState.equals(State.FIXED.getState());
  }

  @Override
  protected Map<String, String> getNextStatesMap() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.TO_DO.getState());
    stateMap.put(State.TO_DO.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.FIXED.getState());
    return stateMap;
  }
}
