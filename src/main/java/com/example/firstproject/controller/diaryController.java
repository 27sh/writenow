package com.example.firstproject.controller;

import com.example.firstproject.entity.Diary;
import com.example.firstproject.entity.Member;
import com.example.firstproject.entity.Song;
import com.example.firstproject.repository.DiaryRepository;
import com.example.firstproject.repository.MemberRepository;
import com.example.firstproject.repository.SongRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
public class diaryController {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    private final SongRepository songRepository;

    public diaryController(DiaryRepository diaryRepository, MemberRepository memberRepository, SongRepository songRepository) {
        this.diaryRepository = diaryRepository;
        this.memberRepository = memberRepository;
        this.songRepository = songRepository;
    }


    // 글쓰기용 diaryw.mustache 페이지
    @GetMapping("/diary/write")
    public String writeDiary(Model model) {
        model.addAttribute("mode", "new");
        model.addAttribute("diary", new Diary());

        model.addAttribute("isEmoteSmile", false);
        model.addAttribute("isEmoteNeutral", false);
        model.addAttribute("isEmoteSleep", false);
        model.addAttribute("isWeatherSunny", false);
        model.addAttribute("isWeatherCloud", false);
        model.addAttribute("isWeatherRain", false);

        return "diaryw";
    }

    @PostMapping("/diary")
    @ResponseBody
    public String saveDiary(@RequestBody Diary diary) {
        // 로그인된 사용자 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Member> memberOpt = memberRepository.findById(username);

        if (memberOpt.isEmpty()) {
            return "member not found";
        }

        diary.setMember(memberOpt.get());

        // 🎯 감정에 맞는 곡 목록 중 하나 랜덤 선택
        List<Song> matchedSongs = songRepository.findBySemote(diary.getEmote());
        if (!matchedSongs.isEmpty()) {
            Song selected = matchedSongs.get(new Random().nextInt(matchedSongs.size()));
            diary.setSong(selected);
        } else {
            // 예외 처리: 감정에 맞는 노래가 없으면 기본값
            songRepository.findById(1L).ifPresent(diary::setSong);
        }

        diaryRepository.save(diary);
        return "success";
    }





    @GetMapping("/diary/{dno}")
    public String viewDiary(@PathVariable Long dno, Model model) {
        Optional<Diary> optionalDiary = diaryRepository.findById(dno);
        if (optionalDiary.isEmpty()) {
            return "redirect:/myList";
        }

        Diary diary = optionalDiary.get();
        model.addAttribute("diary", diary);
        model.addAttribute("mode", "view");

        Song song = diary.getSong();
        model.addAttribute("song", song);

        String audioPath = "/audio/song" + song.getSno() + ".mp3";
        model.addAttribute("audioPath", audioPath);

        // stime → MM:SS 형식으로 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
        model.addAttribute("formattedStime", song.getStime().format(formatter));

        // 이모지 매핑
        model.addAttribute("isEmoteSmile", "😆".equals(diary.getEmote()));
        model.addAttribute("isEmoteNeutral", "😐".equals(diary.getEmote()));
        model.addAttribute("isEmoteSleep", "😴".equals(diary.getEmote()));
        model.addAttribute("isWeatherSunny", "☀️".equals(diary.getWeather()));
        model.addAttribute("isWeatherCloud", "☁️".equals(diary.getWeather()));
        model.addAttribute("isWeatherRain", "🌧️".equals(diary.getWeather()));

        return "diary";
    }

}
