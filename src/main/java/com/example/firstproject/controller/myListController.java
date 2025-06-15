package com.example.firstproject.controller;

import com.example.firstproject.entity.Diary;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.DiaryRepository;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class myListController {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    public myListController(DiaryRepository diaryRepository, MemberRepository memberRepository) {
        this.diaryRepository = diaryRepository;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/myList")
    public String myListPage(Model model) {
        // 현재 로그인된 사용자 ID 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();  // username = member.id

        // 로그인된 사용자의 Member 정보 조회
        Optional<Member> memberOpt = memberRepository.findById(loginId);
        if (memberOpt.isPresent()) {
            model.addAttribute("member", memberOpt.get());
        }

        // 로그인된 사용자의 일기 목록 조회
        List<Diary> diaries = diaryRepository.findByMember_Id(loginId);
        model.addAttribute("diaries", diaries);

        return "myList";
    }
}
