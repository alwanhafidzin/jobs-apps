package com.alwan.dansTest.jobsApps.controller;

import com.alwan.dansTest.jobsApps.dto.GetDetailJobRequest;
import com.alwan.dansTest.jobsApps.dto.GetDetailJobResponse;
import com.alwan.dansTest.jobsApps.dto.GetListJobRequest;
import com.alwan.dansTest.jobsApps.dto.GetListJobResponse;
import com.alwan.dansTest.jobsApps.service.JobService;
import com.alwan.dansTest.jobsApps.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public GetListJobResponse getListJob (@RequestBody GetListJobRequest request){
        return jobService.getListJob(request);
    }

    @PostMapping("/detail")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public GetDetailJobResponse getDetailJob (@RequestBody GetDetailJobRequest request){
        return jobService.getDetailJob(request);
    }
}
