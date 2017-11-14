package com.focustech.mic.test.cb.entity.wms.order;

import com.focustech.mic.test.cb.entity.wms.BaseMsg;

import java.util.List;

/**
 * @author caiwen
 */
public class DeliveryPost extends BaseMsg{

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

  private String bolCode;
  private String carrierService;
  private String pickCode;
  private List<DeliverCargo> sendPostingDetails;

  public String getBolCode() {
    return bolCode;
  }

  public void setBolCode(String bolCode) {
    this.bolCode = bolCode;
  }

  public static String getACTIVATED() {
    return ACTIVATED;
  }

  public static String getWorkDocFinished() {
    return WORK_DOC_FINISHED;
  }

  public static String getShipmentRegistered() {
    return SHIPMENT_REGISTERED;
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

  public List<DeliverCargo> getSendPostingDetails() {
    return sendPostingDetails;
  }

  public void setSendPostingDetails(List<DeliverCargo> sendPostingDetails) {
    this.sendPostingDetails = sendPostingDetails;
  }
}
