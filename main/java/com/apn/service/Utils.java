package com.altomni.apn.service;

import com.altomni.apn.model.EntityBase;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by jianhui on 4/22/17.
 */
public class Utils {
    public static <K, E extends EntityBase>
    Map<String, E> toMap(List<E> list) {
        if (list == null) return null;
        return list.stream().collect(Collectors.toMap(E::getIdString, v -> v));
    }

    public static <E extends EntityBase, FEs>
    FEs findForeignEntities(List<E> entities, Function<E, Long> fnForeignId, Function<Collection<Long>, FEs> fnFindIdIn) {
        if (entities == null) return null;
        HashSet<Long> ids = new HashSet<>();
        entities.forEach(e -> {
            Long fid = fnForeignId.apply(e);
            if (fid != null) { ids.add(fid); }
        });
        return fnFindIdIn.apply(ids);
    }

    public static <E extends EntityBase, FE>
    FE findForeignEntity(E entities, Function<E, Long> fnForeignId, Function<Long, FE> fnFindIdIn) {
        if (entities == null) return null;
        Long fid = fnForeignId.apply(entities);
        return fnFindIdIn.apply(fid);
    }

}
