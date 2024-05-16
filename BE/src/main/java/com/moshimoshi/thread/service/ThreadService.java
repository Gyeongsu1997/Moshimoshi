package com.moshimoshi.thread.service;

import com.moshimoshi.common.exception.CommonException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ThreadService {
    private static final int PAGE_SIZE = 10;

    private final ThreadRepository threadRepository;

    public Page<Thread> list(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("id").descending());
        return threadRepository.findAll(pageable);
    }

    @Transactional
    public void post() {
//        Thread thread = Thread.of();
//        threadRepository.save(thread);
    }

    public Thread findOne(Long threadId) {
        Thread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new CommonException(ErrorCode.THREAD_NOT_EXIST));
        if (thread.isDeleted()) {
            throw new CommonException(ErrorCode.THREAD_NOT_EXIST);
        }
        return thread;
    }

    @Transactional
    public void deleteOne(Long threadId) {
        Thread thread = findOne(threadId);
        //현재 로그인한 유저와 thread.getWriter가 같은 유저인지 검증
        thread.deleteThread();
    }
}
