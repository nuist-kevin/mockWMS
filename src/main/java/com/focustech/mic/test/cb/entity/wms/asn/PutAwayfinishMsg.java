package com.focustech.mic.test.cb.entity.wms.asn;

import com.focustech.mic.test.cb.entity.BusinessType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author caiwen
 */
public class PutAwayfinishMsg {

  private String asnCode;
  private BusinessType businessType = BusinessType.WMS2OSS_PUTAWAYPOST;
  private String companyCode;
  private BigDecimal expectedQuantityBU;
  private Integer micComId;
  private String operatorName = "liangbiao";
  private Integer operatorNo = 39;
  private Date operatorTime;
  private BigDecimal receivedQuantityBU;
  private String relatedBill1;
  private List<TransitReceiptPostDetail> transitReceiptPostDetails;
  private String warehouseCode = "FTUSCA001";

  public String getAsnCode() {
    return asnCode;
  }

  public void setAsnCode(String asnCode) {
    this.asnCode = asnCode;
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

  public BigDecimal getExpectedQuantityBU() {
    return expectedQuantityBU;
  }

  public void setExpectedQuantityBU(BigDecimal expectedQuantityBU) {
    this.expectedQuantityBU = expectedQuantityBU;
  }

  public Integer getMicComId() {
    return micComId;
  }

  public void setMicComId(Integer micComId) {
    this.micComId = micComId;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public Integer getOperatorNo() {
    return operatorNo;
  }

  public void setOperatorNo(Integer operatorNo) {
    this.operatorNo = operatorNo;
  }

  public Date getOperatorTime() {
    return operatorTime;
  }

  public void setOperatorTime(Date operatorTime) {
    this.operatorTime = operatorTime;
  }

  public BigDecimal getReceivedQuantityBU() {
    return receivedQuantityBU;
  }

  public void setReceivedQuantityBU(BigDecimal receivedQuantityBU) {
    this.receivedQuantityBU = receivedQuantityBU;
  }

  public String getRelatedBill1() {
    return relatedBill1;
  }

  public void setRelatedBill1(String relatedBill1) {
    this.relatedBill1 = relatedBill1;
  }

  public List<TransitReceiptPostDetail> getTransitReceiptPostDetails() {
    return transitReceiptPostDetails;
  }

  public void setTransitReceiptPostDetails(
      List<TransitReceiptPostDetail> transitReceiptPostDetails) {
    this.transitReceiptPostDetails = transitReceiptPostDetails;
  }

  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }
}
