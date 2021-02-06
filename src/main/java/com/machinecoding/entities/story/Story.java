package com.machinecoding.entities.story;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Story {

  private String summary;

  private List<Subtrack> subtracks;
}
