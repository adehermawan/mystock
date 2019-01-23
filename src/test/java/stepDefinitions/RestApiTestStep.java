package stepDefinitions;

import static org.hamcrest.Matchers.is; 
import static org.junit.Assert.assertThat;

import java.util.List;

import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.adhe.mystock.payload.LoginRequest;
import com.adhe.mystock.payload.SignUpRequest;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RestApiTestStep{
	private LoginRequest loginParam;
	
	private SignUpRequest signupParam;
	
	private TestRestTemplate restTemplate;
	
	private HttpHeaders headers;
	
	private ResponseEntity<String> response = null;
	
	
	@Before
	public void init() {
		loginParam = new LoginRequest();
		loginParam.setUsernameOrEmail("ade");
		loginParam.setPassword("rahasia");
		
		restTemplate = new TestRestTemplate();
		headers = new HttpHeaders();
	}
	
	@When("^users input username and password$")
	public void generate_token() throws Throwable {
	    String url = "http://localhost:5000" + "/api/auth/signin"; 

		HttpEntity<LoginRequest> entity = new HttpEntity<LoginRequest>(loginParam, headers);

		response = restTemplate.exchange(
				url,
				HttpMethod.POST, entity, String.class);		
	}

	@Then("^users get response code of (\\d+)$")
	public void get_status_code_of(int statusCode) throws Throwable {
	    assertThat("status code is incorrect : " + response.getBody(), response.getStatusCodeValue(), is(statusCode));
	}
	
	@And("^users receives generated token$")
	public void get_generated_token() throws Throwable {
		JSONAssert.assertEquals("{accessToken:x, tokenType:Bearer}", response.getBody(),  
				  new CustomComparator(
				  JSONCompareMode.STRICT, 
				  new Customization("accessToken", 
				  new RegularExpressionValueMatcher<Object>("(.+)"))));
		
	}
	
    @When("^User signup with the following details$")
    public void user_signup_with_the_following_detail(DataTable data) throws Throwable {
    	List<List<String>> obj = data.raw();
    	signupParam = new SignUpRequest();
    	signupParam.setName(obj.get(0).get(0));
    	signupParam.setUsername(obj.get(0).get(1));
    	signupParam.setEmail(obj.get(0).get(2));
    	signupParam.setPassword(obj.get(0).get(3));
    	
    	String url = "http://localhost:5000" + "/api/auth/signup"; 

		HttpEntity<SignUpRequest> entity = new HttpEntity<SignUpRequest>(signupParam, headers);

		response = restTemplate.exchange(
				url,
				HttpMethod.POST, entity, String.class);		
        
    }

    @Then("^The server should handle it and return a success status$")
    public void the_server_should_handle_it_and_return_a_success_status() throws Throwable {
    	System.out.println(response.getBody());
    	JSONAssert.assertEquals("{success:true, message:x}", response.getBody(),  
				  new CustomComparator(
				  JSONCompareMode.STRICT, 
				  new Customization("message", 
				  new RegularExpressionValueMatcher<Object>("(.+)"))));
    }
}
