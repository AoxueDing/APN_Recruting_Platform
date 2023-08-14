import com.altomni.apn.model.AuthTokenInfo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by JIALIN on 5/12/2017.
 */
@Slf4j
public class ITFavorite {
    public static void main(String[] args) {
        ITFavorite test = new ITFavorite();
        test.testFavorite();
    }

    public void testFavorite() {
        RestAssured.baseURI = "http://localhost:8080/api";
        RestAssured.requestSpecification = new RequestSpecBuilder().build().contentType(ContentType.JSON).accept(ContentType.JSON);
        RestAssured.responseSpecification = new ResponseSpecBuilder().build().contentType(ContentType.JSON);

        AuthTokenInfo token = Utils.genAuthToken();
        log.debug("token: {}", token);

        RestAssured.given()
                .auth().oauth2(token.getAccess_token())
                .when().get("favorites")
                .then().statusCode(200);
    }
}
