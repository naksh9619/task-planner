package com.machinecoding.service;

public interface TaskManagerService {

  String createNewTask(String title, String creator, String assignee, String type, String dueDate);

  String createNewSubTrack(String taskId, String title, String status);

  boolean changeTaskAssignee(String taskId, String newAssignee);

  void printAllTasksByAssignee();

  boolean changeTaskStatus(String taskId);
}
