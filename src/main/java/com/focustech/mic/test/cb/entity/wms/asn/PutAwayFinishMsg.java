package com.focustech.mic.test.cb.entity.wms.asn;

import com.focustech.mic.test.cb.entity.wms.BaseMsg;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author caiwen
 */
public class PutAwayFinishMsg extends BaseMsg {

  private String asnCode;
  private BigDecimal expectedQuantityBU;
  private BigDecimal receivedQuantityBU;
  private List<TransitReceiptPostDetail> transitReceiptPostDetails;

  public String getAsnCode() {
    return asnCode;
  }

  public void setAsnCode(String asnCode) {
    this.asnCode = asnCode;
  }

  public BigDecimal getExpectedQuantityBU() {
    return expectedQuantityBU;
  }

  public void setExpectedQuantityBU(BigDecimal expectedQuantityBU) {
    this.expectedQuantityBU = expectedQuantityBU;
  }

  public BigDecimal getReceivedQuantityBU() {
    return receivedQuantityBU;
  }

  public void setReceivedQuantityBU(BigDecimal receivedQuantityBU) {
    this.receivedQuantityBU = receivedQuantityBU;
  }

  public List<TransitReceiptPostDetail> getTransitReceiptPostDetails() {
    return transitReceiptPostDetails;
  }

  public void setTransitReceiptPostDetails(
      List<TransitReceiptPostDetail> transitReceiptPostDetails) {
    this.transitReceiptPostDetails = transitReceiptPostDetails;
  }
}
