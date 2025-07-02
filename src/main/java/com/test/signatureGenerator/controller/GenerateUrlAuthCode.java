/**
 * dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package com.test.signatureGenerator.controller;

import com.alibaba.fastjson.JSON;
import com.test.signatureGenerator.model.GenerateSignatureResult;
import com.test.signatureGenerator.model.GenerateUrlAuthCodeRequest;
import com.test.signatureGenerator.model.GenerateUrlAuthCodeResult;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.UUID;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: GenerateUrlAuthCode.java, v 0.1 2022‐02‐04 14.41 edward.chandra Exp $$
 */

@RestController
public class GenerateUrlAuthCode {

    @PostMapping("/generateUrlAuthCode")
    public ResponseEntity generateSignature(@RequestBody GenerateUrlAuthCodeRequest request){

        String url = null;

        try {

            String requestId = UUID.randomUUID().toString();

            url = String.format("%s/v1.0/get-auth-code?timestamp=%s&partnerId=%s&externalId=%s&channelId=%s&state=%s&scopes=%s&redirectUrl=%s&seamlessData=%s&seamlessSign=%s&merchantId=%s&subMerchantId=%s&lang=%s&allowRegistration=%s",
                    request.getUrlEndpoint()
                    ,request.getTimestamp()
                    ,request.getPartnerId()
                    ,requestId
                    ,request.getChannelId()
                    ,request.getState(),
                    String.join(",",request.getScopes())
                    ,request.getRedirectUrl()
                    ,request.getSeamlessData() != null ? URLEncoder.encode(JSON.toJSONString(request.getSeamlessData()),"UTF-8") : ""
                    ,request.getSeamlessData() != null ? URLEncoder.encode(signByPrivateKey(JSON.toJSONString(request.getSeamlessData()), request.getPrivateKey(), "SHA256withRSA"),"UTF-8"): ""
                    ,request.getMerchantId()
                    ,request.getSubMerchantId()
                    ,request.getLang()
                    ,request.getAllowRegistration()
            ) ;

        } catch (Exception e) {

            GenerateSignatureResult generateSignatureResult = new GenerateSignatureResult();

            System.out.println(e.getMessage());

            return ResponseEntity.ok(generateSignatureResult);
        }

        GenerateUrlAuthCodeResult generateUrlAuthCodeResult = new GenerateUrlAuthCodeResult();
        generateUrlAuthCodeResult.setSuccess(true);
        generateUrlAuthCodeResult.setUrl(url);

        return ResponseEntity.ok(generateUrlAuthCodeResult);

    }

    public String signByPrivateKey(String signContent, String privateKey,
                                   String algorithm) throws InvalidKeyException,
                                                            NoSuchAlgorithmException,
                                                            SignatureException,
                                                            InvalidKeySpecException {

        if (StringUtils.isEmpty(signContent)) {
            signContent = "";
        }
        byte[] signContentByte = signContent.getBytes();

        byte[] signResult = sign(signContentByte, Base64.decodeBase64(privateKey.getBytes()),
                algorithm);

        String signStr = new String(Base64.encodeBase64(signResult));
        return signStr;
    }

    private byte[] sign(byte[] data, byte[] key, String algorithm)
            throws NoSuchAlgorithmException,
                   InvalidKeyException,
                   SignatureException,
                   InvalidKeySpecException {

        PrivateKey privateKey = recoverPrivateKey(key);
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();

    }


    private PrivateKey recoverPrivateKey(byte[] data) throws NoSuchAlgorithmException,
                                                             InvalidKeySpecException {

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(data);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);

    }

}