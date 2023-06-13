package api.endpoints;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsUsingPropertiesFile {

	// Reading Routes properties file
	public static String getConfigData(String key) throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Routes\\routes.properties");

		prop.load(fis);

		return prop.getProperty(key);
	}
	
	// CRUD methods implementations

	public static Response createUser(User payload) throws IOException {

		Response res = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
					   .when()
					   		.post(getConfigData("postURL"));
		return res;
	}

	public static Response readUser(String userName) throws IOException {

		Response res = given()
							.pathParam("username", userName)
					   .when()
					   		.get(getConfigData("getURL"));
		return res;
	}

	public static Response updateUser(User payload, String userName) throws IOException {

		Response res = given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
							.pathParam("username", userName)
					   .when()
					   		.put(getConfigData("updateURL"));
		return res;
	}

	public static Response deleteUser(String userName) throws IOException {

		Response res = given()
							.pathParam("username", userName)
					   .when()
					   		.get(getConfigData("deleteURL"));
		return res;
	}

}
