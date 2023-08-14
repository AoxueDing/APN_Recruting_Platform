package com.altomni.apn.controller;

import com.altomni.apn.model.Favorite;
import com.altomni.apn.service.FavoriteService;
import com.altomni.apn.service.ListWithIncludes;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by JIALIN on 5/2/2017.
 */
@Slf4j
@RestController
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/favorites", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<ListWithIncludes<Favorite>> getFavorite() {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        ListWithIncludes<Favorite> favoriteList = favoriteService.findByUserId(userId);
        if(favoriteList == null) {
            log.debug("Favorite with userId " + userId + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(favoriteList, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/favorites", method = RequestMethod.POST)
    public ResponseEntity<Favorite> createFavorite (@RequestBody Favorite favorite) {
        AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        Long userId = userInfo.getUserId();
        log.debug("Creating Favorite" + userId + favorite.getEntityId());

        WithIncludes<Favorite> savedFavorite=favoriteService.saveFavorite(favorite);
        if (savedFavorite == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Favorite createdFavorite=savedFavorite.getData();
        createdFavorite.setCreatedAt(createdFavorite.getCreatedAt());

        URI location = new UriTemplate("/favorites/{entityId}").expand(createdFavorite.getEntityId());
        return ResponseEntity.created(location).body(createdFavorite);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/favorites/{entityId}", method = RequestMethod.DELETE)
    public ResponseEntity<WithIncludes<Favorite>> deleteFavorite(@PathVariable("id")Long entityId){
        log.debug("Deleting favorite" + entityId);

        //AuthUserInfo userInfo = ContextUtils.getAuthUserInfo();
        //Long userId = userInfo.getUserId();
        WithIncludes<Favorite> currentFavoriteWithIncludes = favoriteService.findById(entityId);
        if (currentFavoriteWithIncludes == null) {
            log.debug("Favorite with entityId " + entityId + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Favorite currentFavorite = currentFavoriteWithIncludes.getData();
        currentFavorite.setDeletedAt(currentFavorite.getDeletedAt());
        currentFavorite.setFlag(-1);

        WithIncludes<Favorite> updatedFavoriteWithIncludes = favoriteService.saveFavorite(currentFavorite);
        return new ResponseEntity<>(updatedFavoriteWithIncludes, HttpStatus.OK);
    }
}
