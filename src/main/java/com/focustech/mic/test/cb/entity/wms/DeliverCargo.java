package com.focustech.mic.test.cb.entity.wms;

import com.focustech.mic.test.cb.entity.DoCargo;

import java.math.BigDecimal;

/**
 * Created by caiwen on 2017/4/24.
 */
public class DeliverCargo extends DoCargo {
    private BigDecimal expectedQuantityBU;
    private String micAsnOrderId;
    private BigDecimal shippedQuantityBU;
    private String wmsLotNo;

    public BigDecimal getExpectedQuantityBU() {
        return expectedQuantityBU;
    }

    public void setExpectedQuantityBU(BigDecimal expectedQuantityBU) {
        this.expectedQuantityBU = expectedQuantityBU;
    }

    public String getMicAsnOrderId() {
        return micAsnOrderId;
    }

    public void setMicAsnOrderId(String micAsnOrderId) {
        this.micAsnOrderId = micAsnOrderId;
    }

    public BigDecimal getShippedQuantityBU() {
        return shippedQuantityBU;
    }

    public void setShippedQuantityBU(BigDecimal shippedQuantityBU) {
        this.shippedQuantityBU = shippedQuantityBU;
    }

    public String getWmsLotNo() {
        return wmsLotNo;
    }

    public void setWmsLotNo(String wmsLotNo) {
        this.wmsLotNo = wmsLotNo;
    }
}
