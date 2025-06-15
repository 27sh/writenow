package com.example.firstproject.repository;

import com.example.firstproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsById(String id);
    Optional<Member> findById(String id);
}