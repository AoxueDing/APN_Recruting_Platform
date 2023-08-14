package com.altomni.apn.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
	private static final String TENANT_ID = "tenant_id";
	private static final String USER_ID = "user_id";
	private static final String ROLE_TENANT = "ROLE_TENANT_";
	private static final String ROLE_USER = "ROLE_USER_";

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		final Map<String, Object> additionalInfo = new HashMap<>();
		String tenantId = "", userId = "";
		for (GrantedAuthority ga : user.getAuthorities()) {
			String auth = ga.getAuthority();

			if (auth.startsWith(ROLE_TENANT)) {
				tenantId = auth.substring(ROLE_TENANT.length());
			} else if (auth.startsWith(ROLE_USER)) {
				userId = auth.substring(ROLE_USER.length());
			}
		}
		additionalInfo.put(TENANT_ID, tenantId);
		additionalInfo.put(USER_ID, userId);
		DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
		oAuth2AccessToken.setAdditionalInformation(additionalInfo);

		return accessToken;
	}

}
