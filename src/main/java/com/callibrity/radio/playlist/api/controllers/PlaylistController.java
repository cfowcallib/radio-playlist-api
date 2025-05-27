package com.callibrity.radio.playlist.api.controllers;

import com.callibrity.radio.playlist.api.dtos.TrackDto;
import com.callibrity.radio.playlist.api.interfaces.PlayListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="Playlists")
public class PlaylistController {

    private final PlayListService trackService;

    @GetMapping("/now-playing")
    @Operation(summary="Get currently playing track. ",
            description= """
            Returns the currently playing track on the radio stream. It is determined by finding the most recent startedAt time. This data
            is populated by the DJ app.
            """)
    private TrackDto nowPlaying() {
        return trackService.getNowPlaying();
    }

    @GetMapping("/recently-played")
    @Operation(summary="Play List Calls",
            description= """
            Returns X the most recently played tracks from on the radio station. This includes the currently playing track.
            """)
    private List<TrackDto> recentlyPlayed(@RequestParam(value="returnCount",required=false,defaultValue="10") Integer returnCount) {
        return trackService.getRecentlyPlayed(returnCount);
    }
}
