package com.focustech.mic.test.cb.sender;

import com.alibaba.fastjson.JSON;

import com.focustech.mic.test.cb.entity.DeliveryOrder;
import com.focustech.mic.test.cb.entity.DoCargo;
import com.focustech.mic.test.cb.entity.wms.DeliverCargo;
import com.focustech.mic.test.cb.entity.wms.DeliveryPost;
import com.focustech.mic.test.cb.entity.wms.DeliveryStatusMessage;

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
 * Created by caiwen on 2017/4/21.
 */

@Component
public class DoMsgSender {

    @Autowired
    JmsOperations jmsOperations;

//    @Autowired
    JdbcTemplate jdbcTemplate;

    private DeliveryStatusMessage buildMsgObjectFromMqMessage(TextMessage message, String status) throws JMSException, IOException {
        DeliveryStatusMessage deliveryStatusMessage = new DeliveryStatusMessage();
        DeliveryOrder deliveryOrder = parseObject(message.getText(), DeliveryOrder.class);
        deliveryStatusMessage.setStatus(status);
        deliveryStatusMessage.setCarrierService(deliveryOrder.getCarrierService());
        deliveryStatusMessage.setCompanyCode(deliveryOrder.getCompanyCode());
        deliveryStatusMessage.setMicComId(deliveryOrder.getMicComId());
        deliveryStatusMessage.setRelatedBill1(deliveryOrder.getRelatedBill1());
        deliveryStatusMessage.setWmsDoCode("CAPO" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddhhmmss")));
        return deliveryStatusMessage;

    }

    public String messageToJson(DeliveryStatusMessage deliveryStatusMessage) {
        return JSON.toJSONString(deliveryStatusMessage);

    }

    public void sendSuiteForMessage(TextMessage message) throws IOException, JMSException {
        //发货单生效
        DeliveryStatusMessage deliveryStatusMessage = buildMsgObjectFromMqMessage(message, "1");
        String activateDoMsg = messageToJson(deliveryStatusMessage);
        jmsOperations.send(session -> session.createTextMessage(activateDoMsg));

        //拣货单完成
        deliveryStatusMessage.setStatus("2");
        String pickupfinishedMsg = messageToJson(deliveryStatusMessage);
        jmsOperations.send(session -> session.createTextMessage(pickupfinishedMsg));

        //过账


        DeliveryOrder deliveryOrder = JSON.parseObject(message.getText(), DeliveryOrder.class);
        DeliveryPost deliveryPost = build(deliveryOrder);

        String deliveryPostMessage = JSON.toJSONString(deliveryPost);
        System.out.println(deliveryPostMessage);
        jmsOperations.send(session -> session.createTextMessage(deliveryPostMessage));

        //发货完成
        deliveryStatusMessage.setStatus("3");
        String finishedMsg = messageToJson(deliveryStatusMessage);
        jmsOperations.send(session -> session.createTextMessage(finishedMsg));
    }

    public DeliveryPost build(DeliveryOrder deliveryOrder) {
        DeliveryPost deliveryPost = new DeliveryPost();
        deliveryPost.setBolCode("CAPT" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddhhmmss")));
        BeanUtils.copyProperties(deliveryOrder, deliveryPost, "businessType");

        List<DeliverCargo> postingDetails = new ArrayList<>(deliveryOrder.getTransitDeliveryListDetails().size());
        for (int i = 0; i < deliveryOrder.getTransitDeliveryListDetails().size(); i++) {
            DoCargo doCargo = deliveryOrder.getTransitDeliveryListDetails().get(i);
            DeliverCargo deliverCargo = new DeliverCargo();
            BeanUtils.copyProperties(doCargo, deliverCargo, "expectedQuantity", "packageUnit");
            deliverCargo.setExpectedQuantityBU(BigDecimal.valueOf(Long.parseLong(doCargo.getExpectedQuantity())));
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
