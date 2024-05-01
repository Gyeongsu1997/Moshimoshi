package com.moshimoshi.thread.service;

import com.moshimoshi.exception.CommonException;
import com.moshimoshi.exception.ErrorCode;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThreadService {
    private final ThreadRepository threadRepository;

    public void post() {
        threadRepository.save();
    }

    public void findOne(Long threadId) {
        Thread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new CommonException(ErrorCode.THREAD_NOT_EXIST));
    }

    public void deleteOne(Long threadId) {

    }
}
