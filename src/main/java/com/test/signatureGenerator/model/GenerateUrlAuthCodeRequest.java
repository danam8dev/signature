/**
 * dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package com.test.signatureGenerator.model;

import java.util.List;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: GenerateUrlAuthCodeRequest.java, v 0.1 2022‐02‐04 14.46 edward.chandra Exp $$
 */
public class GenerateUrlAuthCodeRequest {

    private SeamlessData seamlessData;

    private String partnerId;

    private String timestamp;

    private String channelId;

    private String merchantId;

    private String subMerchantId;

    private List<String> scopes;

    private String redirectUrl;

    private String state;

    private String lang;

    private String allowRegistration;

    private String urlEndpoint;

    private String privateKey;

    /**
     * Getter method for property seamlessData.
     *
     * @return property value of seamlessData
     */
    public SeamlessData getSeamlessData() {
        return seamlessData;
    }

    /**
     * Setter method for property seamlessData.
     *
     * @param seamlessData value to be assigned to property seamlessData
     */
    public void setSeamlessData(SeamlessData seamlessData) {
        this.seamlessData = seamlessData;
    }

    /**
     * Getter method for property partnerId.
     *
     * @return property value of partnerId
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Setter method for property partnerId.
     *
     * @param partnerId value to be assigned to property partnerId
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * Getter method for property timestamp.
     *
     * @return property value of timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Setter method for property timestamp.
     *
     * @param timestamp value to be assigned to property timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter method for property channelId.
     *
     * @return property value of channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * Setter method for property channelId.
     *
     * @param channelId value to be assigned to property channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * Getter method for property merchantId.
     *
     * @return property value of merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * Setter method for property merchantId.
     *
     * @param merchantId value to be assigned to property merchantId
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * Getter method for property subMerchantId.
     *
     * @return property value of subMerchantId
     */
    public String getSubMerchantId() {
        return subMerchantId;
    }

    /**
     * Setter method for property subMerchantId.
     *
     * @param subMerchantId value to be assigned to property subMerchantId
     */
    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }

    /**
     * Getter method for property scopes.
     *
     * @return property value of scopes
     */
    public List<String> getScopes() {
        return scopes;
    }

    /**
     * Setter method for property scopes.
     *
     * @param scopes value to be assigned to property scopes
     */
    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    /**
     * Getter method for property redirectUrl.
     *
     * @return property value of redirectUrl
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * Setter method for property redirectUrl.
     *
     * @param redirectUrl value to be assigned to property redirectUrl
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * Getter method for property state.
     *
     * @return property value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Setter method for property state.
     *
     * @param state value to be assigned to property state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter method for property lang.
     *
     * @return property value of lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * Setter method for property lang.
     *
     * @param lang value to be assigned to property lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Getter method for property allowRegistration.
     *
     * @return property value of allowRegistration
     */
    public String getAllowRegistration() {
        return allowRegistration;
    }

    /**
     * Setter method for property allowRegistration.
     *
     * @param allowRegistration value to be assigned to property allowRegistration
     */
    public void setAllowRegistration(String allowRegistration) {
        this.allowRegistration = allowRegistration;
    }

    /**
     * Getter method for property urlEndpoint.
     *
     * @return property value of urlEndpoint
     */
    public String getUrlEndpoint() {
        return urlEndpoint;
    }

    /**
     * Setter method for property urlEndpoint.
     *
     * @param urlEndpoint value to be assigned to property urlEndpoint
     */
    public void setUrlEndpoint(String urlEndpoint) {
        this.urlEndpoint = urlEndpoint;
    }

    /**
     * Getter method for property privateKey.
     *
     * @return property value of privateKey
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * Setter method for property privateKey.
     *
     * @param privateKey value to be assigned to property privateKey
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override public String toString() {
        return "GenerateUrlAuthCodeRequest{" + "seamlessData=" + seamlessData + ", partnerId='"
               + partnerId + '\'' + ", timestamp='" + timestamp + '\'' + ", channelId='" + channelId
               + '\'' + ", merchantId='" + merchantId + '\'' + ", subMerchantId='" + subMerchantId
               + '\'' + ", scopes=" + scopes + ", redirectUrl='" + redirectUrl + '\'' + ", state='"
               + state + '\'' + ", lang='" + lang + '\'' + ", allowRegistration='"
               + allowRegistration + '\'' + '}';
    }
}