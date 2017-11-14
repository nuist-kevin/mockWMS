package com.focustech.mic.test.cb.entity.mount;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author caiwen
 */
public class AsnCargo {

  private Integer cargoId;
  private Integer expectedQuantity;
  private String inventoryStatus;
  private String itemCode;
  private Integer micAsnCagroId;
  private String packageUnit;
  private LocalDate productionDate;

  public Integer getCargoId() {
    return cargoId;
  }

  public void setCargoId(Integer cargoId) {
    this.cargoId = cargoId;
  }

  public Integer getExpectedQuantity() {
    return expectedQuantity;
  }

  public void setExpectedQuantity(Integer expectedQuantity) {
    this.expectedQuantity = expectedQuantity;
  }

  public String getInventoryStatus() {
    return inventoryStatus;
  }

  public void setInventoryStatus(String inventoryStatus) {
    this.inventoryStatus = inventoryStatus;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public Integer getMicAsnCagroId() {
    return micAsnCagroId;
  }

  public void setMicAsnCagroId(Integer micAsnCagroId) {
    this.micAsnCagroId = micAsnCagroId;
  }

  public String getPackageUnit() {
    return packageUnit;
  }

  public void setPackageUnit(String packageUnit) {
    this.packageUnit = packageUnit;
  }

  public LocalDate getProductionDate() {
    return productionDate;
  }

  public void setProductionDate(LocalDate productionDate) {
    this.productionDate = productionDate;
  }
}
