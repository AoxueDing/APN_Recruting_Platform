package com.altomni.apn.service.impl;

import com.altomni.apn.model.Activity;
import com.altomni.apn.model.ActivityMemo;
import com.altomni.apn.model.ActivityType;
import com.altomni.apn.repository.vendor.ActivityCommentRepo;
import com.altomni.apn.repository.vendor.ActivityTypeRepo;
import com.altomni.apn.repository.vendor.ActivityRepo;
import com.altomni.apn.service.ActivityService;
import com.altomni.apn.service.ListWithIncludes;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Alfred Yuan on 4/23/17.
 */

@Slf4j
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
	@Autowired
    private ActivityRepo activityRepo;

    @Autowired
    private ActivityCommentRepo activityCommentRepo;

	@Autowired
    private ActivityTypeRepo activityTypeRepo;

    @Override
    public ListWithIncludes<Activity> getActivities(Long candidateId) {
        log.debug("get activities of candidate:{}", candidateId);

        List<Activity> activities = activityRepo.findByCandidateId(candidateId);
        activities.forEach(activity -> activity.setActivityMemos(activityCommentRepo.findByActivityId(activity.getId())));

        return new ListWithIncludes<>(activities);
    }

    @Override
    public Activity createActivity(Activity activity) {
        log.debug("create activity");

        try {
            return activityRepo.save(activity);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }

    }

    @Override
    public ActivityMemo createActivityMemo(ActivityMemo activityMemo) {
        log.debug("create activity comment");

        try {
            return activityCommentRepo.save(activityMemo);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public ListWithIncludes<ActivityType> getAllActivityTypes() {
        log.debug("get all activity event types");

        try {
            return new ListWithIncludes<>(Lists.newArrayList(activityTypeRepo.findAll()));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ListWithIncludes<>(Collections.emptyList());
        }
    }

    @Override
    public ActivityType createActivityType(ActivityType activityType) {
        log.debug("create activity event type");

        return activityTypeRepo.save(activityType);
    }
}