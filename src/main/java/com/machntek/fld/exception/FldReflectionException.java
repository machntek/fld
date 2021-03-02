package com.machntek.fld.exception;

import lombok.NoArgsConstructor;

//@ResponseStatus
@NoArgsConstructor
public class FldReflectionException extends RuntimeException{
    public FldReflectionException(Throwable cause) {
        super(cause);
    }
}
