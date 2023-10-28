package com.swiggy.serviceImpl;

import com.swiggy.entities.Banner;
import com.swiggy.repo.BannerRepo;
import com.swiggy.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepo bannerRepo;

    @Override
    public Banner addBanner(Banner banner) {
        if (banner != null) {
            bannerRepo.save(banner);
            return banner;
        }
        return null;
    }

    @Override
    public Banner getBannerById(Long id) {
        if (id != null) {
            Banner banner = bannerRepo.findById(id).get();
            return banner;
        }
        return null;
    }

    @Override
    public List<Banner> getAllBanners() {
        return bannerRepo.findAll();
    }

    @Override
    public Banner deleteBannerById(Long id) {
        if(id != null){
            Banner banner = bannerRepo.findById(id).get();
            bannerRepo.deleteById(id);
            return banner;
        }
        return null;
    }
}
