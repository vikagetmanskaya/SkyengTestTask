package com.getmanskaya.skyeng.repository;

import com.getmanskaya.skyeng.entity.PostalItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalItemRepository extends JpaRepository<PostalItem, Long> {
    PostalItem getByIdentifier(String identifier);
}
