package com.getmanskaya.skyeng.repository;

import com.getmanskaya.skyeng.entity.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
    PostOffice getByPostcode(String postcode);

    PostOffice getByName(String name);

}
