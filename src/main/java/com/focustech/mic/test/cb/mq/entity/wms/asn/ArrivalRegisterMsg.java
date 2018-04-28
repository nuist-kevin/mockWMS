package com.focustech.mic.test.cb.mq.entity.wms.asn;

import com.focustech.mic.test.cb.mq.entity.wms.BaseMsg;

import java.util.Date;

/**
 * @author caiwen
 */
public class ArrivalRegisterMsg extends BaseMsg {

  public static final Integer RECEIVED_FLAG_FINISHED = 1;
  private String asnCode;
  private Date receivedDate = new Date();
  private Integer receivedFlag = RECEIVED_FLAG_FINISHED;

  public String getAsnCode() {
    return asnCode;
  }

  public void setAsnCode(String asnCode) {
    this.asnCode = asnCode;
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
}
