package com.machinecoding.utils;

import java.util.Map;
import java.util.UUID;

import com.machinecoding.common.TaskPlannerException;
import com.machinecoding.entities.Sprint;
import com.machinecoding.entities.State;
import com.machinecoding.entities.Task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskPlannerUtils {

  public TaskPlannerUtils() {

  }

  public static Task createTaskRequest(String title, String creator, String assignee, String type, String dueDate) {
    Task task = new Task();
    task.setType(type);
    task.setTitle(title);
    task.setCreator(creator);
    task.setDueDate(dueDate);
    task.setAssignee(assignee);
    task.setStatus(State.OPEN.getState());
    return task;
  }

  public static boolean isTaskTypeStory(Task task) {
    return task.getType().equals("story");
  }

  public static String getUniqueId() {
    return UUID.randomUUID().toString();
  }

  public static Task getTaskByTaskId(Map<String, Task> allTasks, String taskId) {
    Task task = allTasks.get(taskId);
    if (task == null) {
      log.error("Unable to get task by taskId");
      throw new TaskPlannerException("No task exists for given task id.");
    }
    return task;
  }

  public static Sprint validateAndGetSprintBySprintId(Map<String, Sprint> allSprints, String sprintId) {
    Sprint sprint = allSprints.get(sprintId);
    if (sprint == null) {
      log.error("Unable to get sprint by Sprint Id.");
      throw new TaskPlannerException("No sprint exists for given sprint id");
    }
    return sprint;
  }
}
