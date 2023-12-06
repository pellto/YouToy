package com.pellto.youtoy.util.view;

import com.pellto.youtoy.domain.view.dto.ViewHistoryDto;
import com.pellto.youtoy.domain.view.entity.ViewHistory;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ViewHistoryFixtureFactory {
    private static final Long ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Long VIDEO_ID = 1L;
    private static final Integer VIDEO_TYPE = 0;
    private static final Long LAST_VIEW_AT = 100L;
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0
    );

    public static ViewHistory create() {
        return create(ID, USER_ID, VIDEO_ID, VIDEO_TYPE, LAST_VIEW_AT, CREATED_AT);
    }

    public static ViewHistory create(LocalDateTime createdAt) {
        return create(ID, USER_ID, VIDEO_ID, VIDEO_TYPE, LAST_VIEW_AT, createdAt);
    }

    public static ViewHistory create(
            Long id,
            Long userId,
            Long videoId,
            Integer videoType,
            Long lastViewAt,
            LocalDateTime createdAt
    ) {
        return ViewHistory.builder()
                .id(id)
                .userId(userId)
                .videoId(videoId)
                .videoType(videoType)
                .lastViewAt(lastViewAt)
                .createdAt(createdAt)
                .build();
    }

    public static List<ViewHistoryDto> createList(Integer size) {
        var params = new EasyRandomParameters();
        var ret = new ArrayList<ViewHistoryDto>();
        for (int i = 0; i < size; i++) {
            var tmp = new EasyRandom(params).nextObject(ViewHistory.class);
            ret.add(new ViewHistoryDto(
                    tmp.getId(),
                    tmp.getUserId(),
                    tmp.getVideoId(),
                    tmp.getVideoType(),
                    tmp.getLastViewAt(),
                    tmp.getCreatedAt()
            ));
        }
        return ret;
    }

    public static ViewHistoryDto toDto(ViewHistory viewHistory) {
        return new ViewHistoryDto(
                viewHistory.getId(),
                viewHistory.getUserId(),
                viewHistory.getVideoId(),
                viewHistory.getVideoType(),
                viewHistory.getLastViewAt(),
                viewHistory.getCreatedAt()
        );

    }
}
