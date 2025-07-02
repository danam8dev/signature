/**
 * Alipay.com Inc.
 * Copyright(c) 2004‐2021 All Rights Reserved.
 */
package com.test.signatureGenerator.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.test.signatureGenerator.model.GenerateSignatureDynamicallyRequest;
import com.test.signatureGenerator.model.GenerateSignatureResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author Edward Chandra
 * @version $Id:GenerateSignatureDynamicallyController.java,v 0.1 2021‐10‐0616.36 Edward Chandra Exp $$
 */
@Controller
public class GenerateSignatureDynamicallyController {

    @PostMapping("/dynamic/generateSignature")
    public ResponseEntity generateSignature(@RequestBody GenerateSignatureDynamicallyRequest request){

        String signature = null;

        String stringToSign = null;

        try {

            stringToSign = composeAsymStringtoSign(request.getPayload().toString(), request.getUrl(), request.getHttpMethod(),
                    request.getTimestamp());

            signature = signByPrivateKey(stringToSign, request.getPrivateKey(), "SHA256withRSA");

        } catch (Exception e) {

            GenerateSignatureResult generateSignatureResult = new GenerateSignatureResult();

            return ResponseEntity.ok(generateSignatureResult);
        }

        GenerateSignatureResult generateSignatureResult = new GenerateSignatureResult();
        generateSignatureResult.setSuccess(true);
        generateSignatureResult.setSignature(signature);
        generateSignatureResult.setStringToSign(stringToSign);

        return ResponseEntity.ok(generateSignatureResult);

    }


    private String composeAsymStringtoSign(String payload, String url, String httpMethod,
                                           String timestamp) throws NoSuchAlgorithmException {

        StringBuilder sb = new StringBuilder();

        sb
                .append(httpMethod)
                .append(":")
                .append(url)
                .append(":")
                .append(digestHexSHA256(JSONObject.parseObject(payload, Feature.OrderedField).toJSONString()).toLowerCase())
                .append(":")
                .append(timestamp);



        return sb.toString();

    }

    public String digestHexSHA256(String str) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return new String(Hex.encodeHex(digest));

    }


    public String signByPrivateKey(String signContent, String privateKey,
                                   String algorithm) throws InvalidKeyException,
            NoSuchAlgorithmException, SignatureException,
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

    /**
     *
     * @param content
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset)
            throws UnsupportedEncodingException {
        if (charset.isEmpty()) {
            return content.getBytes();
        }
        return content.getBytes(charset);
    }
}
