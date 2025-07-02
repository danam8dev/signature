
package com.test.signatureGenerator.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.test.signatureGenerator.model.GenerateSignatureDynamicallyRequest;
import com.test.signatureGenerator.model.GenerateSignatureResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class GenerateSignatureControllerNew {

    private static final String key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRoodwd+Jf+8xt"
            + "9xlONRt3x1kVi/GDfwQNdlYRo92cHqKwqf76oceRs+YtJPG2igrnft2lVvjf2p7c"
            + "+0VZaPhynSarNGTYF03hQZ1r5SWXcnZn+PpO+IPjLbLa7tCHCCtckAOouSiweKhY"
            + "4gko+lp30p9Ik0LNFeIz0OEQxUgS+/KPBSMa5AWTM93St4hvPfZIHp2JEaFGkp8c"
            + "UF3w6bf9q2r1Q1BZ5egmf4D5n4GvOfma0Jgxum6BzxZ9eFml4YK8nsw2mMRpxOW+"
            + "3o/SkIEWDqAWsAWZt8L22BO+tmkBo5c/JTwUpt3epikWaRdXOzO8YVns1DaQ6nAq"
            + "zRY0hkX7AgMBAAECggEAEy8eukEAj/ZCWqX3LmcuhBu9b2os2U6NARz6f35ffeZH"
            + "NhBepzNSb64L/xxjvtD2WMJMfjAzvoE09hUfcIS0XZy38EuJkTGYr7iN/R2FUyrf"
            + "0BhrGKYB8ZYeflqBG5iG5b2OzdX+NyjdvD+iR0SbUDdthgjn/s4IKx0z2Jq0QNO+"
            + "mnuSZWfEkumACdK6kWhvn3apld8q2kmqxBLcuPmZnTw21AIRDyUOeVjHr9siGXfi"
            + "p7tUjRy1b5XGrsqr3uMsBotxieXdrZmLCSPfz9o7UyrhEDMnx/LSVf6Y5b8blOOP"
            + "v2MEpMaFknYxyj6huCMHWndvpPrdWUsWRUwbsebmIQKBgQDIroZ99C/fGTrZE/PG"
            + "J92RDrEzGOgKXtmRJohvczABlN0u0ovkoiDgCZyCf+q5nzHkIffGgNE/y7QoQKxY"
            + "vgKsRbJcrl4nPccC7BJ/r0YcLIwKAVpridFIo6t8txjxdDtWOSuIY5ZIO+YTLExg"
            + "o4GGYG5uFcJijGSxH16g/as4iwKBgQC5x4Vo5UhrT+RZ9c8Lm7Yfio0ky6a8buYC"
            + "BHDf4G7R0HNBb+Df5QSIDnAVy0JR2GbacWeBDLX75+rD4A81b3mxFGZ1PsPN6Jyx"
            + "MU5VPS1MdzgHtruUz3HuN4JhlWsxv23ALgPIg7/Em40prjMDqP+G0wtSqqm77yit"
            + "JvBXUrZmUQKBgQCmLYcnM5aUmrLVYSVDGG3sGYt4sY4sUEC/ZnYiO/MrzBagGC9J"
            + "g0vP2YWltsErNn082TfxQ2iC+G7VykAtOCLEfu5kuusyeYCbldr3qq9W0zifbGFw"
            + "nW4VRZrSqlfYUZR1IOBAj565cAhkJhGVELBIEwA+3nvZ2UHkPgLBM22eYwKBgD6B"
            + "dM+AJUeFz8y8uXasYzBli2uP4X+QyG5xVBDfECojukOQAtcR1N56DRgoFR44cxKY"
            + "CT0QK8KfmwtgPX27DfSYaoiUH4kN9HphnntrY+xQSFfAEYjV6v09S+7NOQ253JFG"
            + "ahtyyHi75VjatWtL1v1j0HF13jnPE55+dWrL93ahAoGAJTcgoXBmo5toF2oDarx4"
            + "6Kv1swH/6eIiHOjAaenTzeWjdfP4tDsDkmndeiolWkdSQkH/Af1E5eE9w8tYJRot"
            + "YBvME1MnbzYFdLL26Gv9Kt9HmuKmutMACv5arKTT2ImV57YUI/YoxycON4lhULYj"
            + "l7y0fvPPPLDQrXen6pPY500=";

    @PostMapping("generateSignatureNew")
    public ResponseEntity generateSignature(@RequestBody GenerateSignatureDynamicallyRequest request){

        String signature = null;

        try {

            String stringToSign = composeAsymStringtoSign(request.getPayload().toString(), request.getUrl(), request.getHttpMethod(),
                    request.getTimestamp());

            signature = signByPrivateKey(stringToSign, key, "SHA256withRSA");

        } catch (Exception e) {

            GenerateSignatureResult generateSignatureResult = new GenerateSignatureResult();

            return ResponseEntity.ok(generateSignatureResult);
        }

        GenerateSignatureResult generateSignatureResult = new GenerateSignatureResult();
        generateSignatureResult.setSuccess(true);
        generateSignatureResult.setSignature(signature);

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


}
