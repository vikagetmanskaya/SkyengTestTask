package com.getmanskaya.skyeng.service.impl;

import com.getmanskaya.skyeng.entity.MovementHistory;
import com.getmanskaya.skyeng.entity.PostalItem;
import com.getmanskaya.skyeng.repository.MovementHistoryRepository;
import com.getmanskaya.skyeng.repository.PostalItemRepository;
import com.getmanskaya.skyeng.service.MovementHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementHistoryServiceImpl implements MovementHistoryService {
    @Autowired
    private MovementHistoryRepository movementHistoryRepository;
    @Autowired
    private PostalItemRepository postalItemRepository;

    @Override
    public List<MovementHistory> getByPostalItem(long id) {
        List<MovementHistory> movementHistories = movementHistoryRepository.findAll();
        movementHistories = movementHistories
                .stream().filter(movementHistory -> movementHistory.getPostalItem().getId() == id)
                .collect(Collectors.toList());
        return movementHistories;
    }

    @Override
    public boolean save(MovementHistory movementHistory) {
        PostalItem postalItem = postalItemRepository.getById(movementHistory.getPostalItem().getId());
        if (postalItem == null) {
            return false;
        }
        movementHistory.setDateTime(LocalDateTime.now());
        movementHistoryRepository.save(movementHistory);
        return true;
    }
}
