package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSON;

import com.focustech.mic.test.cb.entity.DateFormat;
import com.focustech.mic.test.cb.entity.BusinessType;
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
import static com.focustech.mic.test.cb.entity.wms.order.DeliveryPost.ACTIVATED;
import static com.focustech.mic.test.cb.entity.wms.order.DeliveryPost.SHIPMENT_REGISTERED;
import static com.focustech.mic.test.cb.entity.wms.order.DeliveryPost.WORK_DOC_FINISHED;

/**
 * @author caiwen
 */
@Component
public class DoMsgSender {

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
        "CAPO" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddhhmmss")));
    return deliveryStatusMsg;

  }

  public void sendSuiteForMessage(TextMessage message) throws IOException, JMSException {
    //发货单生效
    DeliveryStatusMsg activatedMsg = buildMsgObjectFromMqMessage(message, ACTIVATED);
    String activatedMsgInJson = JSON.toJSONString(activatedMsg);
    jmsOperations.send(session -> session.createTextMessage(activatedMsgInJson));

    //拣货单完成
    DeliveryStatusMsg workDocFinishedMsg = buildMsgObjectFromMqMessage(message, WORK_DOC_FINISHED);
    String workDocFinishedMsgInJson = JSON.toJSONString(workDocFinishedMsg);
    jmsOperations.send(session -> session.createTextMessage(workDocFinishedMsgInJson));

    //过账

    DeliveryOrder deliveryOrder = JSON.parseObject(message.getText(), DeliveryOrder.class);
    DeliveryPost deliveryPost = build(deliveryOrder);

    String deliveryPostMessage = JSON.toJSONString(deliveryPost);
    System.out.println(deliveryPostMessage);
    jmsOperations.send(session -> session.createTextMessage(deliveryPostMessage));

    //发货完成
    DeliveryStatusMsg shipmentRegisteredMsg = buildMsgObjectFromMqMessage(message, SHIPMENT_REGISTERED);
    String shipmentRegisteredMsgInJson = JSON.toJSONString(shipmentRegisteredMsg);
    jmsOperations.send(session -> session.createTextMessage(shipmentRegisteredMsgInJson));
  }

  public DeliveryPost build(DeliveryOrder deliveryOrder) {
    DeliveryPost deliveryPost = new DeliveryPost();
    deliveryPost.setBolCode(
        "CAPT" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateFormat.ISO_DATE_FORMAT_YYMMDDHHMMSS)));
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

      postingDetails.add(deliverCargo);
    }
    deliveryPost.setSendPostingDetails(postingDetails);
    deliveryPost.setWarehouseCode(deliveryOrder.getWarehouse());
    deliveryPost.setPickCode(deliveryPost.getBolCode());
    return deliveryPost;
  }
}
