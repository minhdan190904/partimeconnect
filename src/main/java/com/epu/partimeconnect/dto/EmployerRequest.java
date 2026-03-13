package com.epu.partimeconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerRequest {
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private String description;
    private String status;
}
