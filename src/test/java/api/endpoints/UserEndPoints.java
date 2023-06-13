package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	// CRUD methods implementations
	
	public static Response createUser(User payload) {
		
		Response res = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
						.when()
							.post(Routes.postURL);	
		return res;		
	}
	
	
	public static Response readUser(String userName) {
		
		Response res = given()
							.pathParam("username", userName)
						.when()
							.get(Routes.getURL);	
		return res;
	}
	
	
	public static Response updateUser(User payload, String userName) {
		
		Response res = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
							.pathParam("username", userName)
						.when()
							.put(Routes.updateURL);
		return res;
	}
	
	
	public static Response deleteUser(String userName) {
		
		Response res = given()
							.pathParam("username", userName)
						.when()
							.get(Routes.deleteURL);	
		return res;
	}

}
