/**
 * dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package com.test.signatureGenerator.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Edward Chandra <edward.chandra@dana.id>
 * @version $Id: BIMockController.java, v 0.1 2022‐03‐31 21.14 edward.chandra Exp $$
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BIMockController {


    @RequestMapping(method = RequestMethod.OPTIONS, value="/v1.0/get-auth-code", produces="application/json")
    public ResponseEntity generateSignature(@RequestParam String clientId){


        ObjectNode rootNode = new ObjectMapper().createObjectNode();


        if (clientId.startsWith("1")){
            rootNode.put("responseCode","4011000");
            rootNode.put("responseMessage","Unauthorized. Invalid Signature");
        } else {

            rootNode.put("responseCode","2001000");
            rootNode.put("responseMessage","Successful");
            rootNode.put("authCode","a4sd5a4fsaf5d5f4df66ad85f4");
            rootNode.put("state","WodkkwijSDs");
        }


        return ResponseEntity.ok(rootNode.toPrettyString());

    }


}