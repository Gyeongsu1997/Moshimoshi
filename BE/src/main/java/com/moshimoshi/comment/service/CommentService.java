package com.moshimoshi.comment.service;

import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.comment.dto.CommentRequest;
import com.moshimoshi.comment.repository.CommentRepository;
import com.moshimoshi.common.exception.CommonException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.repository.ThreadRepository;
import com.moshimoshi.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ThreadRepository threadRepository;

    public Comment findOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommonException(ErrorCode.COMMENT_NOT_EXIST));
        if (comment.isDeleted()) {
            throw new CommonException(ErrorCode.COMMENT_NOT_EXIST);
        }
        return comment;
    }

    public Thread findThread(Long threadId) {
        Thread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new CommonException(ErrorCode.THREAD_NOT_EXIST));
        if (thread.isDeleted()) {
            throw new CommonException(ErrorCode.THREAD_NOT_EXIST);
        }
        return thread;
    }

    public List<Comment> list(Long threadId) {
        Thread thread = findThread(threadId);
        return thread.getAvailableComments();
    }

    @Transactional
    public void comment(User user, Long threadId, CommentRequest commentRequest) {
        Thread thread = findThread(threadId);
        Comment comment = thread.addComment(user, commentRequest);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteOne(User user, Long commentId) {
        Comment comment = findOne(commentId);
        if (!comment.isWriter(user)) {
            throw new CommonException(ErrorCode.FORBIDDEN);
        }
        comment.deleteComment();
    }

    @Transactional
    public void reply(User user, Long threadId, Long commentId, CommentRequest commentRequest) {
        Thread thread = findThread(threadId);
        Comment reply = thread.addComment(user, commentRequest);
        Comment comment = findOne(commentId);
        comment.addReply(reply);
    }
}
