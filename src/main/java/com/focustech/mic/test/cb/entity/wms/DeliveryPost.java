package com.focustech.mic.test.cb.entity.wms;

import java.util.Date;
import java.util.List;

/**
 * Created by caiwen on 2017/4/24.
 */
public class DeliveryPost {

    private String bolCode;
    private String businessType = "WMS2OSS_DELIVERYPOST";
    private String carrierService;

    private String companyCode;
    private Long micComId;
    private String operatorName = "liangbiao";
    private Integer operatorNo = 39;
    private final Date operatorTime = new Date();
    private String pickCode;
    private String relatedBill1;

    private List<DeliverCargo> sendPostingDetails;

    private String warehouseCode;

    public String getBolCode() {
        return bolCode;
    }

    public void setBolCode(String bolCode) {
        this.bolCode = bolCode;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public String getPickCode() {
        return pickCode;
    }

    public void setPickCode(String pickCode) {
        this.pickCode = pickCode;
    }

    public String getRelatedBill1() {
        return relatedBill1;
    }

    public void setRelatedBill1(String relatedBill1) {
        this.relatedBill1 = relatedBill1;
    }

    public List<DeliverCargo> getSendPostingDetails() {
        return sendPostingDetails;
    }

    public void setSendPostingDetails(List<DeliverCargo> sendPostingDetails) {
        this.sendPostingDetails = sendPostingDetails;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}
