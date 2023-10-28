package com.swiggy.repo;

import com.swiggy.entities.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepo extends JpaRepository<Banner, Long> {

}
