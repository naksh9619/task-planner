package com.machinecoding.service;

import java.util.Map;

public interface TaskManagerService {

  String createNewTask(String title, String creator, String assignee,
                       String type, String dueDate, Map<String, String> metadata);

  String createNewSubTrack(String taskId, String title, String status);

  boolean changeTaskAssignee(String taskId, String newAssignee);

  void printAllTasksByAssignee();

  boolean changeTaskStatus(String taskId);
}
