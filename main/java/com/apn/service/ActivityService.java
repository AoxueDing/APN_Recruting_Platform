package com.altomni.apn.service;

import com.altomni.apn.model.Activity;
import com.altomni.apn.model.ActivityMemo;
import com.altomni.apn.model.ActivityType;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
public interface ActivityService {
    ListWithIncludes<Activity> getActivities(Long candidateId);

    Activity createActivity(Activity activity);

    ActivityMemo createActivityMemo(ActivityMemo activityMemo);

    ListWithIncludes<ActivityType> getAllActivityTypes();

    ActivityType createActivityType(ActivityType activityType);
}