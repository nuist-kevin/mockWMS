package com.focustech.mic.test.cb.mq.entity.mount;

import com.focustech.mic.test.cb.mq.entity.BusinessType;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author caiwen
 */
public class AsnOrder {

  private Long micComId;
  private String relatedBill1;
  private String remark;
  private String type;
  private String warehouseCode;
  private Integer expectedQuantityBU;
  private String micAsnOrderNo;
  private String billTypeCode;
  private LocalDate estimateDate;
  private Date orderDate;
  private BusinessType businessType;
  private String companyCode;
  private List<ReceiptListDetail> receiptListDetails;
  private Integer resultType;
  private Integer micVersion;

  public Long getMicComId() {
    return micComId;
  }

  public void setMicComId(Long micComId) {
    this.micComId = micComId;
  }

  public String getRelatedBill1() {
    return relatedBill1;
  }

  public void setRelatedBill1(String relatedBill1) {
    this.relatedBill1 = relatedBill1;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  public Integer getExpectedQuantityBU() {
    return expectedQuantityBU;
  }

  public void setExpectedQuantityBU(Integer expectedQuantityBU) {
    this.expectedQuantityBU = expectedQuantityBU;
  }

  public String getMicAsnOrderNo() {
    return micAsnOrderNo;
  }

  public void setMicAsnOrderNo(String micAsnOrderNo) {
    this.micAsnOrderNo = micAsnOrderNo;
  }

  public String getBillTypeCode() {
    return billTypeCode;
  }

  public void setBillTypeCode(String billTypeCode) {
    this.billTypeCode = billTypeCode;
  }

  public LocalDate getEstimateDate() {
    return estimateDate;
  }

  public void setEstimateDate(LocalDate estimateDate) {
    this.estimateDate = estimateDate;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public BusinessType getBusinessType() {
    return businessType;
  }

  public void setBusinessType(BusinessType businessType) {
    this.businessType = businessType;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }

  public List<ReceiptListDetail> getReceiptListDetails() {
    return receiptListDetails;
  }

  public void setReceiptListDetails(
      List<ReceiptListDetail> receiptListDetails) {
    this.receiptListDetails = receiptListDetails;
  }

  public Integer getResultType() {
    return resultType;
  }

  public void setResultType(Integer resultType) {
    this.resultType = resultType;
  }

  public Integer getMicVersion() {
    return micVersion;
  }

  public void setMicVersion(Integer micVersion) {
    this.micVersion = micVersion;
  }
}
