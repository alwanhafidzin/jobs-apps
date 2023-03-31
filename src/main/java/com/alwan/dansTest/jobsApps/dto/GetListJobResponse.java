package com.alwan.dansTest.jobsApps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetListJobResponse implements Serializable {
    private Long status;
    private String message;
    private List<GetJobResponseDto> data;
    private Long totalPages;
    private Long totalElements;
    private Long numberOfElements;
}
