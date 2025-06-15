package com.example.firstproject.controller;

import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;



@RestController
@RequestMapping("/api")
public class MemberApiController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/signup")
    public void signUp(@RequestBody Member member) {
        memberRepository.save(member);
    }

    @GetMapping("/check-id")
    public Map<String, Boolean> checkDuplicateId(@RequestParam("id") String id) {
        boolean exists = memberRepository.existsById(id);
        return Collections.singletonMap("exists", exists);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member loginRequest) {
        Optional<Member> optionalMember = memberRepository.findById(loginRequest.getId());

        if (optionalMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 없음");
        }

        Member member = optionalMember.get();
        if (!member.getPw().equals(loginRequest.getPw())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호 불일치");
        }

        return ResponseEntity.ok().build(); // 200 OK, 로그인 성공
    }


}
