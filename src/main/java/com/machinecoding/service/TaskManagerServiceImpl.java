package com.machinecoding.service;

import static com.machinecoding.utils.TaskPlannerUtils.createTask;
import static com.machinecoding.utils.TaskPlannerUtils.getTaskByTaskId;
import static com.machinecoding.utils.TaskPlannerUtils.getUniqueId;
import static com.machinecoding.utils.TaskPlannerUtils.isTaskTypeStory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.machinecoding.common.TaskPlannerException;
import com.machinecoding.core.TaskManager;
import com.machinecoding.entities.Task;
import com.machinecoding.entities.story.Subtrack;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskManagerServiceImpl implements TaskManagerService {


  private Map <String, Task> allTasks;

  private Map<String, Subtrack> subTracks;

  private Map<String, TaskManager> taskManagerMap;

  public TaskManagerServiceImpl(Map<String, TaskManager> taskManagerMap) {
    this.taskManagerMap = taskManagerMap;
    this.allTasks = new HashMap<String, Task>();
    this.subTracks = new HashMap<String, Subtrack>();
  }

  @Override
  public String createNewTask(String title, String creator, String assignee, String type, String dueDate) {
    Task task = createTask(title, creator, assignee, type, dueDate);
    TaskManager taskManager = getTaskManagerByTaskType(type);
    boolean taskCreated = taskManager.createTask(task);
    if (!taskCreated) {
      log.error("Unable to create task");
      throw new TaskPlannerException("Exception occurred while trying to create task of type " + type);
    }
    String taskId = getUniqueId();
    allTasks.put(taskId, task);
    return taskId;
  }

  @Override
  public String createNewSubTrack(String taskId, String title, String status) {
    Task task = getTaskByTaskId(allTasks, taskId);
    if (!isTaskTypeStory(task)) {
      log.error("Unable to create subtrack as task type is not story ");
      throw new TaskPlannerException("Unable to create subtrack.");
    }
    Subtrack subtrack = new Subtrack(title, status);
    subTracks.put(taskId, subtrack);
    return taskId;
  }

  @Override
  public boolean changeTaskAssignee(String taskId, String newAssignee) {
    Task task = getTaskByTaskId(allTasks, taskId);
    if (taskManagerMap.get(task.getType()).taskInTerminalState(task.getStatus())) {
      log.error("Task already in terminal state.");
      throw new TaskPlannerException("Exception occurred while changing assignee as task already in terminal state.");
    }
    task.setAssignee(newAssignee);
    return true;
  }

  @Override
  public void printAllTasksByAssignee() {
    Map < String, List<Task> > allTasksByAssignee = new HashMap<String, List<Task>>();
    for (Task task: allTasks.values()) {
      List<Task> tasksByAssignee = allTasksByAssignee.get(task.getAssignee());
      if (tasksByAssignee == null) {
        tasksByAssignee = new ArrayList<Task>();
      }
      tasksByAssignee.add(task);
    }

    for (Map.Entry<String, List<Task>> entry: allTasksByAssignee.entrySet()) {
      System.out.println("All tasks for " + entry.getKey());
      for (Task task: entry.getValue()) {
        System.out.println(task.getTitle() + " " + task.getType());
      }
      System.out.println();
    }
  }

  @Override
  public boolean changeTaskStatus(String taskId) {
    Task task = getTaskByTaskId(allTasks, taskId);
    TaskManager taskManager = getTaskManagerByTaskType(task.getType());
    taskManager.changeTaskStatus(task);
    return false;
  }

  private TaskManager getTaskManagerByTaskType(String taskType) {
    TaskManager taskManager = taskManagerMap.get(taskType);
    if (taskManager == null) {
      log.error("Task of type {} not supported.", taskType);
      throw new TaskPlannerException("No task manager found for type: " + taskType);
    }
    return taskManager;
  }
}
