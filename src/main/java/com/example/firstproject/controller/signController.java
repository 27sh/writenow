package com.example.firstproject.controller;

import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping
public class signController {

    private final MemberRepository memberRepository;

    public signController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member loginRequest, HttpSession session) {
        Optional<Member> optionalMember = memberRepository.findById(loginRequest.getId());

        if (optionalMember.isEmpty() || !optionalMember.get().getPw().equals(loginRequest.getPw())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

        session.setAttribute("loginId", loginRequest.getId()); // 세션에 ID 저장
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "signin";
    }
}
