package api.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPointsUsingPropertiesFile;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDataDriven {
	
	
	@Test(priority=1, dataProvider="UserData", dataProviderClass=DataProviders.class)
	public void testPostUser(Map<String,String> map) throws IOException {
		
		User userPayload = new User();
		userPayload.setId(0);
		userPayload.setUsername(map.get("username"));
		userPayload.setFirstName(map.get("firstName"));
		userPayload.setLastName(map.get("lastName"));
		userPayload.setEmail(map.get("email"));
		userPayload.setPhone(map.get("phone"));
		userPayload.setPassword(map.get("password"));
				
		Response res = UserEndPointsUsingPropertiesFile.createUser(userPayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	
	@Test(priority=2, dataProvider="UserUpdateData", dataProviderClass=DataProviders.class)
	public void testGetUserByName(Map<String,String> map) throws IOException {
		
		Response res = UserEndPointsUsingPropertiesFile.readUser(map.get("username"));
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	
	@Test(priority=3, dataProvider="UserUpdateData", dataProviderClass=DataProviders.class)
	public void testUpdateUserByName(Map<String,String> map) throws IOException {
		
		// Update data using payload
		User userPayload = new User();
		userPayload.setId(0);
		userPayload.setUsername(map.get("username"));
		userPayload.setFirstName(map.get("firstName"));
		userPayload.setLastName(map.get("lastName"));
		userPayload.setEmail(map.get("email"));
		userPayload.setPhone(map.get("phone"));
		userPayload.setPassword(map.get("password"));
		
		Response res = UserEndPointsUsingPropertiesFile.updateUser(userPayload, map.get("username"));
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		// Checking Data after Update
		Response resAfUp = UserEndPointsUsingPropertiesFile.readUser(map.get("username"));
		resAfUp.then().log().all();
		
		Assert.assertEquals(resAfUp.getStatusCode(), 200);
		long resTime = resAfUp.getTime();
		Assert.assertEquals(resTime < 1500, true);
		System.out.println("Response time of Get Request: "+resAfUp.getTime());
	}
	
	
	@Test(priority=4, dataProvider="UserUpdateData", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(Map<String,String> map) throws IOException {
		
		Response res = UserEndPointsUsingPropertiesFile.deleteUser(map.get("username"));
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
