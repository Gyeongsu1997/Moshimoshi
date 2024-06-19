package com.moshimoshi.user.service;

import com.moshimoshi.common.exception.BusinessException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.user.domain.User;
import com.moshimoshi.user.dto.SignUpRequest;
import com.moshimoshi.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @DisplayName("회원가입: 성공")
    @Test
    void signUpSuccess() {
        //given
        SignUpRequest request = signUpRequest();

        when(userRepository.findByLoginId(anyString()))
                .thenReturn(Optional.empty());

        //when
        String id = userService.signUp(request);

        //then
        assertEquals(request.getLoginId(), id);

        //verify
        verify(userRepository).findByLoginId(anyString());
        verify(userRepository).save(any(User.class));
    }

    @DisplayName("회원가입: 중복 아이디")
    @Test
    void signUpDuplicateID() {
        //given
        SignUpRequest request = signUpRequest();

        when(userRepository.findByLoginId(anyString()))
                .thenReturn(Optional.of(new User()));

        //when
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.signUp(request));

        //then
        assertEquals(ErrorCode.DUPLICATE_ID.getHttpStatus(), exception.getHttpStatus());
        assertEquals(ErrorCode.DUPLICATE_ID.getCode(), exception.getCode());
        assertEquals(ErrorCode.DUPLICATE_ID.getMessage(), exception.getMessage());

        //verify
        verify(userRepository).findByLoginId(anyString());
        verify(userRepository, times(0)).save(any(User.class));
    }

    private SignUpRequest signUpRequest() {
        return SignUpRequest.builder()
                .loginId("test")
                .password("1234")
                .nickname("test")
                .email("test@test.com")
                .avatar("test")
                .build();
    }

}