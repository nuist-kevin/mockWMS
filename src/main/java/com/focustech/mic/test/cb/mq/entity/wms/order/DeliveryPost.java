package com.focustech.mic.test.cb.mq.entity.wms.order;

import com.focustech.mic.test.cb.mq.entity.BusinessType;
import com.focustech.mic.test.cb.mq.entity.wms.BaseMsg;

import java.util.List;

/**
 * @author caiwen
 */
public class DeliveryPost extends BaseMsg {

  private String bolCode;
  private String carrierService;
  private String pickCode;
  private List<PostingDetail> sendPostingDetails;

  public DeliveryPost() {
    this.setBusinessType(BusinessType.WMS2OSS_DELIVERYPOST);
  }

  public String getBolCode() {
    return bolCode;
  }

  public void setBolCode(String bolCode) {
    this.bolCode = bolCode;
  }

  public String getCarrierService() {
    return carrierService;
  }

  public void setCarrierService(String carrierService) {
    this.carrierService = carrierService;
  }

  public String getPickCode() {
    return pickCode;
  }

  public void setPickCode(String pickCode) {
    this.pickCode = pickCode;
  }

  public List<PostingDetail> getSendPostingDetails() {
    return sendPostingDetails;
  }

  public void setSendPostingDetails(List<PostingDetail> sendPostingDetails) {
    this.sendPostingDetails = sendPostingDetails;
  }
}
