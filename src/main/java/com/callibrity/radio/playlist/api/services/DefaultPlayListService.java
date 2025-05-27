package com.callibrity.radio.playlist.api.services;

import com.callibrity.radio.playlist.api.dtos.TrackDto;
import com.callibrity.radio.playlist.api.entities.Track;
import com.callibrity.radio.playlist.api.interfaces.PlayListService;
import com.callibrity.radio.playlist.api.mappers.TrackMapper;
import com.callibrity.radio.playlist.api.repositories.PlayListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPlayListService implements PlayListService {

    private final PlayListRepository nowPlayingRepository;
    private final TrackMapper trackMapper;
    @Value("${app.playlist.history.hours}")
    private Integer HISTORY_HOURS;

    @Override
    public TrackDto getNowPlaying() {
        LocalDateTime history = LocalDateTime.now().minusHours(HISTORY_HOURS);
        Track nowPlaying = nowPlayingRepository.findPlayedAfter(history.toString(),PageRequest.of(0, 1))
                .stream().findFirst().orElse(null);
        return trackMapper.toTrackDto(nowPlaying);
    }

    @Override
    public List<TrackDto> getRecentlyPlayed(Integer returnCount) {
        LocalDateTime history = LocalDateTime.now().minusHours(HISTORY_HOURS);
        return nowPlayingRepository.findPlayedAfter(history.toString(),PageRequest.of(0, 10))
                .stream().map(trackMapper::toTrackDto).limit(returnCount).toList();
    }
}
