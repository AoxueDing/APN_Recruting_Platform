package com.altomni.apn.repository.vendor;

import com.altomni.apn.model.ActivityMemo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
public interface ActivityCommentRepo extends PagingAndSortingRepository<ActivityMemo, Long> {
    List<ActivityMemo> findByActivityId(long activityId);
}
