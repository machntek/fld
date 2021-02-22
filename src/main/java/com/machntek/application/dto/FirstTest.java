package com.machntek.application.dto;

import com.machntek.fld.annotation.Fld;
import com.machntek.fld.annotation.FldElement;
import com.machntek.fld.annotation.Type;
import com.machntek.fld.dto.FldBody;

@Fld
public class FirstTest implements FldBody {

    @FldElement(order = 1, length = 10)
    String test;

    @FldElement(order = 2, length = 7)
    int testNum;
}
