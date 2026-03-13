package com.epu.partimeconnect.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class JobRequest {
    private Long employerId;
    private String title;
    private String slug;
    private String shortDescription;
    private String description;
    private String requirements;
    private String benefits;
    private String jobGroup;
    private BigDecimal salaryAmount;
    private String salaryUnit;
    private String workingType;
    private String shiftLabel;
    private String workingTimeText;
    private Integer quantity;
    private String province;
    private String district;
    private String workAddress;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String status;
    private Boolean published;
}
