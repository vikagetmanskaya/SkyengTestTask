package com.getmanskaya.skyeng.service.impl;

import com.getmanskaya.skyeng.entity.*;
import com.getmanskaya.skyeng.repository.PostOfficeRepository;
import com.getmanskaya.skyeng.repository.PostalItemRepository;
import com.getmanskaya.skyeng.service.MovementHistoryService;
import com.getmanskaya.skyeng.service.PostalItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostalItemServiceImpl implements PostalItemService {
    @Autowired
    private PostalItemRepository postalItemRepository;
    @Autowired
    private MovementHistoryService movementHistoryService;
    @Autowired
    private PostOfficeRepository postOfficeRepository;

    @Override
    public boolean save(PostalItem postalItem, long id) {
        PostalItem postalItemByIdentifier = postalItemRepository.getByIdentifier(postalItem.getIdentifier());
        PostOffice postOffice = postOfficeRepository.getById(id);
        if (postalItemByIdentifier != null | postOffice == null) {
            return false;
        }
        MovementHistory movementHistory = new MovementHistory();
        movementHistory.setPostalItem(postalItem);
        movementHistory.setStatus(MovementStatus.REGISTERED);
        movementHistory.setPostOffice(postOffice);
        postalItemRepository.save(postalItem);
        movementHistoryService.save(movementHistory);
        return true;
    }
}
