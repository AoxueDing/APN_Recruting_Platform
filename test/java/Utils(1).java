import com.altomni.apn.model.AuthTokenInfo;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Alfred Yuan on 4/24/17.
 */
@Slf4j
public class Utils {
    public static AuthTokenInfo genAuthToken() {
        AuthTokenInfo token = RestAssured.given()
                .auth().basic("my-trusted-client", "secret")
                .queryParam("grant_type", "password")
                .queryParam("username", "yisu")
                .queryParam("password", "yisu123")
                .when().post("/oauth/token").thenReturn().as(AuthTokenInfo.class);

        log.debug("token: {}", token);
        return token;
    }
}
