package com.alwan.dansTest.jobsApps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetJobResponseDto implements Serializable {

    private String id;
    private String type;
    private String url;
    private String createdAt;
    private String company;
    private String companyUrl;
    private String location;
    private String title;
    private String description;
    private String howToApply;
    private String companyLogo;
}
