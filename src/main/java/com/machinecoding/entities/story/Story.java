package com.machinecoding.entities.story;

import java.util.ArrayList;
import java.util.List;

import com.machinecoding.entities.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Story extends Task {

  private String summary;

  private List<Subtrack> subtracks = new ArrayList<>();
}
