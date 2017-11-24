package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSON;
import com.focustech.mic.test.cb.entity.BusinessType;
import com.focustech.mic.test.cb.entity.mount.AsnOrder;
import com.focustech.mic.test.cb.entity.wms.asn.ArrivalRegisterMsg;
import com.focustech.mic.test.cb.entity.wms.asn.PutAwayFinishMsg;
import com.focustech.mic.test.cb.entity.wms.asn.ReceiptPostMsg;
import com.focustech.mic.test.cb.entity.wms.asn.TransitReceiptPostDetail;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

  private ArrivalRegisterMsg arrivalRegisterMsg;
  private PutAwayFinishMsg putAwayFinishMsg;
  private ReceiptPostMsg receiptPostMsg = new ReceiptPostMsg();

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
    logger.debug(arrivalRegisterMsg.toString());
    jmsOperations.convertAndSend(arrivalRegisterMsg);
  }

  public void putAwayPost(AsnOrder asnOrder) {
    this.putAwayFinishMsg = new PutAwayFinishMsg();
    this.putAwayFinishMsg.setBusinessType(BusinessType.WMS2OSS_PUTAWAYPOST);
    this.putAwayFinishMsg.setAsnCode(asnOrder.getMicAsnOrderNo());
    this.putAwayFinishMsg.setCompanyCode(asnOrder.getCompanyCode());
    this.putAwayFinishMsg
        .setExpectedQuantityBU(BigDecimal.valueOf(asnOrder.getExpectedQuantityBU()));
    this.putAwayFinishMsg.setMicComId(asnOrder.getMicComId());
    this.putAwayFinishMsg
        .setReceivedQuantityBU(BigDecimal.valueOf(asnOrder.getExpectedQuantityBU()));
    this.putAwayFinishMsg.setRelatedBill1(asnOrder.getRelatedBill1());
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
          }
//              transitReceiptPostDetail.setLotNo(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
          transitReceiptPostDetails.add(transitReceiptPostDetail);
        });
    this.putAwayFinishMsg.setTransitReceiptPostDetails(transitReceiptPostDetails);
    logger.debug(putAwayFinishMsg.toString());
    jmsOperations.convertAndSend(this.putAwayFinishMsg);
  }

  public void receiptPost() {
    BeanUtils.copyProperties(this.putAwayFinishMsg, this.receiptPostMsg, "businessType");
    this.receiptPostMsg.setBusinessType(BusinessType.WMS2OSS_RECEIPTPOST);
    logger.debug(receiptPostMsg.toString());
    jmsOperations.convertAndSend(this.receiptPostMsg);
  }
}
