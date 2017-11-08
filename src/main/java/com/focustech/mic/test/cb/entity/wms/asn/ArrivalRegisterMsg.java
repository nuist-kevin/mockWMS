package com.focustech.mic.test.cb.entity.wms.asn;

import com.focustech.mic.test.cb.entity.BusinessType;
import java.util.Date;

/**
 * @author caiwen
 */
public class ArrivalRegisterMsg {

  private String asnCode;
  private BusinessType businessType = BusinessType.WMS2OSS_ARRIVALREGISTER;
  private String companyCode;
  private Integer micComId;
  private String operatorName = "liangbiao";
  private Integer operatorNo = 39;
  private Date operatorTime;
  private Date receivedDate;
  private Integer receivedFlag;
  private String relatedBill1;
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

  public Date getReceivedDate() {
    return receivedDate;
  }

  public void setReceivedDate(Date receivedDate) {
    this.receivedDate = receivedDate;
  }

  public Integer getReceivedFlag() {
    return receivedFlag;
  }

  public void setReceivedFlag(Integer receivedFlag) {
    this.receivedFlag = receivedFlag;
  }

  public String getRelatedBill1() {
    return relatedBill1;
  }

  public void setRelatedBill1(String relatedBill1) {
    this.relatedBill1 = relatedBill1;
  }

  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }
}
