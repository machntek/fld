package com.machntek.application.dto;

import com.machntek.fld.annotation.Fld;
import com.machntek.fld.annotation.FldElement;
import com.machntek.fld.dto.FldBody;
import lombok.Getter;

@Getter
@Fld
public class FirstTest implements FldBody {

    @FldElement(order = 1, size = 10)
    private String test;

    @FldElement(order = 2, size = 7)
    private int testNum;
}
