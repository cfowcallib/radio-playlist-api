package com.callibrity.radio.playlist.api.repositories;

import com.callibrity.radio.playlist.api.entities.Track;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PlayListRepositoryTest {

    @Autowired
    private PlayListRepository playListRepository;
    private Track dontTouch;
    private Track intergalactic;

    @BeforeEach
    public void setup() {
        dontTouch = new Track();
        dontTouch.setSongId(1L);
        dontTouch.setTrack("Dontcha Touch Me Baby Cause I'm shakin' too much");
        dontTouch.setArtist("Motorhead");
        dontTouch.setPlayStart("2025-05-01T00:00:00");
        playListRepository.save(dontTouch);
        intergalactic = new Track();
        intergalactic.setSongId(2L);
        intergalactic.setTrack("Intergalactic Planetary");
        intergalactic.setArtist("Beastie Boys");
        intergalactic.setPlayStart("2025-05-02T00:00:00");
        playListRepository.save(intergalactic);
    }

    @AfterEach
    public void tearDown() {
        playListRepository.deleteAll();
    }

    @Test
    void findTrackBySongId_passingValidSongId_shouldReturnTrack() {
        Track track = playListRepository.findById(dontTouch.getSongId()).orElse(null);
        assertNotNull(track);
        assertEquals(dontTouch.getTrack(), track.getTrack());
        assertEquals(dontTouch.getArtist(), track.getArtist());
        assertEquals(dontTouch.getPlayStart(), track.getPlayStart());
    }

    @Test
    void findTrackBySongId_passingInvalidSongId_shouldReturnNull() {
        Track track = playListRepository.findById(10L).orElse(null);
        assertNull(track);
    }
    @Test
    void findTrackNowPlaying_shouldReturnTrack() {
        Track nowPlaying = playListRepository.findPlayedAfter("2025-04-30T00:00:00",
                PageRequest.of(0, 1)).stream().findFirst().orElse(null);
        assertNotNull(nowPlaying);
        assertEquals(intergalactic.getTrack(), nowPlaying.getTrack());
    }

    @Test
    void findTrackNowPlaying_shouldReturnNull() {
        Track nowPlaying = playListRepository.findPlayedAfter("2025-06-30T00:00:00",
                PageRequest.of(0, 1)).stream().findFirst().orElse(null);
        assertNull(nowPlaying);
    }

    @Test
    void findRecentlyPlayed_shouldReturnPartialList() {
        Track cowboys = new Track();
        cowboys.setSongId(3L);
        cowboys.setTrack("Cowboys From Hell");
        cowboys.setArtist("Pantera");
        cowboys.setPlayStart("2025-03-01T00:00:00");
        playListRepository.save(cowboys);
        List<Track> recentTracks = playListRepository.findPlayedAfter("2025-04-30T00:00:00",
                PageRequest.of(0, 10));

        assertEquals(2, recentTracks.size());
        assertThat(recentTracks).asInstanceOf(InstanceOfAssertFactories.LIST)
                .containsExactly(intergalactic, dontTouch);
    }
}
