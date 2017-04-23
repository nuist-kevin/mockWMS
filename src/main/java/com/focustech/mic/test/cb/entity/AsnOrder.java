package com.focustech.mic.test.cb.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by caiwen on 2017/4/21.
 */
public class AsnOrder {

    private String billTypeCode;
    private String businessType;
    private Integer resultType;
    private String type;

    private String warehouseCode;
    private String  companyCode;
    private Integer micComId;

    private String relatedBill1;
    private String micAsnOrderNo;
    private Date orderDate;
    private Date estimateDate;
    private Integer expectedQuantityBU;
    private List<AsnCargo> receiptListDetails;
    private List<Integer> detailSnapIds;
    private List<Integer> cargoCountIds;
    private Integer micVersion;

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getMicComId() {
        return micComId;
    }

    public void setMicComId(Integer micComId) {
        this.micComId = micComId;
    }

    public String getRelatedBill1() {
        return relatedBill1;
    }

    public void setRelatedBill1(String relatedBill1) {
        this.relatedBill1 = relatedBill1;
    }

    public String getMicAsnOrderNo() {
        return micAsnOrderNo;
    }

    public void setMicAsnOrderNo(String micAsnOrderNo) {
        this.micAsnOrderNo = micAsnOrderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(Date estimateDate) {
        this.estimateDate = estimateDate;
    }

    public Integer getExpectedQuantityBU() {
        return expectedQuantityBU;
    }

    public void setExpectedQuantityBU(Integer expectedQuantityBU) {
        this.expectedQuantityBU = expectedQuantityBU;
    }

    public List<AsnCargo> getReceiptListDetails() {
        return receiptListDetails;
    }

    public void setReceiptListDetails(List<AsnCargo> receiptListDetails) {
        this.receiptListDetails = receiptListDetails;
    }

    public List<Integer> getDetailSnapIds() {
        return detailSnapIds;
    }

    public void setDetailSnapIds(List<Integer> detailSnapIds) {
        this.detailSnapIds = detailSnapIds;
    }

    public List<Integer> getCargoCountIds() {
        return cargoCountIds;
    }

    public void setCargoCountIds(List<Integer> cargoCountIds) {
        this.cargoCountIds = cargoCountIds;
    }

    public Integer getMicVersion() {
        return micVersion;
    }

    public void setMicVersion(Integer micVersion) {
        this.micVersion = micVersion;
    }
}