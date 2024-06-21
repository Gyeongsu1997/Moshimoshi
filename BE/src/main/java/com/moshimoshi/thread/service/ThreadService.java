package com.moshimoshi.thread.service;

import com.moshimoshi.common.exception.BusinessException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.thread.repository.ThreadRepository;
import com.moshimoshi.user.domain.Role;
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

    public Page<Thread> findThreadList(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("id").descending());
        return threadRepository.findByDeleted(false, pageable);
    }

    @Transactional
    public void createThread(User user, ThreadPostRequest threadPostRequest) {
        Thread thread = Thread.createThread(user, threadPostRequest);
        threadRepository.save(thread);
    }

    public Thread findThread(Long threadId) {
        return threadRepository.findByIdAndDeletedFalse(threadId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
    }

    @Transactional
    public void deleteThread(User user, Long threadId) {
        Thread thread = findThread(threadId);
        if (!thread.isWriter(user) && user.getRole() != Role.ADMIN) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
        thread.deleteThread(user);
    }

    @Transactional
    public void likeThread(Long threadId) {
        Thread thread = findThread(threadId);
        thread.like();
    }
}
