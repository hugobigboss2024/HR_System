package com.hrm.hrm_backend.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class CompanyDTO {
    
    private Long id;
    private String companyLogo;

    @NotBlank(message = "System Display Name not null")
    private String sysTemDisplayName;

    @NotBlank(message = "BR Name not null")
    private String brName;

    @NotBlank(message = "BR No. not null")
    private String brNo;

    private String employerEmpfId;

    @NotBlank(message = "Phone Number not null")
    private String phoneNumber;

    private String emailAddress;
    private String compantAddress;
    private String vendorBrNo;
    private Integer status;
}
