package com.mita.exception;

public class InvalidStorageCredentialsException extends MitaWebException {

    public InvalidStorageCredentialsException(String errorCode) {
        super(errorCode);
    }

    public InvalidStorageCredentialsException(String errorCode,String msg) {
        super(errorCode,msg);
    }

}

