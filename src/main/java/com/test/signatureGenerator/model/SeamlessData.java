/**
 * dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package com.test.signatureGenerator.model;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: SeamlessData.java, v 0.1 2022‐02‐04 14.52 edward.chandra Exp $$
 */
public class SeamlessData {

    private String mobileNumber;

    private String externalUid;

    private String deviceId;

    private String verifiedTime;

    private String bizScenario;


    /**
     * Getter method for property mobileNumber.
     *
     * @return property value of mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Setter method for property mobileNumber.
     *
     * @param mobileNumber value to be assigned to property mobileNumber
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Getter method for property externalUid.
     *
     * @return property value of externalUid
     */
    public String getExternalUid() {
        return externalUid;
    }

    /**
     * Setter method for property externalUid.
     *
     * @param externalUid value to be assigned to property externalUid
     */
    public void setExternalUid(String externalUid) {
        this.externalUid = externalUid;
    }

    /**
     * Getter method for property deviceId.
     *
     * @return property value of deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Setter method for property deviceId.
     *
     * @param deviceId value to be assigned to property deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Getter method for property verifiedTime.
     *
     * @return property value of verifiedTime
     */
    public String getVerifiedTime() {
        return verifiedTime;
    }

    /**
     * Setter method for property verifiedTime.
     *
     * @param verifiedTime value to be assigned to property verifiedTime
     */
    public void setVerifiedTime(String verifiedTime) {
        this.verifiedTime = verifiedTime;
    }

    /**
     * Getter method for property bizScenario.
     *
     * @return property value of bizScenario
     */
    public String getBizScenario() {
        return bizScenario;
    }

    /**
     * Setter method for property bizScenario.
     *
     * @param bizScenario value to be assigned to property bizScenario
     */
    public void setBizScenario(String bizScenario) {
        this.bizScenario = bizScenario;
    }

    @Override public String toString() {
        return "SeamlessData{" + "mobileNumber='" + mobileNumber + '\'' + ", externalUid='"
               + externalUid + '\'' + ", deviceId='" + deviceId + '\'' + ", verifiedTime='"
               + verifiedTime + '\'' + '}';
    }
}