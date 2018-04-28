package com.focustech.mic.test.cb.mq.entity.mount;

import com.focustech.mic.test.cb.mq.entity.BusinessType;
import java.util.List;

/**
 * @author caiwen
 */
public class Item {
  private String barCode;
  private String baseUnit;
  private String batchRule;
  private BusinessType businessType;
  private String class1;
  private String code;
  private String companyCode;
  private Long micCargoId;
  private Long micComId;
  private String name;
  private Integer precision;
  private List<TransitPackageUnit> transitPackageUnits;
  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBarCode() {
    return barCode;
  }

  public void setBarCode(String barCode) {
    this.barCode = barCode;
  }

  public String getBaseUnit() {
    return baseUnit;
  }

  public void setBaseUnit(String baseUnit) {
    this.baseUnit = baseUnit;
  }

  public String getBatchRule() {
    return batchRule;
  }

  public void setBatchRule(String batchRule) {
    this.batchRule = batchRule;
  }

  public BusinessType getBusinessType() {
    return businessType;
  }

  public void setBusinessType(BusinessType businessType) {
    this.businessType = businessType;
  }

  public String getClass1() {
    return class1;
  }

  public void setClass1(String class1) {
    this.class1 = class1;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }

  public Long getMicCargoId() {
    return micCargoId;
  }

  public void setMicCargoId(Long micCargoId) {
    this.micCargoId = micCargoId;
  }

  public Long getMicComId() {
    return micComId;
  }

  public void setMicComId(Long micComId) {
    this.micComId = micComId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrecision() {
    return precision;
  }

  public void setPrecision(Integer precision) {
    this.precision = precision;
  }

  public List<TransitPackageUnit> getTransitPackageUnits() {
    return transitPackageUnits;
  }

  public void setTransitPackageUnits(List<TransitPackageUnit> transitPackageUnits) {
    this.transitPackageUnits = transitPackageUnits;
  }
}
