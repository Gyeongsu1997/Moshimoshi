package com.moshimoshi.thread.dto;

import com.moshimoshi.thread.domain.Thread;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ThreadListResponse {
    private Integer totalPages;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer numberOfElements;
    private List<ThreadResponse> list;

    public static ThreadListResponse of(Page<Thread> threadPage) {
        return ThreadListResponse.builder()
                .totalPages(threadPage.getTotalPages())
                .pageNumber(threadPage.getNumber())
                .pageSize(threadPage.getSize())
                .numberOfElements(threadPage.getNumberOfElements())
                .list(threadPage.map(ThreadResponse::from).getContent())
                .build();
    }
}
