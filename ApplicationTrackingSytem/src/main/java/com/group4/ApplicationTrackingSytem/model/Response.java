package com.group4.ApplicationTrackingSytem.model;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Response<T> {
    private String responseMessage;
    private String responseCode;
    private T data;

}
