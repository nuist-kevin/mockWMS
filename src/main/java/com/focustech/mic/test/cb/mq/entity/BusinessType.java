package com.focustech.mic.test.cb.mq.entity;

/**
 * @author caiwen
 */
public enum BusinessType {

  /**
   * 同步货品
   */
  OSS2WMS_ITEM,

  /*
  *  出库单审核通过
  */
  OSS2WMS_DELLIST,

  /*
   *  入库单审核通过
   */
  OSS2WMS_ASN,

  /*
  *  wms确认消息
  */
  WMS2OSS_MSG_CONFIRM,

  /*
  *  wms确认消息
  */
  WMS2OSS_DELIVERY_SYN,
  WMS2OSS_DELIVERYPOST,


  /*
  *  出库单到货登记
  */
  WMS2OSS_ARRIVALREGISTER,

  /*
  *  出库单审核通过
  */
  WMS2OSS_PUTAWAYPOST,

  /*
  *  出库单审核通过
  */
  WMS2OSS_RECEIPTPOST
}
