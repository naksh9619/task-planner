package com.machinecoding.core;

import java.util.HashMap;
import java.util.Map;

import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;
import com.machinecoding.entities.story.Story;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import static com.machinecoding.common.TaskPlannerConstants.SUMMARY;


@Slf4j
@Component("story")
public class StoryManager extends AbstractTaskManager {

  public StoryManager() {
  }

  @Override
  public Task createTask(String title, String creator, String assignee,
                         String type, String dueDate, Map<String, String> metadata) {
    return createStoryTask(title, creator, assignee, type, dueDate, metadata);
  }

  @Override
  public boolean taskInTerminalState(String taskState) {
    return taskState.equals(State.COMPLETED.getState());
  }

  @Override
  protected Map<String, String> createNextStateMap() {
    Map<String, String> stateMap = new HashMap<>();
    stateMap.put(State.OPEN.getState(), State.IN_PROGRESS.getState());
    stateMap.put(State.IN_PROGRESS.getState(), State.COMPLETED.getState());
    return stateMap;
  }

  private Story createStoryTask(String title, String creator, String assignee,
                                String type, String dueDate, Map<String, String> metadata) {
    Story story = new Story();
    story.setType(type);
    story.setTitle(title);
    story.setCreator(creator);
    story.setDueDate(dueDate);
    story.setAssignee(assignee);
    story.setStatus(State.OPEN.getState());
    story.setSummary(metadata.get(SUMMARY));
    return story;
  }
}
