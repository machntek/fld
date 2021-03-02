package com.machntek.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.machntek.application.dto.FirstTest;
import com.machntek.fld.parser.FldParser;
import org.springframework.stereotype.Service;

@Service
public class FldService {

    public void test(String data) throws JsonProcessingException {
        FirstTest test =  FldParser.parseRequestBody(data, FirstTest.class);


        System.out.println(new ObjectMapper().writeValueAsString(test));

    }
}
