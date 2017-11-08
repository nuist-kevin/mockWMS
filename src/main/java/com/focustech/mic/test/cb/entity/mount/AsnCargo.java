package com.focustech.mic.test.cb.entity.mount;

import java.util.Date;

/**
 * @author caiwen
 */
public class AsnCargo {

  private Long cargoId;
  private Long expectedQuantity;
  private String inventoryStatus;
  private String itemCode;
  private Long micAsnCagroId;
  private String packageUnit;
  private Date productionDate;

  public Long getCargoId() {
    return cargoId;
  }

  public void setCargoId(Long cargoId) {
    this.cargoId = cargoId;
  }

  public Long getExpectedQuantity() {
    return expectedQuantity;
  }

  public void setExpectedQuantity(Long expectedQuantity) {
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

  public Long getMicAsnCagroId() {
    return micAsnCagroId;
  }

  public void setMicAsnCagroId(Long micAsnCagroId) {
    this.micAsnCagroId = micAsnCagroId;
  }

  public String getPackageUnit() {
    return packageUnit;
  }

  public void setPackageUnit(String packageUnit) {
    this.packageUnit = packageUnit;
  }

  public Date getProductionDate() {
    return productionDate;
  }

  public void setProductionDate(Date productionDate) {
    this.productionDate = productionDate;
  }
}
