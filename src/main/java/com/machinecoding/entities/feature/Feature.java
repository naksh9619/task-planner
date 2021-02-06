package com.machinecoding.entities.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Feature {
  private String summary;

  private FeatureImpact impact;


}
