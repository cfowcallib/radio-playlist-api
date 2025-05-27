package com.callibrity.radio.playlist.api.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrackDto {

    private Long songId;
    private String dj;
    private String artist;
    private String track;
    private String notes;
    private Boolean artistIsLocal;
    private String label;
    private String playedAtGmt;
    private String releaseName;
    private String playedAtLocal;
    private String lastFmUrls;
    private String playStart;

}
