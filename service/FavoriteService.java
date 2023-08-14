package com.altomni.apn.service;

import com.altomni.apn.model.Favorite;

import java.util.List;

/**
 * Created by JIALIN on 5/2/2017.
 */
public interface FavoriteService {
    ListWithIncludes<Favorite> findByUserId(Long userId);
    WithIncludes<Favorite> findById(Long Id);
    WithIncludes<Favorite> saveFavorite(Favorite favorite);
}
