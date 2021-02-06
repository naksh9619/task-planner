package com.machinecoding.entities;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

  @NotNull
  private String title;

  @NotNull
  private String creator;

  private String assignee;

  @NotNull
  private State status;

  private String type;

  private String dueDate;

  private String sprint;
}
