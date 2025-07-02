
package com.test.signatureGenerator.model;

import com.fasterxml.jackson.databind.JsonNode;

public class GenerateSignatureDynamicallyRequest {

    private String httpMethod;

    private String url;

    private String timestamp;

    private JsonNode payload;

    private String privateKey;

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

    /**
     * Getter method for property payload.
     *
     * @return property value of payload
     */
    public JsonNode getPayload() {
        return payload;
    }

    /**
     * Setter method for property payload.
     *
     * @param payload value to be assigned to property payload
     */
    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    /**
     * Getter method for property httpMethod.
     *
     * @return property value of httpMethod
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * Setter method for property httpMethod.
     *
     * @param httpMethod value to be assigned to property httpMethod
     */
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
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
     * Getter method for property url.
     *
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property url.
     *
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
