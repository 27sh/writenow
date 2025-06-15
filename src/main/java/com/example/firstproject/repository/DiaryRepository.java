package com.example.firstproject.repository;

import com.example.firstproject.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByMember_Id(String id);

}
