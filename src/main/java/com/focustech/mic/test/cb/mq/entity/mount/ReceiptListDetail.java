package com.focustech.mic.test.cb.mq.entity.mount;

import java.time.LocalDate;

/**
 * @author caiwen
 */
public class ReceiptListDetail {

  private String inventoryStatus;
  private Long cargoId;
  private Long micAsnCagroId;
  private LocalDate productionDate;
  private Integer expectedQuantity;
  private String packageUnit;
  private String itemCode;

  public String getInventoryStatus() {
    return inventoryStatus;
  }

  public void setInventoryStatus(String inventoryStatus) {
    this.inventoryStatus = inventoryStatus;
  }

  public Long getCargoId() {
    return cargoId;
  }

  public void setCargoId(Long cargoId) {
    this.cargoId = cargoId;
  }

  public Long getMicAsnCagroId() {
    return micAsnCagroId;
  }

  public void setMicAsnCagroId(Long micAsnCagroId) {
    this.micAsnCagroId = micAsnCagroId;
  }

  public LocalDate getProductionDate() {
    return productionDate;
  }

  public void setProductionDate(LocalDate productionDate) {
    this.productionDate = productionDate;
  }

  public Integer getExpectedQuantity() {
    return expectedQuantity;
  }

  public void setExpectedQuantity(Integer expectedQuantity) {
    this.expectedQuantity = expectedQuantity;
  }

  public String getPackageUnit() {
    return packageUnit;
  }

  public void setPackageUnit(String packageUnit) {
    this.packageUnit = packageUnit;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }
}
