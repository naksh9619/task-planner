package com.machinecoding.core;

import com.machinecoding.entities.Task;

import java.util.Map;

public interface TaskManager {

  Task createTask(String title, String creator, String assignee,
                  String type, String dueDate, Map<String, String> metadata);

  void changeTaskStatus(Task task);

  boolean taskInTerminalState(String taskState);
}
