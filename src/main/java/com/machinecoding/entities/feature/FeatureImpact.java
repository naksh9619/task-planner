package com.machinecoding.entities.feature;

import com.machinecoding.common.TaskPlannerException;

public enum FeatureImpact {
  LOW("low"), MODERATE("moderate"), HIGH("high");

  private String impact;

  FeatureImpact(String impact) {
    this.impact = impact;
  }

  public String getFeatureImpact() {
    return impact;
  }

  public static FeatureImpact getFeatureImpactType(String impactStr) {
    for (FeatureImpact featureImpact: FeatureImpact.values()) {
      if (featureImpact.impact.equals(impactStr)) {
        return featureImpact;
      }
    }
    throw new TaskPlannerException("Feature Impact not supported.");
  }
}
