package com.focustech.mic.test.cb.entity.wms;

import java.util.Date;

/**
 * @author caiwen
 */
public class MsgConfirm {

  private Date addTime;
  private String businessType;
  private String fromSite;
  private String recId;
  private String status;

  public Date getAddTime() {
    return addTime;
  }

  public void setAddTime(Date addTime) {
    this.addTime = addTime;
  }

  public String getBusinessType() {
    return businessType;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  public String getFromSite() {
    return fromSite;
  }

  public void setFromSite(String fromSite) {
    this.fromSite = fromSite;
  }

  public String getRecId() {
    return recId;
  }

  public void setRecId(String recId) {
    this.recId = recId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
