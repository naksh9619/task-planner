package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;
import com.machinecoding.entities.feature.Feature;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import static com.machinecoding.common.TaskPlannerConstants.IMPACT;
import static com.machinecoding.common.TaskPlannerConstants.SUMMARY;
import static com.machinecoding.entities.feature.FeatureImpact.getFeatureImpactType;

@Slf4j
@Component("feature")
public class FeatureManager extends AbstractTaskManager {

  public FeatureManager() {
  }

  @Override
  public Task createTask(String title, String creator, String assignee,
                         String type, String dueDate, Map<String, String> metadata) {
    return createFeatureTask(title, creator, assignee, type, dueDate, metadata);
  }

  @Override
  public boolean taskInTerminalState(String taskState) {
    return taskState.equals(State.DEPLOYED.getState());
  }

  @Override
  protected Map<String, String> createNextStateMap() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.TESTING.getState());
    stateMap.put(State.TESTING.getState(), State.DEPLOYED.getState());
    return stateMap;
  }

  private Feature createFeatureTask(String title, String creator, String assignee,
                                    String type, String dueDate, Map<String, String> metadata) {
    Feature feature = new Feature();
    feature.setType(type);
    feature.setTitle(title);
    feature.setCreator(creator);
    feature.setDueDate(dueDate);
    feature.setAssignee(assignee);
    feature.setStatus(State.OPEN.getState());
    feature.setSummary(metadata.get(SUMMARY));
    feature.setImpact(getFeatureImpactType(metadata.get(IMPACT)));
    return feature;
  }
}
