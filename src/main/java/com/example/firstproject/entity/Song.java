package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sno", nullable = false)
    private Long sno;

    @Column(name = "stitle", nullable = false, length = 255)
    private String stitle;

    @Column(name = "singer", nullable = false, length = 255)
    private String singer;

    @Column(name = "stime", nullable = false)
    private java.time.LocalTime stime;

    @Column(name = "cover", nullable = true, length = 255)
    private String cover;

    @Column(name = "semote", length = 255)
    private String semote;  // 오늘의 기분 😊 😐 😴 등
}
