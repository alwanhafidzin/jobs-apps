package com.alwan.dansTest.jobsApps.service;

import com.alwan.dansTest.jobsApps.constant.UrlConstant;
import com.alwan.dansTest.jobsApps.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JobService {

    @Autowired
    private RestService restService;
    public GetListJobResponse getListJob(GetListJobRequest request) {
        log.info("Start get list job");
        try{
            var getResponse = restService.get(UrlConstant.URL, ApiResponseDto[].class);
            List<GetJobResponseDto> responseDtoList = new ArrayList<>();
            for (ApiResponseDto apiResponseDto : getResponse) {
                GetJobResponseDto getJobResponseDto = GetJobResponseDto
                        .builder()
                        .id(apiResponseDto.getId())
                        .company(apiResponseDto.getCompany())
                        .type(apiResponseDto.getType())
                        .companyUrl(apiResponseDto.getCompany_url())
                        .url(apiResponseDto.getUrl())
                        .createdAt(apiResponseDto.getCreated_at())
                        .title(apiResponseDto.getTitle())
                        .location(apiResponseDto.getLocation())
                        .description(apiResponseDto.getDescription())
                        .howToApply(apiResponseDto.getHow_to_apply())
                        .companyLogo(apiResponseDto.getCompany_logo())
                        .build();
                responseDtoList.add(getJobResponseDto);
            }
            PagedListHolder responsePage = new PagedListHolder(responseDtoList);
            responsePage.setPageSize(request.getSize());
            responsePage.setMaxLinkedPages(request.getPage());
            responsePage.setPage(request.getPage());
            return GetListJobResponse
                    .builder()
                    .status(200L)
                    .message("Success")
                    .data(responsePage.getPageList())
                    .totalElements((long) responseDtoList.size())
                    .numberOfElements((long) responsePage.getNrOfElements())
                    .totalPages((long) responsePage.getPageCount())
                    .build();
        }catch (Exception e){
            throw e;
        }finally {
            log.info("Success get list job");
        }
    }

    public GetDetailJobResponse getDetailJob(GetDetailJobRequest request) {
        log.info("Start get detail job");
        try{
            var getResponse = restService.get(UrlConstant.URL_DETAIL+request.getId(), ApiResponseDto.class);
            GetJobResponseDto getJobResponseDto = GetJobResponseDto
                    .builder()
                    .id(getResponse.getId())
                    .type(getResponse.getType())
                    .company(getResponse.getCompany())
                    .companyUrl(getResponse.getCompany_url())
                    .url(getResponse.getUrl())
                    .createdAt(getResponse.getCreated_at())
                    .title(getResponse.getTitle())
                    .location(getResponse.getLocation())
                    .description(getResponse.getDescription())
                    .howToApply(getResponse.getHow_to_apply())
                    .companyLogo(getResponse.getCompany_logo())
                    .build();
            return GetDetailJobResponse
                    .builder()
                    .status(200L)
                    .message("Success")
                    .data(getJobResponseDto)
                    .build();
        }catch (Exception e){
            throw e;
        }finally {
            log.info("Success get detail job");
        }
    }
}
