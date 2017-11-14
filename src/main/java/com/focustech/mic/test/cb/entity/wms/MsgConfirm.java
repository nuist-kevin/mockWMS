package com.focustech.mic.test.cb.entity.wms;

import com.focustech.mic.test.cb.entity.BusinessType;
import java.util.Date;

import static com.focustech.mic.test.cb.entity.BusinessType.WMS2OSS_MSG_CONFIRM;

/**
 * @author caiwen
 */
public class MsgConfirm {

  private static final String WMS_SITE = "USA";
  private static final String CONFIRM_STATUS_SUCCESS = "1";

  private Date addTime = new Date();
  private BusinessType businessType = WMS2OSS_MSG_CONFIRM;
  private String fromSite = WMS_SITE;
  private String recId;
  private String status = CONFIRM_STATUS_SUCCESS;

  public Date getAddTime() {
    return addTime;
  }

  public void setAddTime(Date addTime) {
    this.addTime = addTime;
  }

  public BusinessType getBusinessType() {
    return businessType;
  }

  public void setBusinessType(BusinessType businessType) {
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
