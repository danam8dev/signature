/**
 * dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package com.test.signatureGenerator.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.test.signatureGenerator.model.VerifySignatureRequest;
import com.test.signatureGenerator.model.VerifySignatureResult;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: VerifySignatureOpenAPI.java, v 0.1 2022‐09‐27 14.05 edward.chandra Exp $$
 */
@RestController
public class VerifySignatureOpenAPI {

    @PostMapping("verifySignatureOpenAPI")
    public ResponseEntity verifySignature(@RequestBody VerifySignatureRequest request){

        VerifySignatureResult verifySignatureResult = new VerifySignatureResult();

        try {

            JSONObject jsonObject = JSONObject.parseObject(request.getBody().toString(), Feature.OrderedField);

            String stringToSign = jsonObject.getString(request.getKey());

            verifySignatureResult.setStringToSign(stringToSign);

            boolean verified = verify(request.getSignature(), request.getPublicKey(), stringToSign);

            verifySignatureResult.setPublicKey(request.getPublicKey());

            if (verified){
                verifySignatureResult.setSuccess(true);
            } else {
                verifySignatureResult.setSuccess(false);
            }

        } catch (Exception e) {

            return ResponseEntity.ok(new VerifySignatureResult());
        }

        return ResponseEntity.ok(verifySignatureResult);

    }

    @PostMapping("generateSignatureOpenAPI")
    public ResponseEntity generateSignature(@RequestBody VerifySignatureRequest request){

        VerifySignatureResult verifySignatureResult = new VerifySignatureResult();

        try {

            JSONObject jsonObject = JSONObject.parseObject(request.getBody().toString(), Feature.OrderedField);

            String stringToSign = jsonObject.getString(request.getKey());

            verifySignatureResult.setStringToSign(stringToSign);

            String signature = signByPrivateKey(stringToSign, request.getPublicKey(), "SHA256withRSA");

            verifySignatureResult.setSuccess(true);
            verifySignatureResult.setSignature(signature);

        } catch (Exception e) {

            System.out.println(e);

            return ResponseEntity.ok(new VerifySignatureResult());
        }

        return ResponseEntity.ok(verifySignatureResult);

    }

    /**
     * @param publicKey publicKey
     * @return true or false
     * @throws InvalidKeyException      InvalidKeyException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     * @throws SignatureException       SignatureException
     */
    public boolean verify(String signature, String publicKey, String signContent)
            throws InvalidKeyException,
                   NoSuchAlgorithmException,
                   InvalidKeySpecException,
                   SignatureException {
        // 如果传入为空，则验证签名
        if (!StringUtils.isEmpty(signature)) {
            byte[] signContentByte = signContent.getBytes();
            // 验签时，需要对signature以及publicKey 进行decode
            return verify(signContentByte, Base64Utils.decode(signature.getBytes()),
                    Base64Utils.decode(publicKey.getBytes()), "SHA256withRSA");
        }
        //签名为空直接为false
        return false;
    }

    /**
     * 公共验签接口
     *
     * @param unSignedData 待验签数据
     * @param signedData   验签数据
     * @param key          秘钥
     * @param algorithm    算法
     * @return 验签结果
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     * @throws InvalidKeyException      InvalidKeyException
     * @throws SignatureException       SignatureException
     */
    public boolean verify(byte[] unSignedData, byte[] signedData, byte[] key,
                          String algorithm) throws NoSuchAlgorithmException,
                                                   InvalidKeySpecException, InvalidKeyException,
                                                   SignatureException {
        PublicKey publicKey = recoverPublicKey(key);
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);

        signature.update(unSignedData);
        return signature.verify(signedData);

    }

    /**
     * 恢复公钥
     *
     * @param data data
     * @return PublicKey
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     */
    public static PublicKey recoverPublicKey(byte[] data) throws NoSuchAlgorithmException,
                                                                 InvalidKeySpecException {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(data);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
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
}