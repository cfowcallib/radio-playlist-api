package com.callibrity.radio.playlist.api.repositories;

import com.callibrity.radio.playlist.api.entities.Track;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayListRepository extends JpaRepository<Track, Long> {

    @Query("select t from Track t where t.playStart > :startTime order by t.playStart desc")
    List<Track> findPlayedAfter(@Param("startTime") String startTime, Pageable pageable);

}