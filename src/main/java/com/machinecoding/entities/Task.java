package com.machinecoding.entities;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;

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
  private String status;

  private String type;

  private String dueDate;

  private String metaData = StringUtils.EMPTY;

  private String sprint = StringUtils.EMPTY;
}
