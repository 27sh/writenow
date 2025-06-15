package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;

    @Column(name = "pw", nullable = false, length = 255)
    private String pw;

    @Column(name = "name", nullable = false, length = 255)
    private String name;
}
