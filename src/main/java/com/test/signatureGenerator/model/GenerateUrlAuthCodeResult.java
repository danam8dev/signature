/**
 * dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package com.test.signatureGenerator.model;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: GenerateUrlAuthCodeResult.java, v 0.1 2022‐02‐04 15.32 edward.chandra Exp $$
 */
public class GenerateUrlAuthCodeResult {

    private boolean isSuccess = false;

    private String url;

    /**
     * Getter method for property isSuccess.
     *
     * @return property value of isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Setter method for property isSuccess.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        isSuccess = success;
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