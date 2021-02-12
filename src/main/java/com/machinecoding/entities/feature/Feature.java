package com.machinecoding.entities.feature;

import com.machinecoding.entities.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Feature extends Task {

  private String summary;

  private FeatureImpact impact;
}
