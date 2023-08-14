import com.altomni.apn.model.AuthTokenInfo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by JIALIN on 5/15/2017.
 */
@Slf4j
public class ITCompanyEmail {
    public static void main(String args[]) {
        ITCompanyEmail test = new ITCompanyEmail();
        test.testCompanyEmails();
    }

    public void testCompanyEmails() {
        RestAssured.baseURI = "http://localhost:8080/api";
        RestAssured.requestSpecification = new RequestSpecBuilder().build().contentType(ContentType.JSON).accept(ContentType.JSON);
        RestAssured.responseSpecification = new ResponseSpecBuilder().build().contentType(ContentType.JSON);

        AuthTokenInfo token = Utils.genAuthToken();
        log.debug("token: {}", token);

        RestAssured.given()
                .auth().oauth2(token.getAccess_token())
                .when().get("companyEmails/gmail.com")
                .then().statusCode(200);
    }
}
