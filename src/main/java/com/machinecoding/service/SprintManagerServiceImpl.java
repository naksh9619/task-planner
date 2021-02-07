package com.machinecoding.service;

import static com.machinecoding.utils.TaskPlannerUtils.getTaskByTaskId;
import static com.machinecoding.utils.TaskPlannerUtils.getUniqueId;
import static com.machinecoding.utils.TaskPlannerUtils.validateAndGetSprintBySprintId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.machinecoding.entities.Sprint;
import com.machinecoding.entities.Task;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SprintManagerServiceImpl implements SprintManagerService {

  private Map<String, Sprint> allSprints;

  private Map<String, Task> allTasks;

  public SprintManagerServiceImpl(Map<String, Task> allTasks) {
    this.allSprints = new HashMap<String, Sprint>();
    this.allTasks = allTasks;
  }

  @Override
  public String createNewSprint(String sprintName) {
    String sprintId = getUniqueId();
    allSprints.put(sprintId, new Sprint());
    return sprintId;
  }

  @Override
  public boolean deleteSprint(String sprintId) {
    Sprint sprint = validateAndGetSprintBySprintId(allSprints, sprintId);
    allSprints.remove(sprintId);
    unTagTasksForSprint(sprint.getTasks());
    return false;
  }

  @Override
  public void addTaskToSprint(String sprintId, String taskId) {
    Task task = getTaskByTaskId(allTasks, taskId);
    task.setSprint(sprintId);
    allTasks.put(taskId, task);
    Sprint sprint = validateAndGetSprintBySprintId(allSprints, sprintId);
    List<Task> allTasksBySprint = sprint.getTasks();
    allTasksBySprint.add(task);
  }

  @Override
  public boolean deleteTaskFromSprint(String sprintId, String taskId) {
    validateAndGetSprintBySprintId(allSprints, sprintId);
    Task task = getTaskByTaskId(allTasks, taskId);
    if (!task.getSprint().equals(sprintId)) {
      log.warn("Unable to update sprint to task. Task associated to some other sprint.");
      return false;
    }
    task.setSprint(StringUtils.EMPTY);
    return true;
  }

  @Override
  public void printAllSprintTasksByStatus(String sprintId) {
    Sprint sprint = validateAndGetSprintBySprintId(allSprints, sprintId);
    Map<String, List<Task>> allTasksByStatus = new HashMap<>();
    List<Task> allTasks = sprint.getTasks();
    for (Task task: allTasks) {
      List<Task> tasksByStatus = allTasksByStatus.get(task.getStatus());
      if (tasksByStatus.isEmpty()) {
        tasksByStatus = new ArrayList<>();
      }
      tasksByStatus.add(task);
    }
    for (Map.Entry<String, List<Task>> entry: allTasksByStatus.entrySet()) {
      System.out.println("Status: " + entry.getKey());
      List<Task> tasks = entry.getValue();
      for (Task task: tasks) {
        System.out.print(task.getTitle() + " ");
      }
      System.out.println();
    }
  }

  private void unTagTasksForSprint(List<Task> allTasks) {
    for (Task task: allTasks) {
      task.setSprint(StringUtils.EMPTY);
    }
  }
}
