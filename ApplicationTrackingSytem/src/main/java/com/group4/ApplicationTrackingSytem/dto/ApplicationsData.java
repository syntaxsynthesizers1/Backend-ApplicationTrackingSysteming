package com.group4.ApplicationTrackingSytem.dto;

import com.group4.ApplicationTrackingSytem.model.Response;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApplicationsData<T> {
    private String responseMessage;
    private String responseCode;
    private int totalMales;
    private int totalFemales;
    private int count;
    private T data;

}
