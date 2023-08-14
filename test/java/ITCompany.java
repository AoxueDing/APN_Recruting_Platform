import com.altomni.apn.model.AuthTokenInfo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jianhui on 4/22/17.
 */
@Slf4j
public class ITCompany {
    public static void main(String args[]) {
        ITCompany test = new ITCompany();
        test.testCompanies();
    }

    public void testCompanies() {
        RestAssured.baseURI = "http://localhost:8080/api";
        RestAssured.requestSpecification = new RequestSpecBuilder().build().contentType(ContentType.JSON).accept(ContentType.JSON);
        RestAssured.responseSpecification = new ResponseSpecBuilder().build().contentType(ContentType.JSON);

        AuthTokenInfo token = Utils.genAuthToken();
        log.debug("token: {}", token);

        RestAssured.given()
                .auth().oauth2(token.getAccess_token())
                .when().get("industries")
                .then().statusCode(200);
//                .body("data.size()", greaterThan(1));

        RestAssured.given()
                .auth().oauth2(token.getAccess_token())
                .when().get("companies")
                .then().statusCode(200);
//                .body("data.size()", greaterThan(1))
//                .body("includes.industries.size()", greaterThan(1))
//                .body("includes.countries.size()", greaterThan(1));
    }
}
