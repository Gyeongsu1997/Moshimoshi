package com.moshimoshi.comment.service;

import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.comment.repository.CommentRepository;
import com.moshimoshi.common.exception.CommonException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ThreadRepository jpaThreadRepository;

    public List<Comment> list(Long threadId) {
        Thread thread = jpaThreadRepository.findById(threadId)
                .orElseThrow(() -> new CommonException(ErrorCode.THREAD_NOT_EXIST));
        return new ArrayList<>(thread.getComments());
    }
}
