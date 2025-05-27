package com.callibrity.radio.playlist.api.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "now_playing")
@Data
public class Track {

    @Id
    @Column(name = "song_id")
    private Long songId;
    @Column(name = "played_at_local_ts")
    private Long playedAtLocalTs;
    @Column(name = "dj")
    private String dj;
    @Column(name = "artist")
    private String artist;
    @Column(name = "track")
    private String track;
    @Column(name = "notes")
    private String notes;
    @Column(name = "artist_is_local")
    private Boolean artistIsLocal;
    @Column(name = "label")
    private String label;
    @Column(name = "played_at_gmt_ts")
    private Long playedAtGmtTs;
    @Column(name = "played_at_gmt")
    private String playedAtGmt;
    @Column(name = "release_name")
    private String releaseName;
    @Column(name = "played_at_local_expire")
    private String playedAtLocalExpire;
    @Column(name = "played_at_local")
    private String playedAtLocal;
    @Column(name = "id")
    private String id;
    @Column(name = "lastfm_urls")
    private String lastFmUrls;
    @Column(name = "play_start")
    private String playStart;

}
