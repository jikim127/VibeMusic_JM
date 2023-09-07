package com.example.vibemusic.service;

import com.example.vibemusic.dto.UserSessionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
@Service
public class AuthService {

    @Autowired
    private HttpSession session;


    public void login(User user) {
        // 사용자 정보를 가져와서 UserSessionDto 객체에 저장
        UserSessionDTO userSessionDTO = new UserSessionDTO();
        userSessionDTO.setUsername(user.getUsername());
        userSessionDTO.setPassword(user.getPassword());
        userSessionDTO.setRoles(user.getAuthorities());

        // 세션에 UserSessionDto 객체 저장
        session.setAttribute("user", userSessionDTO);
    }
}