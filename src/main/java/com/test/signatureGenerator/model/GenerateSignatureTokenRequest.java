
package com.test.signatureGenerator.model;


public class GenerateSignatureTokenRequest {

    private String clientId;

    private String timestamp;

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
     * Getter method for property clientId.
     *
     * @return property value of clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Setter method for property clientId.
     *
     * @param clientId value to be assigned to property clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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

}
