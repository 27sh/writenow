package com.example.firstproject.repository;

import com.example.firstproject.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("SELECT s FROM Song s WHERE s.semote = :semote")
    List<Song> findBySemote(@Param("semote") String semote);

}
