package com.epu.partimeconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationRequest {
    private Long jobId;
    private String fullName;
    private String phone;
    private String email;
    private Integer birthYear;
    private String gender;
    private String schoolName;
    private String major;
    private String experienceText;
    private String availableTime;
    private String note;
}
