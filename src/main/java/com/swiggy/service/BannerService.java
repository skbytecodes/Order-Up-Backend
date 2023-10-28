package com.swiggy.service;

import com.swiggy.entities.Banner;

import java.util.List;

public interface BannerService {

    public Banner addBanner(Banner banner);
    public Banner getBannerById(Long id);
    public List<Banner> getAllBanners();
    public Banner deleteBannerById(Long id);
}
