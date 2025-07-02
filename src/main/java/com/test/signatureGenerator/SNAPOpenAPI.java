/**
 * dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package com.test.signatureGenerator;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;


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
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: SNAPOpenAPI.java, v 0.1 2023‐06‐27 18.33 edward.chandra Exp $$
 */
public class SNAPOpenAPI {

    public static void main(String[] args) {

        String payload = "{\"partnerReferenceNo\":\"20230626000001\",\"merchantId\":\"216620000095853391534\",\"amount\":{\"value\":\"100.00\",\"currency\":\"IDR\"},\"externalStoreId\":\"externalShopId\",\"validUpTo\":\"2023-06-27T09:41:17+07:00\",\"title\":\"Lazada VA\",\"payOptionDetails\":[{\"payMethod\":\"VIRTUAL_ACCOUNT\",\"payOption\":\"BCA\",\"transAmount\":{\"currency\":\"IDR\",\"value\":\"100.00\"},\"cardToken\":\"\",\"additionalInfo\":{}}]}";

        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCiba7jOOo6Kljzu/S60lYfcZp2slTyKiv7V7uWKRqHrbFBWRMsQ4O1m5IA6j2qnSjUyRJOWIEgU39LANb2FYpNDrqB8SDnRAQgXUbcjPC3qON54ZWb6e0gsr/wrqFN5HlON6/+hZk+VKpsbvfyDtJofgaJELyBXHDfcFoKL9bdJP39D4YXx2c1QpPhiirHBsbh2LgdR8fHUKzjiaLHuOtUvBHOaWO10OwkI90iSV6J2cCbYBbkpMbW8s46JMNVJxIqEQOyvIm+smGFD0gtRp33+Xtjn/FYtXnmuN4igAvh/E4YHH2ic8MNjO17Wn2Tyw6aaXEr14oSlTw18uScKPhPAgMBAAECggEAZA+3vkjQmnZ+B+CRGgn7fjFo25huliR+pb7dU9sMeer5mG08T3dkCxvEbQXgaVuZzMP1oAgLvBNUBquEwWJxqZjaMrSN9YpEFIAB6Fp27F2BjRL4LG2GZC9Z6PZbbnXCePn7VIuO5QQZXOVbn2WcHz0L36JMzlBZRHydK0tfTSm9AV0bNYXjQqc97RteNCbjbs9qhZQwaChkb69qwVB0R3DUVdplUgPEXbJ9oE99ZsyT6tU4sln9C7YmUcO0p1uZ9JHKxBawqRHbnEolorQ9B3WtJnkfg5em44gNBkoIWh22bkAEWJ+OPPY6SZqe/FdIJG9uG2W1FJQhMgXsfAO8QQKBgQD7VNvGCPdgjo6aEhWzWRJE1Te4FscpQMMuj4pZERx7ckPxNCJVTSEmx/T12akNqDYVrlzozt2rwVGdj4wdVUKyE2XhwZC/6lm+roOVjEd7olXmJXkU/mA7WpV1b6JY1nP1WDYFfTAR74NtNaqSo7uRAafvAnDATLa9DsiT4F4FrwKBgQClchHFrz+ry7YlpiEu/PQv79yAL+dfht77inJ8cSbfn8gX78rRiLHcazzDNjSMXp/W72or4zLY7P1hPO5F3X9h3ocr32UKrdj29IOh9a4EXPbuC7Vb9vbRwYQirJ6xQyeBY3QwuwlTNKvLSSmdRMXZ27FeE7EJaHkdKqIL/Hl/YQKBgBxl2TNGHhV15hz4gCU0okDOyq3tPPGnVKZ1J4+qxEoafhzJ5Ds5LM7B9ya0Qkq9v+axPeQ8Q4W9oSgpaQ6DbLbMRE2+3kkhBKTr+Qgzwnvwur33a/f4Sg1KzepoA7MAADgvkGMMYxsTsgjr8GsDPxVCV6RE5VKFGHzCJ921k0oXAoGBAIp8o1fATCG30W5OU3SLlLMTqpU6hAg4UypnR1HXPVzu2IUA2dru+0KwGRqmovHkuxMlMNAvy/480hO09xZQRDJeHPOieWepAfYl0pGdnt5UGvZDormqTgxiWgLUT7m9prHt1/J6fWHCsxLk2Qql6J9mAF/pR8XCR68cYqx52BHBAoGBAI6CT+2ooIMhMg8IgkHKW6P09Xe2iQIGhIJM5+Eq1BQ7Dy27e/LysX5pgDeAznuJmspgmAdIqutMzSaLw3p2LRj+IQG9ZZIoSyuIqVZbI58202F/eZQorJe3OfTd5vhgHkXLcgbQ23Y5JC37OIZqF1KfP2AWUHbnAgV6AIMMI5bV";

        String url = "/v1.0/quick-pay.htm";

        String httpMethod = "POST";

        String timestamp = "2023-06-27T09:41:17+07:00";


        String signature = null;

        String stringToSign = null;

        try {

            //compose stringToSign
            stringToSign = composeAsymStringtoSign(payload,
                    url, httpMethod, timestamp);


            //sign by privateKey
            signature = signByPrivateKey(stringToSign, privateKey, "SHA256withRSA");

        } catch (Exception e) {

            System.out.println("Failed generate signature");

        }

        System.out.println("StringToSign: " + stringToSign);

        System.out.println("Signature: " + signature);

    }

    private static String composeAsymStringtoSign(String payload, String url, String httpMethod,
                                           String timestamp) throws NoSuchAlgorithmException {

        StringBuilder sb = new StringBuilder();

        sb.append(httpMethod).append(":").append(url).append(":").append(digestHexSHA256(
                JSONObject.parseObject(payload, Feature.OrderedField).toJSONString()).toLowerCase())
                .append(":").append(timestamp);

        return sb.toString();

    }

    public static String digestHexSHA256(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return new String(Hex.encodeHex(digest));

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

    private static  PrivateKey recoverPrivateKey(byte[] data)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(data);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);

    }
}

