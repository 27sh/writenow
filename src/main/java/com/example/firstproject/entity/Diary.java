package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "diary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dno", nullable = false)
    private Long dno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sno", nullable = false)
    private Song song;

    @Column(name = "dtitle", nullable = false, length = 255)
    private String dtitle;

    @Column(name = "post", columnDefinition = "varchar(255)")
    private String post;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "emote", length = 255)
    private String emote;  // 오늘의 기분 😊 😐 😴 등

    @Column(name = "weather", length = 255)
    private String weather; // 오늘의 날씨 ☀️ ☁️ 🌧️ 등
}
