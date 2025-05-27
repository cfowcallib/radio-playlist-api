package com.callibrity.radio.playlist.api.mappers;

import com.callibrity.radio.playlist.api.dtos.TrackDto;
import com.callibrity.radio.playlist.api.entities.Track;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrackMapper {
    TrackMapper INSTANCE = Mappers.getMapper(TrackMapper.class);
    TrackDto toTrackDto(Track track);
    Track toTrack(TrackDto trackDto);
}
