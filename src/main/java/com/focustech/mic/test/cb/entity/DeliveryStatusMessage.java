package com.focustech.mic.test.cb.entity;

import java.util.Date;

/**
 * Created by caiwen on 2017/4/21.
 */
public class DeliveryStatusMessage {

    private final String businessType = "WMS2OSS_DELIVERY_SYN";
    private String carrierService;
    private String companyCode;
    private Long micComId;
    private String operatorName = "liangbiao";
    private Integer operatorNo = 39;
    private final Date operatorTime = new Date();
    private String relatedBill1;
    private String status;
    private String warehouseCode = "FTUSCA001";
    private String wmsDoCode;

    public String getBusinessType() {
        return businessType;
    }

    public String getCarrierService() {
        return carrierService;
    }

    public void setCarrierService(String carrierService) {
        this.carrierService = carrierService;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Long getMicComId() {
        return micComId;
    }

    public void setMicComId(Long micComId) {
        this.micComId = micComId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(Integer operatorNo) {
        this.operatorNo = operatorNo;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public String getRelatedBill1() {
        return relatedBill1;
    }

    public void setRelatedBill1(String relatedBill1) {
        this.relatedBill1 = relatedBill1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWmsDoCode() {
        return wmsDoCode;
    }

    public void setWmsDoCode(String wmsDoCode) {
        this.wmsDoCode = wmsDoCode;
    }
}
