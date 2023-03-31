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
public class GetListJobRequest implements Serializable {
    //page,start from 0
    private Integer page;
    //page size,Ex : 10
    private Integer size;
}
