package com.focustech.mic.test.cb.entity.wms.order;

import com.focustech.mic.test.cb.entity.BusinessType;
import com.focustech.mic.test.cb.entity.wms.BaseMsg;

/**
 * @author caiwen
 */
public class DeliveryStatusMsg extends BaseMsg {

  /**
   * 发货单已生效
   */
  public final static String ACTIVATED = "1";

  /**
   * 作业单已完成
   */
  public final static String WORK_DOC_FINISHED = "2";

  /**
   * 已发运登记
   */
  public final static String SHIPMENT_REGISTERED = "3";

  private String carrierService;
  private String status;
  private String wmsDoCode;

  public DeliveryStatusMsg() {
    this.setBusinessType(BusinessType.WMS2OSS_DELIVERY_SYN);
  }

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