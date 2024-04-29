package com.group4.ApplicationTrackingSytem.dto;

import com.group4.ApplicationTrackingSytem.model.RequestHeader;
import lombok.Data;

@Data
public class GetApplicant extends RequestHeader {
    private int id;
}
