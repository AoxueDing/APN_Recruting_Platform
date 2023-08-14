package com.altomni.apn.controller;

import com.altomni.apn.model.Activity;
import com.altomni.apn.model.ActivityMemo;
import com.altomni.apn.model.ActivityType;
import com.altomni.apn.service.ActivityService;
import com.altomni.apn.service.ListWithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by Alfred Yuan on 4/23/17.
 */
@Slf4j
@RestController
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/candidates/{candidateId}/activities", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<Activity>> getActivities(@PathVariable("candidateId") Long candidateId) {
        log.debug("get activities of candidateId: " + candidateId);

        ListWithIncludes<Activity> activities = activityService.getActivities(candidateId);

        if (CollectionUtils.isEmpty(activities.getData())) {
            log.debug("no activities");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(activities);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/candidates/{candidateId}/activities", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Activity> createActivity(@PathVariable("candidateId")Long candidateId, @RequestBody Activity activity) {
        log.debug("create activity");

        Activity retActivity = activityService.createActivity(activity);

        if (retActivity == null) {
            log.debug("create activity failed");
            return ResponseEntity.badRequest().build(); // TODO
        }

        URI location = new UriTemplate("/candidates/{candidateId}/activities/{id}").expand(candidateId, retActivity.getId());

        return ResponseEntity.created(location).body(retActivity);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/candidates/{candidateId}/activities/{activityId}/memos", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ActivityMemo> createActivityMemo(@PathVariable("candidateId")Long candidateId, @PathVariable("activityId")Long activityId, @RequestBody ActivityMemo activityMemo) {
        log.debug("create activity comment");

        ActivityMemo retActivityMemo = activityService.createActivityMemo(activityMemo);

        if (retActivityMemo == null) {
            log.debug("create activity comment failed");
            return ResponseEntity.badRequest().build(); // TODO
        }

        URI location = new UriTemplate("/candidates/{candidateId}/activities/{activityId}/memos/{memoId}").expand(candidateId, activityId, retActivityMemo.getId());
        return ResponseEntity.created(location).body(retActivityMemo);
    }


    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/activity-types", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ListWithIncludes<ActivityType>> getAllActivityTypes() {
        log.debug("get all activity types");

        ListWithIncludes<ActivityType> activityTypes = activityService.getAllActivityTypes();

        if (CollectionUtils.isEmpty(activityTypes.getData())) {
            log.debug("no activityEventTypes");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(activityTypes);
    }


    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/activity-types", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ActivityType> createActivityType(@RequestBody ActivityType activityType) {
        log.debug("create activity type");

        ActivityType retActivityType = activityService.createActivityType(activityType);

        if (retActivityType == null) {
            log.debug("create activity event type failed");
            return ResponseEntity.badRequest().build(); // TODO
        }

        URI location = new UriTemplate("/activities-types/{id}").expand(retActivityType.getId());
        return ResponseEntity.created(location).body(retActivityType);
    }
}