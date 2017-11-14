package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSON;
import com.focustech.mic.test.cb.entity.BusinessType;
import com.focustech.mic.test.cb.entity.mount.AsnOrder;
import com.focustech.mic.test.cb.entity.wms.asn.ArrivalRegisterMsg;
import com.focustech.mic.test.cb.entity.wms.asn.ReceiptPostMsg;
import com.focustech.mic.test.cb.entity.wms.asn.TransitReceiptPostDetail;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caiwen
 */
@Component
public class AsnMsgSender {

  @Autowired
  private JmsOperations jmsOperations;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private ArrivalRegisterMsg arrivalRegisterMsg;
  private PutAwayFinishMsg putAwayFinishMsg;
  private ReceiptPostMsg receiptPostMsg;

  public void sendAsnResponse(TextMessage message) throws JMSException, IOException {
    AsnOrder asnOrder = JSON.parseObject(message.getText(), AsnOrder.class);
    arrivalRegister(asnOrder);
    putAwayPost(asnOrder);
    receiptPost();
  }

  public void arrivalRegister(AsnOrder asnOrder) throws IOException, JMSException {
    arrivalRegisterMsg = new ArrivalRegisterMsg();
    arrivalRegisterMsg.setBusinessType(BusinessType.WMS2OSS_ARRIVALREGISTER);
    arrivalRegisterMsg.setAsnCode(asnOrder.getMicAsnOrderNo());
    arrivalRegisterMsg.setRelatedBill1(asnOrder.getRelatedBill1());
    arrivalRegisterMsg.setCompanyCode(asnOrder.getCompanyCode());
    arrivalRegisterMsg.setMicComId(asnOrder.getMicComId());
    jmsOperations.convertAndSend(arrivalRegisterMsg);
  }

  public void putAwayPost(AsnOrder asnOrder) {
    this.putAwayFinishMsg = new PutAwayFinishMsg();
    this.putAwayFinishMsg.setBusinessType(BusinessType.WMS2OSS_PUTAWAYPOST);
    this.putAwayFinishMsg.setAsnCode(asnOrder.getMicAsnOrderNo());
    this.putAwayFinishMsg.setCompanyCode(asnOrder.getCompanyCode());
    this.putAwayFinishMsg.setExpectedQuantityBU(BigDecimal.valueOf(asnOrder.getExpectedQuantityBU()));
    this.putAwayFinishMsg.setMicComId(asnOrder.getMicComId());
    this.putAwayFinishMsg.setReceivedQuantityBU(BigDecimal.valueOf(asnOrder.getExpectedQuantityBU()));
    this.putAwayFinishMsg.setRelatedBill1(asnOrder.getRelatedBill1());
    List<TransitReceiptPostDetail> transitReceiptPostDetails =
            new ArrayList<>(asnOrder.getReceiptListDetails().size());
    asnOrder.getReceiptListDetails().stream()
            .forEach(asnCargo -> {
              TransitReceiptPostDetail transitReceiptPostDetail = new TransitReceiptPostDetail();
              transitReceiptPostDetail.setCargoId(asnCargo.getCargoId());
              transitReceiptPostDetail.setExpectedQuantityBU(BigDecimal.valueOf(asnCargo.getExpectedQuantity()));
              transitReceiptPostDetail.setReceivedQuantityBU(BigDecimal.valueOf(asnCargo.getExpectedQuantity()));
              transitReceiptPostDetail.setInventoryStatus(1);
              transitReceiptPostDetail.setItemCode(asnCargo.getItemCode());
              transitReceiptPostDetail.setPackageUnit(asnCargo.getPackageUnit());
              transitReceiptPostDetail.setProductDate(asnCargo.getProductionDate());
              transitReceiptPostDetail.setStorageDate(LocalDate.now());
              SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(
                      " SELECT WMSR_LOT_NO, ASN_ORD_ID FROM WMS_LOT_INFO WHERE CARGO_ID = " +
                              asnCargo.getCargoId());
              if (sqlRowSet.next()) {
                transitReceiptPostDetail.setLotNo(sqlRowSet.getString(1));
              }
              transitReceiptPostDetails.add(transitReceiptPostDetail);
            });
    jmsOperations.convertAndSend(this.putAwayFinishMsg);
  }

  public void receiptPost() {
    BeanUtils.copyProperties(this.putAwayFinishMsg, this.receiptPostMsg, "businessType");
    this.receiptPostMsg.setBusinessType(BusinessType.WMS2OSS_RECEIPTPOST);
    jmsOperations.convertAndSend(this.receiptPostMsg);
  }
}