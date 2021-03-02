package com.machntek.fld.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FldParsingException extends RuntimeException{
    public FldParsingException(Throwable cause) {
        super(cause);
    }
}
