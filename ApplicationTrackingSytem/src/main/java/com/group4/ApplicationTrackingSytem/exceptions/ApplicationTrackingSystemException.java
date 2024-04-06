package com.group4.ApplicationTrackingSytem.exceptions;

public class ApplicationTrackingSystemException extends Throwable {
    public ApplicationTrackingSystemException(String message){
        super(message);
    }

    public ApplicationTrackingSystemException(Throwable throwable){
        super(throwable);
    }
}
