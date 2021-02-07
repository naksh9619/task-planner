package com.machinecoding.core;

import com.machinecoding.entities.Task;

public interface TaskManager {

  boolean createTask(Task task);

  void changeTaskStatus(Task task);

  boolean taskInTerminalState(String taskState);
}
