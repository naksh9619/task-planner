package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("feature")
public class FeatureManager extends AbstractTaskManager {

  public FeatureManager() {
  }

  @Override
  public boolean createTask(Task task) {
    return false;
  }

  @Override
  public boolean taskInTerminalState(String taskState) {
    return taskState.equals(State.DEPLOYED.getState());
  }

  @Override
  protected Map<String, String> getNextStatesMap() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.TESTING.getState());
    stateMap.put(State.TESTING.getState(), State.DEPLOYED.getState());
    return stateMap;
  }
}
