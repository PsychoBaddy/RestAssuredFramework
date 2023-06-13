package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	
	User userPayload;
	
	@BeforeClass
	public void setUpData() {
		
		faker = new Faker();
		
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(6, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		// Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response res = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		// Checking Data after Update
		Response resAfUp = UserEndPoints.readUser(this.userPayload.getUsername());
		resAfUp.then().log().all();
		
		Assert.assertEquals(resAfUp.getStatusCode(), 200);
		long resTime = resAfUp.getTime();
		Assert.assertEquals(resTime < 1500, true);
		System.out.println("Response time of Get Request: "+resAfUp.getTime());
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
