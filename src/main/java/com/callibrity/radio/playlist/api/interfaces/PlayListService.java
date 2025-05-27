package com.callibrity.radio.playlist.api.interfaces;

import com.callibrity.radio.playlist.api.dtos.TrackDto;

import java.util.List;

public interface PlayListService {
    TrackDto getNowPlaying();
    List<TrackDto> getRecentlyPlayed(Integer returnCount);
}
