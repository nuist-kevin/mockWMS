package com.focustech.mic.test.cb.mq.entity.wms;

import com.focustech.mic.test.cb.mq.entity.BusinessType;

import java.util.Date;

import static com.focustech.mic.test.cb.mq.entity.CommonConstant.OPERATOR_NAME;
import static com.focustech.mic.test.cb.mq.entity.CommonConstant.OPERATOR_NO;
import static com.focustech.mic.test.cb.mq.entity.CommonConstant.WAREHOUSE_CODE;

/**
 * @author caiwen
 */
public class BaseMsg {

  private BusinessType businessType;
  private String companyCode;
  private Long micComId;
  private String operatorName = OPERATOR_NAME;
  private Long operatorNo = OPERATOR_NO;
  private Date operatorTime = new Date();
  private String relatedBill1;
  private String warehouseCode = WAREHOUSE_CODE;

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

  public Long getMicComId() {
    return micComId;
  }

  public void setMicComId(Long micComId) {
    this.micComId = micComId;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public Long getOperatorNo() {
    return operatorNo;
  }

  public void setOperatorNo(Long operatorNo) {
    this.operatorNo = operatorNo;
  }

  public Date getOperatorTime() {
    return operatorTime;
  }

  public void setOperatorTime(Date operatorTime) {
    this.operatorTime = operatorTime;
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
