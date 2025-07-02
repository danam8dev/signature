/**
 * dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package com.test.signatureGenerator.model;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: VerifySignatureResult.java, v 0.1 2022‐09‐27 14.21 edward.chandra Exp $$
 */
public class VerifySignatureResult {

    /**
     * Getter method for property success.
     *
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property success.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

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
     * Getter method for property publicKey.
     *
     * @return property value of publicKey
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Setter method for property publicKey.
     *
     * @param publicKey value to be assigned to property publicKey
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    private boolean success;

    private String stringToSign;

    private String publicKey;

    private String signature;

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