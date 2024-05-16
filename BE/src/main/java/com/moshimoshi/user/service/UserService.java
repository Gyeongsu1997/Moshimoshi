package com.moshimoshi.user.service;

import com.moshimoshi.common.exception.CommonException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.user.domain.User;
import com.moshimoshi.user.dto.LoginRequest;
import com.moshimoshi.user.dto.LoginResponse;
import com.moshimoshi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest){
        User user = userRepository.findByLoginId(loginRequest.getLoginId())
                .orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_EXIST));
        if (!user.isCorrectPassword(loginRequest))
            throw new CommonException(ErrorCode.PASSWORD_INCORRECT);
        return LoginResponse.from(user);
    }
}
