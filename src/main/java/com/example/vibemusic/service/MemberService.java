package com.example.vibemusic.service;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.dto.MemberJoinDTO;
import com.example.vibemusic.dto.MemberLoginDTO;

public interface MemberService {

    Member findByUsername(String username);

    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;

    void login(MemberLoginDTO memberLoginDTO) throws MidExistException;

    void modifyInformation(MemberLoginDTO memberLoginDTO) throws MidExistException;

}
