package com.moshimoshi.thread.service;

import com.moshimoshi.exception.CommonException;
import com.moshimoshi.exception.ErrorCode;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ThreadService {
    private final ThreadRepository threadRepository;

    @Transactional
    public void post() {
//        Thread thread = Thread.of();
//        threadRepository.save(thread);
    }

    public Thread findOne(Long threadId) {
        return threadRepository.findById(threadId)
                .orElseThrow(() -> new CommonException(ErrorCode.THREAD_NOT_EXIST));
    }

    @Transactional
    public void deleteOne(Long threadId) {
        Thread thread = findOne(threadId);
        thread.deleteThread();
    }
}
