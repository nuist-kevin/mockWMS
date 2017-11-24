package com.focustech.mic.test.cb.entity.mount;

import com.focustech.mic.test.cb.entity.BusinessType;
import java.util.Date;
import java.util.List;

/**
 * @author caiwen
 */
public class DeliveryOrder {

  private String relatedBill1;
  private String relatedBill2;
  private String relatedBill3;
  private String billType;
  private Date orderDate;
  private String intendShipDate;
  private String carrierService;
  private String expectedQuantityBU;
  private List<TransitDeliveryListDetail> transitDeliveryListDetails;

  private BusinessType businessType;
  private Integer micVersion;
  private String operationCode;
  private String priority;
  private Integer resultType;
  private String type;

  private String company;
  private String companyCode;
  private Integer micComId;
  private String warehouse;
  private Integer wmsComId;

  private String contactName;
  private String mobile;
  private String fax;
  private String email;
  private String country;
  private String province;
  private String city;
  private String address;
  private String address2;
  private String postCode;
  private String description;

  public String getRelatedBill1() {
    return relatedBill1;
  }

  public void setRelatedBill1(String relatedBill1) {
    this.relatedBill1 = relatedBill1;
  }

  public String getRelatedBill2() {
    return relatedBill2;
  }

  public void setRelatedBill2(String relatedBill2) {
    this.relatedBill2 = relatedBill2;
  }

  public String getRelatedBill3() {
    return relatedBill3;
  }

  public void setRelatedBill3(String relatedBill3) {
    this.relatedBill3 = relatedBill3;
  }

  public String getBillType() {
    return billType;
  }

  public void setBillType(String billType) {
    this.billType = billType;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public String getIntendShipDate() {
    return intendShipDate;
  }

  public void setIntendShipDate(String intendShipDate) {
    this.intendShipDate = intendShipDate;
  }

  public String getCarrierService() {
    return carrierService;
  }

  public void setCarrierService(String carrierService) {
    this.carrierService = carrierService;
  }

  public String getExpectedQuantityBU() {
    return expectedQuantityBU;
  }

  public void setExpectedQuantityBU(String expectedQuantityBU) {
    this.expectedQuantityBU = expectedQuantityBU;
  }

  public List<TransitDeliveryListDetail> getTransitDeliveryListDetails() {
    return transitDeliveryListDetails;
  }

  public void setTransitDeliveryListDetails(List<TransitDeliveryListDetail> transitDeliveryListDetails) {
    this.transitDeliveryListDetails = transitDeliveryListDetails;
  }

  public BusinessType getBusinessType() {
    return businessType;
  }

  public void setBusinessType(BusinessType businessType) {
    this.businessType = businessType;
  }

  public Integer getMicVersion() {
    return micVersion;
  }

  public void setMicVersion(Integer micVersion) {
    this.micVersion = micVersion;
  }

  public String getOperationCode() {
    return operationCode;
  }

  public void setOperationCode(String operationCode) {
    this.operationCode = operationCode;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
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

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
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

  public String getWarehouse() {
    return warehouse;
  }

  public void setWarehouse(String warehouse) {
    this.warehouse = warehouse;
  }

  public Integer getWmsComId() {
    return wmsComId;
  }

  public void setWmsComId(Integer wmsComId) {
    this.wmsComId = wmsComId;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
