package com.moshimoshi.thread.service;

import com.moshimoshi.common.exception.CommonException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.PostRequest;
import com.moshimoshi.thread.repository.ThreadRepository;
import com.moshimoshi.user.domain.User;
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
        return threadRepository.findByDeleted(false, pageable);
    }

    @Transactional
    public void post(User user, PostRequest postRequest) {
        Thread thread = Thread.of(postRequest, user);
        threadRepository.save(thread);
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
    public void deleteOne(User user, Long threadId) {
        Thread thread = findOne(threadId);
        if (!thread.isWriter(user)) {
            throw new CommonException(ErrorCode.FORBIDDEN);
        }
        thread.deleteThread();
    }
}
