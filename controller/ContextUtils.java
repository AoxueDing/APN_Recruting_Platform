package com.altomni.apn.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alfred Yuan on 5/3/17.
 */
@Slf4j
public class ContextUtils {
    public static AuthUserInfo getAuthUserInfo() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        List<GrantedAuthority> userDetailStr = new ArrayList<>(userDetails.getAuthorities());

        String roleStr = userDetailStr.get(0).getAuthority();
        String role = roleStr.substring(roleStr.indexOf("_"), roleStr.length());

        String tenantIdStr = userDetailStr.get(1).getAuthority();
        Long tenantId = Long.valueOf(tenantIdStr.substring(tenantIdStr.indexOf("<"), tenantIdStr.indexOf(">")));

        String userIdStr = userDetailStr.get(2).getAuthority();
        Long userId = Long.valueOf(userIdStr.substring(userIdStr.indexOf("<"), userIdStr.indexOf(">")));

        return new AuthUserInfo(userId, tenantId, role);
    }
}

@Data
@AllArgsConstructor
class AuthUserInfo {
    private Long userId;
    private Long tenantId;
    private String role;
}

