package com.moshimoshi.user.service;

import com.moshimoshi.common.dto.BaseResponse;
import com.moshimoshi.common.exception.BusinessException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.auth.domain.AuthenticatedUser;
import com.moshimoshi.user.domain.User;
import com.moshimoshi.auth.dto.LogInRequest;
import com.moshimoshi.auth.dto.LogInResponse;
import com.moshimoshi.user.dto.SignUpRequest;
import com.moshimoshi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST));
    }

    @Transactional
    public String signUp(SignUpRequest signUpRequest){
        checkDuplicateLoginId(signUpRequest.getLoginId());
        User user = signUpRequest.toEntity();
        userRepository.save(user);
        return user.getLoginId();
    }

    private void checkDuplicateLoginId(String loginId) {
        Optional<User> optional = userRepository.findByLoginId(loginId);
        if (optional.isPresent())
            throw new BusinessException(ErrorCode.DUPLICATE_ID);
    }

    public LogInResponse login(LogInRequest loginRequest){
        User user = findByLoginId(loginRequest.getLoginId());
        if (!user.isCorrectPassword(loginRequest))
            throw new BusinessException(ErrorCode.INCORRECT_PASSWORD);
        return LogInResponse.from(user);
    }

    @Transactional
    public void updateRefreshToken(String loginId, String refreshToken){
        User user = findByLoginId(loginId);
        user.updateRefreshToken(refreshToken);
    }

    public AuthenticatedUser findByRefreshToken(String refreshToken) {
        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXIST));
        return AuthenticatedUser.of(user);
    }
}
