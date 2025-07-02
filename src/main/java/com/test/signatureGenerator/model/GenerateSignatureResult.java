/**
 * Alipay.com Inc.
 * Copyright(c) 2004‐2021 All Rights Reserved.
 */
package com.test.signatureGenerator.model;

/**
 * @author Edward Chandra
 * @version $Id:GenerateSignatureResult.java,v 0.1 2021‐09‐2019.09 Edward Chandra Exp $$
 */
public class GenerateSignatureResult {

    private boolean isSuccess = false;

    private String signature;

    private String stringToSign;

    /**
     * Getter method for property stringToSign.
     *
     * @return property value of stringToSign
     */
    public String getStringToSign() {
        return stringToSign;
    }

    /**
     * Setter method for property stringToSign.
     *
     * @param stringToSign value to be assigned to property stringToSign
     */
    public void setStringToSign(String stringToSign) {
        this.stringToSign = stringToSign;
    }

    /**
     * Getter method for property isSuccess.
     *
     * @return property value of isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Setter method for property success.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    /**
     * Getter method for property signature.
     *
     * @return property value of signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Setter method for property signature.
     *
     * @param signature value to be assigned to property signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }
}
