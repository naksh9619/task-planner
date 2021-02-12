package com.machinecoding.entities.bug;

import com.machinecoding.entities.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bug extends Task {

  private BugSeverity severity;
}
