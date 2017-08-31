package nz.co.vincens.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void loginSucceed() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login").param("username",
                "James").param("password", "123")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    public void loginFail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login").param("username",
                "Hacker").param("password", "abc")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(401, result.getResponse().getStatus());
    }
    
    @Test
    public void loginBadRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }
    
}