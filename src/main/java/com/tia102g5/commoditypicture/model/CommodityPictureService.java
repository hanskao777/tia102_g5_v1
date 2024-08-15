package com.tia102g5.commoditypicture.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("commodityPictureService")
public class CommodityPictureService {

    @Autowired
    CommodityPictureRepository repository;

    public void addCommodityPicture(CommodityPicture commodityPicture) {
        repository.save(commodityPicture);
    }

    public void updateCommodityPicture(CommodityPicture commodityPicture) {
        repository.save(commodityPicture);
    }

    public void deleteCommodityPicture(Integer commodityPictureID) {
        if (repository.existsById(commodityPictureID))
            repository.deleteById(commodityPictureID);
    }

    public CommodityPicture getOneCommodityPicture(Integer commodityPictureID) {
        Optional<CommodityPicture> optional = repository.findById(commodityPictureID);
        return optional.orElse(null);
    }

//    public List<CommodityPicture> getCommodityPicturesByCommodityID(Integer commodityID) {
//        return repository.findByCommodity_CommodityID(commodityID);
//    }

    public List<CommodityPicture> getAll() {
        return repository.findAll();
    }
}