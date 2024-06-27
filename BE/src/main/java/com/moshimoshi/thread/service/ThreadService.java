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

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ThreadService {
    private static final int PAGE_SIZE = 10;
    private final ThreadRepository threadRepository;

    public Page<Thread> findThreads(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("id").descending());
        return threadRepository.findThreadsByDeletedFalse(pageable);
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
        //요청을 보낸 사용자가 스레드의 작성자인지 검증
        threadRepository.findByIdAndUserAndDeletedFalse(threadId, user)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        //스레드 삭제(soft delete)
        int affectedRows = threadRepository.updateDeletedById(threadId, LocalDateTime.now(), user.getLoginId());
        //이미 삭제되었으면 예외 발생
        if (affectedRows == 0) {
            throw new BusinessException(ErrorCode.ALREADY_DELETED);
        }
    }

    @Transactional
    public void likeThread(Long threadId) {
        Thread thread = findThread(threadId);
        thread.like();
    }
}
