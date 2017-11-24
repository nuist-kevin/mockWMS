package com.focustech.mic.test.cb.entity.wms.order;

import java.math.BigDecimal;

/**
 * @author caiwen
 */
public class PostingDetail {

  /**
   * 库存状态：正常状态
   */
  public static Short WMS_INVENTORY_STATUS_NORMAL = 1;

  private String itemCode;
  private BigDecimal expectedQuantityBU;
  private String packageUnit;
  private BigDecimal shippedQuantityBU;
  private Short inventoryStatus;
  private String wmsLotNo;
  private String micAsnOrderId;
  private Long cargoId;

  public static Short getWmsInventoryStatusNormal() {
    return WMS_INVENTORY_STATUS_NORMAL;
  }

  public static void setWmsInventoryStatusNormal(Short wmsInventoryStatusNormal) {
    WMS_INVENTORY_STATUS_NORMAL = wmsInventoryStatusNormal;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public BigDecimal getExpectedQuantityBU() {
    return expectedQuantityBU;
  }

  public void setExpectedQuantityBU(BigDecimal expectedQuantityBU) {
    this.expectedQuantityBU = expectedQuantityBU;
  }

  public String getPackageUnit() {
    return packageUnit;
  }

  public void setPackageUnit(String packageUnit) {
    this.packageUnit = packageUnit;
  }

  public BigDecimal getShippedQuantityBU() {
    return shippedQuantityBU;
  }

  public void setShippedQuantityBU(BigDecimal shippedQuantityBU) {
    this.shippedQuantityBU = shippedQuantityBU;
  }

  public Short getInventoryStatus() {
    return inventoryStatus;
  }

  public void setInventoryStatus(Short inventoryStatus) {
    this.inventoryStatus = inventoryStatus;
  }

  public String getWmsLotNo() {
    return wmsLotNo;
  }

  public void setWmsLotNo(String wmsLotNo) {
    this.wmsLotNo = wmsLotNo;
  }

  public String getMicAsnOrderId() {
    return micAsnOrderId;
  }

  public void setMicAsnOrderId(String micAsnOrderId) {
    this.micAsnOrderId = micAsnOrderId;
  }

  public Long getCargoId() {
    return cargoId;
  }

  public void setCargoId(Long cargoId) {
    this.cargoId = cargoId;
  }
}
