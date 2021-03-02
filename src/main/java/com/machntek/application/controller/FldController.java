package com.machntek.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.machntek.application.service.FldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FldController {
    private final FldService fldService;

    @PostMapping("/")
    public String testFunc(@RequestParam String data) throws JsonProcessingException {
        fldService.test(data);
        return "succ!";
    }
}

