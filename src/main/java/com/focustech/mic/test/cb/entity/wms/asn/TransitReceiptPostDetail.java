package com.focustech.mic.test.cb.entity.wms.asn;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author caiwen
 */
public class TransitReceiptPostDetail {

  private Integer cargoId;
  private BigDecimal expectedQuantityBU;
  private Integer inventoryStatus;
  private String itemCode;
  private String lotNo;
  private String packageUnit;
  private LocalDate productDate;
  private BigDecimal receivedQuantityBU;
  private LocalDate storageDate;

  public LocalDate getProductDate() {
    return productDate;
  }

  public void setProductDate(LocalDate productDate) {
    this.productDate = productDate;
  }

  public Integer getCargoId() {
    return cargoId;
  }

  public void setCargoId(Integer cargoId) {
    this.cargoId = cargoId;
  }

  public BigDecimal getExpectedQuantityBU() {
    return expectedQuantityBU;
  }

  public void setExpectedQuantityBU(BigDecimal expectedQuantityBU) {
    this.expectedQuantityBU = expectedQuantityBU;
  }

  public Integer getInventoryStatus() {
    return inventoryStatus;
  }

  public void setInventoryStatus(Integer inventoryStatus) {
    this.inventoryStatus = inventoryStatus;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public String getLotNo() {
    return lotNo;
  }

  public void setLotNo(String lotNo) {
    this.lotNo = lotNo;
  }

  public String getPackageUnit() {
    return packageUnit;
  }

  public void setPackageUnit(String packageUnit) {
    this.packageUnit = packageUnit;
  }

  public BigDecimal getReceivedQuantityBU() {
    return receivedQuantityBU;
  }

  public void setReceivedQuantityBU(BigDecimal receivedQuantityBU) {
    this.receivedQuantityBU = receivedQuantityBU;
  }

  public LocalDate getStorageDate() {
    return storageDate;
  }

  public void setStorageDate(LocalDate storageDate) {
    this.storageDate = storageDate;
  }
}
