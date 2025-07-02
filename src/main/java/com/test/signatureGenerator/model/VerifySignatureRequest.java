/**
 * dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package com.test.signatureGenerator.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: VerifySignatureRequest.java, v 0.1 2022‐09‐27 14.06 edward.chandra Exp $$
 */
public class VerifySignatureRequest {

    private JsonNode body;

    private String signature;

    private String publicKey;

    private String key;

    /**
     * Getter method for property key.
     *
     * @return property value of key
     */
    public String getKey() {
        return key;
    }

    /**
     * Setter method for property key.
     *
     * @param key value to be assigned to property key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Getter method for property body.
     *
     * @return property value of body
     */
    public JsonNode getBody() {
        return body;
    }

    /**
     * Setter method for property body.
     *
     * @param body value to be assigned to property body
     */
    public void setBody(JsonNode body) {
        this.body = body;
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
}