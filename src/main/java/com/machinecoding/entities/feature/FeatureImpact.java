package com.machinecoding.entities.feature;

public enum FeatureImpact {
  LOW("low"), MODERATE("moderate"), HIGH("high");

  private String impact;

  FeatureImpact(String impact) {
    this.impact = impact;
  }

  public String getFeatureImpact() {
    return impact;
  }
}
