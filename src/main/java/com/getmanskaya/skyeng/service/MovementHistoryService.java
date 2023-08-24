package com.getmanskaya.skyeng.service;

import com.getmanskaya.skyeng.entity.MovementHistory;

import java.util.List;

public interface MovementHistoryService {
    List<MovementHistory> getByPostalItem(long id);

    boolean save(MovementHistory movementHistory);
}
