package com.focustech.mic.test.cb.mq.sender;

import com.alibaba.fastjson.JSON;
import com.focustech.mic.test.cb.mq.entity.BusinessType;
import com.focustech.mic.test.cb.mq.entity.mount.AsnOrder;
import com.focustech.mic.test.cb.mq.entity.wms.asn.ArrivalRegisterMsg;
import com.focustech.mic.test.cb.mq.entity.wms.asn.PutAwayFinishMsg;
import com.focustech.mic.test.cb.mq.entity.wms.asn.ReceiptPostMsg;
import com.focustech.mic.test.cb.mq.entity.wms.asn.TransitReceiptPostDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private final Logger logger = LoggerFactory.getLogger(AsnMsgSender.class);

  @Autowired
  private JmsOperations jmsOperations;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void sendAsnResponse(TextMessage message) throws JMSException {

    AsnOrder asnOrder = JSON.parseObject(message.getText(), AsnOrder.class);
    arrivalRegister(asnOrder);
    putAwayPost(asnOrder);
  }

  public void arrivalRegister(AsnOrder asnOrder) {
    ArrivalRegisterMsg arrivalRegisterMsg = new ArrivalRegisterMsg();
    arrivalRegisterMsg.setBusinessType(BusinessType.WMS2OSS_ARRIVALREGISTER);
    arrivalRegisterMsg.setAsnCode(asnOrder.getMicAsnOrderNo());
    arrivalRegisterMsg.setRelatedBill1(asnOrder.getRelatedBill1());
    arrivalRegisterMsg.setCompanyCode(asnOrder.getCompanyCode());
    arrivalRegisterMsg.setMicComId(asnOrder.getMicComId());
    jmsOperations.convertAndSend(arrivalRegisterMsg);
  }

  public void putAwayPost(AsnOrder asnOrder) {
    PutAwayFinishMsg putAwayFinishMsg = new PutAwayFinishMsg();
    putAwayFinishMsg.setBusinessType(BusinessType.WMS2OSS_PUTAWAYPOST);
    putAwayFinishMsg.setAsnCode(asnOrder.getMicAsnOrderNo());
    putAwayFinishMsg.setCompanyCode(asnOrder.getCompanyCode());
    putAwayFinishMsg.setExpectedQuantityBU(BigDecimal.valueOf(asnOrder.getExpectedQuantityBU()));
    putAwayFinishMsg.setMicComId(asnOrder.getMicComId());
    putAwayFinishMsg.setReceivedQuantityBU(BigDecimal.valueOf(asnOrder.getExpectedQuantityBU()));
    putAwayFinishMsg.setRelatedBill1(asnOrder.getRelatedBill1());
    List<TransitReceiptPostDetail> transitReceiptPostDetails =
        new ArrayList<>(asnOrder.getReceiptListDetails().size());
    asnOrder.getReceiptListDetails().stream()
        .forEach(asnCargo -> {
          TransitReceiptPostDetail transitReceiptPostDetail = new TransitReceiptPostDetail();
          transitReceiptPostDetail.setCargoId(asnCargo.getCargoId());
          transitReceiptPostDetail
              .setExpectedQuantityBU(BigDecimal.valueOf(asnCargo.getExpectedQuantity()));
          transitReceiptPostDetail
              .setReceivedQuantityBU(BigDecimal.valueOf(asnCargo.getExpectedQuantity()));
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
          } else {
            transitReceiptPostDetail.setLotNo(asnCargo.getItemCode());
          }
          transitReceiptPostDetails.add(transitReceiptPostDetail);
        });
    putAwayFinishMsg.setTransitReceiptPostDetails(transitReceiptPostDetails);
    jmsOperations.convertAndSend(putAwayFinishMsg);
    receiptPost(putAwayFinishMsg);
  }

  public void receiptPost(PutAwayFinishMsg putAwayFinishMsg) {
    ReceiptPostMsg receiptPostMsg= new ReceiptPostMsg();
    BeanUtils.copyProperties(putAwayFinishMsg, receiptPostMsg, "businessType");
    receiptPostMsg.setBusinessType(BusinessType.WMS2OSS_RECEIPTPOST);
    jmsOperations.convertAndSend(receiptPostMsg);
  }
}
