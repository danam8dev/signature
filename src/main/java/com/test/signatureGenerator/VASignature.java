/**
 * dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package com.test.signatureGenerator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

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
import java.util.HashMap;
import java.util.Map;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: VASignature.java, v 0.1 2023‐06‐22 12.50 edward.chandra Exp $$
 */
public class VASignature {

    public static void main(String[] args) {

        /******
         *  generate signature
         */
        String stringToSign = "{\"virtualAccountExpiryTime\":\"2022-03-14T16:23:55+07:00\",\"virtualAccountCode\":\"83928391283913\"}";
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDW5ypxYcAjyMMuB6kmQMqks562w1J9bXjFdGUHnRJ7COeW2Hza4vdG2FBBn+q3dD9t4KQ4hmF26MmhGIISkcwU9e/pKibRcUJao3vgSLOeYNz0pe5RSNRclXwLmFzDwXozSrMZTabovKJMaaImpRYTgHogyypbUOs5NHhSRq5hlizBjwFcPHV6Q7jDS5IEq1yKd2xj18gfcRSfD15193pJK38ik4Kjd93s4Ri1EJ2ZF+9kNUR4dXx38TomGj2ya2u3J0+PP36qBLanDXAHXSBXzLsrUEbZzuE/pZvNT6EQgfD4gRhhxRhxVhTxr3EAo+/L8oYj78OKD29jLk74jqtlAgMBAAECggEAeD+LWTwoJh3NUPFKbbQtb7FuKrfqbXKW9G2npnX2hE+8PoTOgDAly0d4yO+xh4uNfm8HlPVkjgO95t1u0vMv7b6byq0iYWlthogZ5arojsOGf4Jlcqg8471lVpuftI2LUi4HPy5bKbl7WyR0bglL7Aekzrq/7a6+oBjDBs6z/rfg1E3qwLbgor/6ttboWZxOA55tV7GokO94KD2D7Sw89/ZfQDr3kKsWaCLoVtlkQ3U0mId2rZzFdOw2BTjixpmYhmy1kzSuGvJTkaS/yp/FS1d3Jri9k5GFpWIpKQ8Z6rE6MVIGCdehymfRHFA1V03mbEN7UGhTLhRsKeA3mAHfVQKBgQD/BV5cMUmjBsA11EPeCc3kNBuMwZ4wUe294yyUBUPhHVnwC/bV9ErlDW9GJRyrIK01nvYSTw/s8HyfR53lS9/F3dh99EVgvFdCPriwenl1uQu7bc85n2iBoAyIbA/WgH/i2EoCpZJB3BSSQk8UUizQfUFWsooYR6F7q8SXqQ0uJwKBgQDXul6oEUSdrXHW3hGkhTlM+NFF8HYBMprEMOH1eSeGEdxijWfy0ypFHh/waix1HudAk0iR6ZgjMXdHcXUM2U94hzIge/VsaxhWTHmDm+M2mitH+m9aT2JSs8gGrEWnzHLOVjDSCM9wPAN83KyBko26iR3h4L8a+TcyyaM+Pp9dkwKBgQCHJqU3XreTuoQrAu9kwPVot2TSV9op6YGBXN/HInMy3odHfE+4W9tFQLV0Cz8CQjMJc9EdKuFZxKmHEEFRv8A78LJHLimGdsoXxX3DqWStSwA4uJ1WAHE2IUgDFKHgIAGmitHOTUkRtj0AtjuKnXI6m+cAt2YQMAYYsJNjfKgLiQKBgBwQpOvcgz64J806SrVKl6/J1v/wTonNWZEYguz6fAfXHwK7PsC6dA9V4fXW+j2qbY9bGd/RsfcyIkBrJD0tsEcP35eWSNF4BUyE2nIiv6aE3AibLg1jsnjr5qPnmZBA6tapI+TPPJ1vUUzXMKISvNl9qSOPp40Myv+Sd+hLIVR9AoGBAI89WmyegJ7rR0YnhjkIWlouhRwH0JM3t+Ht2B7IYpeJzjQHG6jMS/1nWWu5BtgNuHnQ/DATRTMG/kZ0DUtH3ZwIRAZUmj8NmedzAiP3ZsiArf0hwc+gT9Mzu8PP7kPCTELwP1UB6fur87yixrkPAoluF8uTmrnzJBvzNgcXbgeP";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1ucqcWHAI8jDLgepJkDKpLOetsNSfW14xXRlB50Sewjnlth82uL3RthQQZ/qt3Q/beCkOIZhdujJoRiCEpHMFPXv6Som0XFCWqN74EiznmDc9KXuUUjUXJV8C5hcw8F6M0qzGU2m6LyiTGmiJqUWE4B6IMsqW1DrOTR4UkauYZYswY8BXDx1ekO4w0uSBKtcindsY9fIH3EUnw9edfd6SSt/IpOCo3fd7OEYtRCdmRfvZDVEeHV8d/E6Jho9smtrtydPjz9+qgS2pw1wB10gV8y7K1BG2c7hP6WbzU+hEIHw+IEYYcUYcVYU8a9xAKPvy/KGI+/Dig9vYy5O+I6rZQIDAQAB";

        Map<String, String> VAInfoInAPIResponse = JSONObject
                .parseObject(stringToSign, new TypeReference<HashMap<String, String>>() {
                });

        try {
            String signature = signByPrivateKey(stringToSign, privateKey, "SHA256withRSA");

            VAInfoInAPIResponse
                    .put("signature", new String(Base64Utils.encode(signature.getBytes())));
        } catch (Exception e) {

            System.out.println("Error while generate signature");
        }

        //print the virtualAccount response
        System.out.println(
                "Result of virtualAccountInfo : " + JSON.toJSONString(VAInfoInAPIResponse));

        /*****
         * verifying signature
         */
        String stringToVerify = JSON.toJSONString(VAInfoInAPIResponse);

        Map<String, String> VAInfoToVerify = JSONObject
                .parseObject(stringToVerify, new TypeReference<HashMap<String, String>>() {
                }, Feature.OrderedField);

        String signature = new String(Base64Utils.decode(VAInfoToVerify.get("signature").getBytes()));

        VAInfoToVerify.remove("signature");
        String stringToSignForVerify = JSON.toJSONString(VAInfoToVerify);

        try {
            boolean verified = verify(signature, publicKey, stringToSignForVerify);

            System.out.println("Result of verify signature: " + verified);
        } catch (Exception e) {
            System.out.println("Error while verifying signature");
        }

    }

    public static String signByPrivateKey(String signContent, String privateKey, String algorithm)
            throws InvalidKeyException, NoSuchAlgorithmException, SignatureException,
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

    private static byte[] sign(byte[] data, byte[] key, String algorithm)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
                   InvalidKeySpecException {

        PrivateKey privateKey = recoverPrivateKey(key);
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();

    }

    private static PrivateKey recoverPrivateKey(byte[] data)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(data);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);

    }

    public static boolean verify(String signature, String publicKey, String signContent)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
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

    public static boolean verify(byte[] unSignedData, byte[] signedData, byte[] key,
                                 String algorithm)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
                   SignatureException {
        PublicKey publicKey = recoverPublicKey(key);
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);

        signature.update(unSignedData);
        return signature.verify(signedData);

    }

    public static PublicKey recoverPublicKey(byte[] data)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(data);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
}