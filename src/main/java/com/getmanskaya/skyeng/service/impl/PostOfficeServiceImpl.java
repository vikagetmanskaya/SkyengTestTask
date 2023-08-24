package com.getmanskaya.skyeng.service.impl;

import com.getmanskaya.skyeng.entity.PostOffice;
import com.getmanskaya.skyeng.repository.PostOfficeRepository;
import com.getmanskaya.skyeng.service.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostOfficeServiceImpl implements PostOfficeService {
    @Autowired
    private PostOfficeRepository postOfficeRepository;

    @Override
    public boolean save(PostOffice postOffice) {
        PostOffice postOfficeByPostCode = postOfficeRepository.getByPostcode(postOffice.getPostcode());
        PostOffice postOfficeByName = postOfficeRepository.getByName(postOffice.getName());
        if (postOfficeByPostCode != null | postOfficeByName != null) {
            return false;
        }
        postOfficeRepository.save(postOffice);
        return true;
    }
}
