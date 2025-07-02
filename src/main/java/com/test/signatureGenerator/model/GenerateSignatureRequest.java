
package com.test.signatureGenerator.model;

import java.util.List;

public class GenerateSignatureRequest {

    private String httpMethod;

    private String url;

    private String timestamp;

    private String payload;

    /**
     * Getter method for property test.
     *
     * @return property value of test
     */
    public List<String> getTest() {
        return test;
    }

    /**
     * Setter method for property test.
     *
     * @param test value to be assigned to property test
     */
    public void setTest(List<String> test) {
        this.test = test;
    }

    private List<String> test;


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
     * Getter method for property payload.
     *
     * @return property value of payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Setter method for property payload.
     *
     * @param payload value to be assigned to property payload
     */
    public void setPayload(String payload) {
        this.payload = payload;
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
