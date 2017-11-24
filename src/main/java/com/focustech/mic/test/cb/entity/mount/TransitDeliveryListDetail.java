package com.focustech.mic.test.cb.entity.mount;

/**
 * @author caiwen
 */
public class TransitDeliveryListDetail {

  private Long cargoId;
  private String expectedQuantity;
  private String inventroyStatus;
  private String itemCode;
  private Long micDoCargoId;
  private String packageUnit;

  public Long getCargoId() {
    return cargoId;
  }

  public void setCargoId(Long cargoId) {
    this.cargoId = cargoId;
  }

  public String getExpectedQuantity() {
    return expectedQuantity;
  }

  public void setExpectedQuantity(String expectedQuantity) {
    this.expectedQuantity = expectedQuantity;
  }

  public String getInventroyStatus() {
    return inventroyStatus;
  }

  public void setInventroyStatus(String inventroyStatus) {
    this.inventroyStatus = inventroyStatus;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public Long getMicDoCargoId() {
    return micDoCargoId;
  }

  public void setMicDoCargoId(Long micDoCargoId) {
    this.micDoCargoId = micDoCargoId;
  }

  public String getPackageUnit() {
    return packageUnit;
  }

  public void setPackageUnit(String packageUnit) {
    this.packageUnit = packageUnit;
  }
}
