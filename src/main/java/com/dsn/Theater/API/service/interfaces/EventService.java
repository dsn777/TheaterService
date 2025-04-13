package com.dsn.Theater.API.service.interfaces;

import com.dsn.Theater.API.dto.out.EventDto;

import java.util.List;

public interface EventService {
    void createEvent();
    void createPresetEvent();
    List<EventDto> getCurrentEvents();
}
