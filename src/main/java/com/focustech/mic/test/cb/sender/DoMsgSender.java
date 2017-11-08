package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSON;

import com.focustech.mic.test.cb.entity.mount.DeliveryOrder;
import com.focustech.mic.test.cb.entity.mount.DoCargo;
import com.focustech.mic.test.cb.entity.wms.order.DeliverCargo;
import com.focustech.mic.test.cb.entity.wms.order.DeliveryPost;
import com.focustech.mic.test.cb.entity.wms.order.DeliveryStatusMsg;

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

  @Autowired
  JmsOperations jmsOperations;

  //    @Autowired
  JdbcTemplate jdbcTemplate;

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
        "CAPO" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddhhmmss")));
    return deliveryStatusMsg;

  }

  public String messageToJson(DeliveryStatusMsg deliveryStatusMsg) {
    return JSON.toJSONString(deliveryStatusMsg);

  }

  public void sendSuiteForMessage(TextMessage message) throws IOException, JMSException {
    //发货单生效
    DeliveryStatusMsg deliveryStatusMsg = buildMsgObjectFromMqMessage(message, "1");
    String activateDoMsg = messageToJson(deliveryStatusMsg);
    jmsOperations.send(session -> session.createTextMessage(activateDoMsg));

    //拣货单完成
    deliveryStatusMsg.setStatus("2");
    String pickupfinishedMsg = messageToJson(deliveryStatusMsg);
    jmsOperations.send(session -> session.createTextMessage(pickupfinishedMsg));

    //过账

    DeliveryOrder deliveryOrder = JSON.parseObject(message.getText(), DeliveryOrder.class);
    DeliveryPost deliveryPost = build(deliveryOrder);

    String deliveryPostMessage = JSON.toJSONString(deliveryPost);
    System.out.println(deliveryPostMessage);
    jmsOperations.send(session -> session.createTextMessage(deliveryPostMessage));

    //发货完成
    deliveryStatusMsg.setStatus("3");
    String finishedMsg = messageToJson(deliveryStatusMsg);
    jmsOperations.send(session -> session.createTextMessage(finishedMsg));
  }

  public DeliveryPost build(DeliveryOrder deliveryOrder) {
    DeliveryPost deliveryPost = new DeliveryPost();
    deliveryPost.setBolCode(
        "CAPT" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddhhmmss")));
    BeanUtils.copyProperties(deliveryOrder, deliveryPost, "businessType");

    List<DeliverCargo> postingDetails = new ArrayList<>(
        deliveryOrder.getTransitDeliveryListDetails().size());
    for (int i = 0; i < deliveryOrder.getTransitDeliveryListDetails().size(); i++) {
      DoCargo doCargo = deliveryOrder.getTransitDeliveryListDetails().get(i);
      DeliverCargo deliverCargo = new DeliverCargo();
      BeanUtils.copyProperties(doCargo, deliverCargo, "expectedQuantity", "packageUnit");
      deliverCargo
          .setExpectedQuantityBU(BigDecimal.valueOf(Long.parseLong(doCargo.getExpectedQuantity())));
      deliverCargo.setShippedQuantityBU(deliverCargo.getExpectedQuantityBU());

      // TODO 数据库查询 并 设置该货品的对应 入库单号 和 批次号
      SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(
          " SELECT WMSR_LOT_NO, ASN_ORD_ID FROM WMS_LOT_INFO WHERE CARGO_ID = " +
              deliverCargo.getCargoId());
      if (sqlRowSet.next()) {
        deliverCargo.setWmsLotNo(sqlRowSet.getString(1));
        deliverCargo.setMicAsnOrderId(sqlRowSet.getString(2));
      }

      System.out.println(sqlRowSet.toString());
      postingDetails.add(deliverCargo);
    }
    deliveryPost.setSendPostingDetails(postingDetails);
    deliveryPost.setWarehouseCode(deliveryOrder.getWarehouse());
    deliveryPost.setPickCode(deliveryPost.getBolCode());
    return deliveryPost;
  }
}
