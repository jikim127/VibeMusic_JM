package com.example.vibemusic.repository;

import com.example.vibemusic.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false ")
    Optional<Member> getWithRoles(String mid);

    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update  Member m set m.mpw = :mpw where m.mid = :mid")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);

    Member findByMid(String mid);

    @Modifying
    @Transactional
    @Query("update Member m set m.address = :address, m.name = :name, m.phone = :phone, m.birthDate = :birthDate where m.mid = :mid")
    void updateInformation(String phone, String address, String birthDate, String name, String mid);
}
