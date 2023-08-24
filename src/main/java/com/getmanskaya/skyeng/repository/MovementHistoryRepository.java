package com.getmanskaya.skyeng.repository;

import com.getmanskaya.skyeng.entity.MovementHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovementHistoryRepository extends JpaRepository<MovementHistory, Long> {
}
