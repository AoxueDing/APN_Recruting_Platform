package com.altomni.apn.service.impl;

import com.altomni.apn.model.Favorite;
import com.altomni.apn.repository.vendor.FavoriteRepo;
import com.altomni.apn.service.FavoriteService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by JIALIN on 5/2/2017.
 */
@Slf4j
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService{
    @Autowired
    private FavoriteRepo favoriteRepo;

    public ListWithIncludes<Favorite> findByUserId(Long userId) {
        log.debug("findByUserId is executed");

        try {
            return new ListWithIncludes<>(favoriteRepo.findByUserId(userId));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }

    public WithIncludes<Favorite> findById(Long id) {
        log.debug("findById is executed");

        try {
            return new WithIncludes<>(favoriteRepo.findOne(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public WithIncludes<Favorite> saveFavorite(Favorite favorite) {
        log.debug("saveFavorite is executed");

        try {
            return new WithIncludes<>(favoriteRepo.save(favorite));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    /*
    public void deleteFavorite(Favorite favorite) {
        log.debug("deleteFavorite is executed");

        try {
             favoriteRepo.delete(favorite.getEntityId());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
    */
}
