package com.focustech.mic.test.cb.entity.wms.order;

import com.focustech.mic.test.cb.entity.wms.BaseMsg;

/**
 * @author caiwen
 */
public class DeliveryStatusMsg extends BaseMsg {

  private String carrierService;
  private String status;
  private String wmsDoCode;

  public String getCarrierService() {
    return carrierService;
  }

  public void setCarrierService(String carrierService) {
    this.carrierService = carrierService;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getWmsDoCode() {
    return wmsDoCode;
  }

  public void setWmsDoCode(String wmsDoCode) {
    this.wmsDoCode = wmsDoCode;
  }
}