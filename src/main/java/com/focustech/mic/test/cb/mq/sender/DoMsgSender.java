package com.focustech.mic.test.cb.mq.sender;

import com.alibaba.fastjson.JSON;

import com.focustech.mic.test.cb.entity.DateFormat;
import com.focustech.mic.test.cb.mq.entity.mount.DeliveryOrder;
import com.focustech.mic.test.cb.mq.entity.mount.TransitDeliveryListDetail;
import com.focustech.mic.test.cb.mq.entity.wms.order.PostingDetail;
import com.focustech.mic.test.cb.mq.entity.wms.order.DeliveryPost;
import com.focustech.mic.test.cb.mq.entity.wms.order.DeliveryStatusMsg;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * @author caiwen
 */
@Component
public class DoMsgSender {

  private final Logger logger = LoggerFactory.getLogger(DoMsgSender.class);

  @Autowired
  private JmsOperations jmsOperations;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private DeliveryStatusMsg buildMsgObjectFromMqMessage(TextMessage message, String status)
      throws JMSException, IOException {
    DeliveryStatusMsg deliveryStatusMsg = new DeliveryStatusMsg();
    DeliveryOrder deliveryOrder = parseObject(message.getText(), DeliveryOrder.class);
    deliveryStatusMsg.setStatus(status);
    deliveryStatusMsg.setCarrierService(deliveryOrder.getCarrierService());
    deliveryStatusMsg.setCompanyCode(deliveryOrder.getCompanyCode());
    deliveryStatusMsg.setMicComId(deliveryOrder.getMicComId());
    deliveryStatusMsg.setRelatedBill1(deliveryOrder.getRelatedBill1());
    deliveryStatusMsg.setWmsDoCode(
        "CAPO" + LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern(DateFormat.ISO_DATE_FORMAT_YYMMDDHHMMSS)));
    return deliveryStatusMsg;

  }

  public void sendSuiteForMessage(TextMessage message) throws IOException, JMSException {
    //发货单生效
    DeliveryStatusMsg activatedMsg = buildMsgObjectFromMqMessage(message,
        DeliveryStatusMsg.ACTIVATED);
    String activatedMsgInJson = JSON.toJSONString(activatedMsg);
    logger.debug(activatedMsgInJson);
    jmsOperations.send(session -> session.createTextMessage(activatedMsgInJson));

    //拣货单完成
    DeliveryStatusMsg workDocFinishedMsg = buildMsgObjectFromMqMessage(message,
        DeliveryStatusMsg.WORK_DOC_FINISHED);
    String workDocFinishedMsgInJson = JSON.toJSONString(workDocFinishedMsg);
    logger.debug(workDocFinishedMsgInJson);
    jmsOperations.send(session -> session.createTextMessage(workDocFinishedMsgInJson));

    //过账

    DeliveryOrder deliveryOrder = JSON.parseObject(message.getText(), DeliveryOrder.class);
    DeliveryPost deliveryPost = build(deliveryOrder);

    String deliveryPostMessage = JSON.toJSONString(deliveryPost);
    logger.debug(deliveryPostMessage);
    jmsOperations.send(session -> session.createTextMessage(deliveryPostMessage));

    //发货完成
    DeliveryStatusMsg shipmentRegisteredMsg = buildMsgObjectFromMqMessage(message,
        DeliveryStatusMsg.SHIPMENT_REGISTERED);
    String shipmentRegisteredMsgInJson = JSON.toJSONString(shipmentRegisteredMsg);
    logger.debug(shipmentRegisteredMsgInJson);
    jmsOperations.send(session -> session.createTextMessage(shipmentRegisteredMsgInJson));
  }

  public DeliveryPost build(DeliveryOrder deliveryOrder) {
    DeliveryPost deliveryPost = new DeliveryPost();
    deliveryPost.setBolCode(
        "CAPT" + LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern(DateFormat.ISO_DATE_FORMAT_YYMMDDHHMMSS)));
    deliveryPost.setPickCode(deliveryPost.getBolCode());
    BeanUtils.copyProperties(deliveryOrder, deliveryPost, "businessType");

    List<PostingDetail> postingDetails = new ArrayList<>(
        deliveryOrder.getTransitDeliveryListDetails().size());
    for (int i = 0; i < deliveryOrder.getTransitDeliveryListDetails().size(); i++) {
      TransitDeliveryListDetail transitDeliveryListDetail = deliveryOrder
          .getTransitDeliveryListDetails().get(i);
      PostingDetail postingDetail = new PostingDetail();
      BeanUtils.copyProperties(transitDeliveryListDetail, postingDetail);
      postingDetail
          .setExpectedQuantityBU(
              BigDecimal.valueOf(Long.parseLong(transitDeliveryListDetail.getExpectedQuantity())));
      postingDetail.setShippedQuantityBU(postingDetail.getExpectedQuantityBU());
      postingDetail.setInventoryStatus(PostingDetail.WMS_INVENTORY_STATUS_NORMAL);
      SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(
          " SELECT WMSR_LOT_NO, ASN_ORD_ID FROM WMS_LOT_INFO WHERE CARGO_ID = " +
              postingDetail.getCargoId());
      if (sqlRowSet.next()) {
        postingDetail.setWmsLotNo(sqlRowSet.getString(1));
        postingDetail.setMicAsnOrderId(sqlRowSet.getString(2));
      }
      postingDetails.add(postingDetail);
    }
    deliveryPost.setSendPostingDetails(postingDetails);

    return deliveryPost;
  }
}
