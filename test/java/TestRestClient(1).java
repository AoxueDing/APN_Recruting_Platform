import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.altomni.apn.model.AuthTokenInfo;

public class TestRestClient {
	private static final String username = "yisu";
	private static final String password = "yisu123";
	private static final String REST_SERVICE_URI = "http://localhost:8080/api";
	private static final String AUTH_SERVER_URI = REST_SERVICE_URI + "/oauth/token";
	private static final String QPM_PASSWORD_GRANT = "?grant_type=password&username=" + username + "&password="
			+ password;

	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	private static HttpHeaders getHeadersWithClientCredentials() {
		String plainClientCredentials = "my-trusted-client:secret";
		String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

		HttpHeaders headers = getHeaders();
		headers.add("Authorization", "Basic " + base64ClientCredentials);
		return headers;
	}

	@SuppressWarnings({ "unchecked" })
	private static AuthTokenInfo sendTokenRequest() {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = null;
		try {
			response = restTemplate.exchange(AUTH_SERVER_URI + QPM_PASSWORD_GRANT, HttpMethod.POST, request,
					Object.class);
		} catch (RestClientException e) {
			System.err.println(e.getMessage());
		}
		AuthTokenInfo tokenInfo = null;

		if (null != response) {
			if (response.getStatusCode().is2xxSuccessful()) {
				LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();

				if (map != null) {
					tokenInfo = new AuthTokenInfo();
					tokenInfo.setAccess_token((String) map.get("access_token"));
					tokenInfo.setToken_type((String) map.get("token_type"));
					tokenInfo.setRefresh_token((String) map.get("refresh_token"));
					tokenInfo.setExpires_in((int) map.get("expires_in"));
					tokenInfo.setScope((String) map.get("scope"));
					tokenInfo.setTenantId((String) map.get("tenant_id"));
					tokenInfo.setUserId((String) map.get("user_id"));
					System.out.println("Token-info:" + tokenInfo);
				} else {
					System.out.println("No user found----------");

				}
			} else {
				System.err.println(response.getBody());
			}
		}
		return tokenInfo;
	}

	public static void main(String[] args) {
		sendTokenRequest();
	}

}
