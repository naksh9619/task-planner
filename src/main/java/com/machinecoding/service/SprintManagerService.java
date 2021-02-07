package com.machinecoding.service;

public interface SprintManagerService {

  String createNewSprint(String sprintName);

  boolean deleteSprint(String sprintId);

  void addTaskToSprint(String sprintId, String taskId);

  boolean deleteTaskFromSprint(String sprintId, String taskId);

  void printAllSprintTasksByStatus(String sprintId);
}
