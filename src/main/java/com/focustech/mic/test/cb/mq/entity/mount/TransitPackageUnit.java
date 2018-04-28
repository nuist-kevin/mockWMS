package com.focustech.mic.test.cb.mq.entity.mount;

import java.math.BigDecimal;

/**
 * @author caiwen
 */
public class TransitPackageUnit {
  private Integer convertFigure;
  private BigDecimal height;
  private BigDecimal length;
  private Long micPackageKey;
  private String unit;
  private BigDecimal volume;
  private BigDecimal weight;
  private BigDecimal width;

  public Integer getConvertFigure() {
    return convertFigure;
  }

  public void setConvertFigure(Integer convertFigure) {
    this.convertFigure = convertFigure;
  }

  public BigDecimal getHeight() {
    return height;
  }

  public void setHeight(BigDecimal height) {
    this.height = height;
  }

  public BigDecimal getLength() {
    return length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  public Long getMicPackageKey() {
    return micPackageKey;
  }

  public void setMicPackageKey(Long micPackageKey) {
    this.micPackageKey = micPackageKey;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public void setVolume(BigDecimal volume) {
    this.volume = volume;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public BigDecimal getWidth() {
    return width;
  }

  public void setWidth(BigDecimal width) {
    this.width = width;
  }
}
